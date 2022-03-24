package br.com.thiaguten.microservices.ocorrenciaservice.service;

import static br.com.thiaguten.microservices.ocorrenciaservice.support.security.RolesAllowedConstants.REALM_ROLE_SGO_USER;

import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status.Family;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.thiaguten.microservices.ocorrenciaservice.exception.KeycloakIntegrationException;
import br.com.thiaguten.microservices.ocorrenciaservice.exception.KeycloakUserAlreadyExistsException;
import br.com.thiaguten.microservices.ocorrenciaservice.support.dto.UsuarioDTO;

@Service
public class KeycloakService {

    @Value("${app.keycloak.authServerUrl}")
    private String serverUrl;

    private Keycloak getKeycloak() {
        return KeycloakBuilder.builder()
                .serverUrl(serverUrl)
                .realm("master")
                .username("admin")
                .password("admin")
                .clientId("admin-cli")
                .resteasyClient(new ResteasyClientBuilder()
                        .connectionPoolSize(10)
                        .build())
                .build();
    }

    private RealmResource getRealmResource() {
        return getKeycloak().realm("sgo");
    }

    public String criarUsuario(UsuarioDTO usuario) {
        var realmResource = getRealmResource();
        var usersResource = realmResource.users();

        // cria o usuario
        var userRepresentation = new UserRepresentation();
        userRepresentation.setUsername(usuario.getNomeUsuario());
        userRepresentation.setEmail(usuario.getEmail());
        userRepresentation.setFirstName(usuario.getPrimeiroNome());
        userRepresentation.setLastName(usuario.getUltimoNome());
        userRepresentation.setEnabled(true);
        userRepresentation.setEmailVerified(false);
        var response = usersResource.create(userRepresentation);

        var status = response.getStatusInfo();
        var statusFamily = status.getFamily();
        var statusCode = status.getStatusCode();

        if (Family.SUCCESSFUL.equals(statusFamily)) {
            // obtem o id do usuario recem criado
            var locationPath = response.getLocation().getPath();
            var kcUserId = locationPath.substring(locationPath.lastIndexOf('/') + 1);

            // obter referencia do usuario criado
            var userResource = usersResource.get(kcUserId);

            // resetar a senha do usuario
            var credentialRepresentation = new CredentialRepresentation();
            credentialRepresentation.setTemporary(false);
            credentialRepresentation.setValue(usuario.getSenha());
            credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
            userResource.resetPassword(credentialRepresentation);

            // aplicar role de usuario ao usuario
            var roleRepresentation = realmResource.roles().get(REALM_ROLE_SGO_USER).toRepresentation();
            userResource.roles().realmLevel().add(List.of(roleRepresentation));

            return kcUserId;

        } else if (Family.CLIENT_ERROR.equals(statusFamily)) {
            if (Response.Status.CONFLICT.equals(status)) {
                throw new KeycloakUserAlreadyExistsException("Usuário já existente!");
            }
        }

        throw new KeycloakIntegrationException(
                "Falha inesperada! Causa: O método de criar usuário retornou o status: " +
                        status.getReasonPhrase() + "(" + statusCode + ")" + ". Status esperado: OK/Created (200/201).");
    }

}
