package br.com.thiaguten.microservices.ocorrenciaservice.support.security;

public final class RolesAllowedConstants {

    public static final String REALM_ROLE_SGO_USER = "sgo-user";
    public static final String REALM_ROLE_SGO_ADMIN = "sgo-admin";
    public static final String CLIENT_ROLE_SERVICOS_READ = "servicos_read";
    public static final String CLIENT_ROLE_SERVICOS_WRITE = "servicos_write";
    public static final String CLIENT_ROLE_OCORRENCIAS_READ = "ocorrencias_read";
    public static final String CLIENT_ROLE_OCORRENCIAS_WRITE = "ocorrencias_write";

    private RolesAllowedConstants() {
    }
}
