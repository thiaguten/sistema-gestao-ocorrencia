package br.com.thiaguten.microservices.ocorrenciaservice.support.dto;

import org.springframework.stereotype.Component;

import br.com.thiaguten.microservices.ocorrenciaservice.model.Usuario;
import br.com.thiaguten.microservices.ocorrenciaservice.model.UsuarioIdentificado;

@Component
public class UsuarioDtoMapper {

    public UsuarioDTO toDto(Usuario usuario) {
        UsuarioIdentificado detalhe = usuario.getDetalhe();
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setCpf(detalhe.getCpf());
        dto.setEmail(detalhe.getEmail());
        dto.setPrimeroNome(detalhe.getPrimeroNome());
        dto.setUltimoNome(detalhe.getUltimoNome());
        dto.setNotificacaoEmailAtiva(usuario.getEmailAtivo());
        return dto;
    }

    public Usuario fromDto(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setId(usuarioDTO.getId());
        usuario.setEmailAtivo(usuarioDTO.getNotificacaoEmailAtiva());

        UsuarioIdentificado detalhe = new UsuarioIdentificado();
        detalhe.setCpf(usuarioDTO.getCpf());
        detalhe.setEmail(usuarioDTO.getEmail());
        detalhe.setId(usuario.getId());
        detalhe.setPrimeroNome(usuarioDTO.getPrimeroNome());
        detalhe.setUltimoNome(usuarioDTO.getUltimoNome());

        usuario.setDetalhe(detalhe);
        return usuario;
    }
}
