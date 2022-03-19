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
        dto.setNotificacaoEmailAtivo(usuario.getNotificacaoEmailAtivo());

        UsuarioIdentificado detalhe = usuario.getDetalhe();
        if (detalhe != null) {
            dto.setCpf(detalhe.getCpf());
            dto.setEmail(detalhe.getEmail());
            dto.setPrimeiroNome(detalhe.getPrimeiroNome());
            dto.setUltimoNome(detalhe.getUltimoNome());
            dto.setNomeUsuario(detalhe.getNomeUsuario());
            // Essa(s) informacao(oes) nao e/sao persistida(s) aqui, somente no keycloak.
            // dto.setSenha(senha);
        }
        return dto;
    }

    public Usuario fromDto(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setId(usuarioDTO.getId());
        usuario.setIdpId(usuarioDTO.getIdpId());
        usuario.setNotificacaoEmailAtivo(usuarioDTO.getNotificacaoEmailAtivo());

        UsuarioIdentificado detalhe = new UsuarioIdentificado();
        detalhe.setCpf(usuarioDTO.getCpf());
        detalhe.setEmail(usuarioDTO.getEmail());
        detalhe.setId(usuario.getId());
        detalhe.setPrimeiroNome(usuarioDTO.getPrimeiroNome());
        detalhe.setUltimoNome(usuarioDTO.getUltimoNome());
        detalhe.setNomeUsuario(usuarioDTO.getNomeUsuario());
        // Essa(s) informacao(oes) nao e/sao persistida(s) aqui, somente no keycloak.
        // detalhe.setSenha(usuarioDTO.getSenha());

        usuario.setDetalhe(detalhe);
        return usuario;
    }
}
