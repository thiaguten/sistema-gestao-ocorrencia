package br.com.thiaguten.microservices.ocorrenciaservice.service;

import java.util.List;
import java.util.Optional;

import br.com.thiaguten.microservices.ocorrenciaservice.model.Ocorrencia;
import br.com.thiaguten.microservices.ocorrenciaservice.model.Usuario;

public interface OcorrenciaService {

    Ocorrencia salvar(Ocorrencia ocorrencia);

    Optional<Ocorrencia> recuperar(Long id);

    Ocorrencia atualizar(Ocorrencia novaOcorrencia, Long id);

    List<Ocorrencia> listar();

    List<Ocorrencia> listarPorUsuario(Usuario usuario);

    Optional<Ocorrencia> pesquisarPorCodigo(String codigo);

}
