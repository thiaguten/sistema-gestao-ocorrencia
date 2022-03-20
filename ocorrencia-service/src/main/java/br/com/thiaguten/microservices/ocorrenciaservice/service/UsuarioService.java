package br.com.thiaguten.microservices.ocorrenciaservice.service;

import java.util.List;
import java.util.Optional;

import br.com.thiaguten.microservices.ocorrenciaservice.model.Usuario;

public interface UsuarioService {

    Usuario salvar(Usuario usuario);

    Optional<Usuario> recuperar(Long id);

    Optional<Usuario> recuperar(String idpId);

    Usuario atualizar(Usuario novoUsuario, Long id);

    void deletar(Long id);

    List<Usuario> listar();

    Usuario obterReferencia(Long id);

}
