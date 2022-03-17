package br.com.thiaguten.microservices.ocorrenciaservice.support.dto;

import org.springframework.stereotype.Component;

import br.com.thiaguten.microservices.ocorrenciaservice.model.Usuario;
import br.com.thiaguten.microservices.ocorrenciaservice.model.UsuarioIdentificado;

@Component
public class UsuarioDtoMapper {

    public UsuarioDTO toDto(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setIdpId(usuario.getIdpId());
        dto.setNotificacaoEmailAtiva(usuario.getEmailAtivo());

        UsuarioIdentificado detalhe = usuario.getDetalhe();
        if (detalhe != null) {
            dto.setCpf(detalhe.getCpf());
            dto.setEmail(detalhe.getEmail());
            dto.setPrimeroNome(detalhe.getPrimeroNome());
            dto.setUltimoNome(detalhe.getUltimoNome());
            // essas informacoes nao sao persistidas aqui, somente no keycloak
            // dto.setUsername(username);
            // dto.setSenha(senha);
        }
        return dto;
    }

    public Usuario fromDto(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setId(usuarioDTO.getId());
        usuario.setIdpId(usuarioDTO.getIdpId());
        usuario.setEmailAtivo(usuarioDTO.getNotificacaoEmailAtiva());

        UsuarioIdentificado detalhe = new UsuarioIdentificado();
        detalhe.setCpf(usuarioDTO.getCpf());
        detalhe.setEmail(usuarioDTO.getEmail());
        detalhe.setId(usuario.getId());
        detalhe.setPrimeroNome(usuarioDTO.getPrimeroNome());
        detalhe.setUltimoNome(usuarioDTO.getUltimoNome());
        // essas informacoes nao sao persistidas aqui, somente no keycloak
        // detalhe.setUsername(usuarioDTO.getUsername());
        // detalhe.setSenha(usuarioDTO.getSenha());

        usuario.setDetalhe(detalhe);
        return usuario;
    }
}
