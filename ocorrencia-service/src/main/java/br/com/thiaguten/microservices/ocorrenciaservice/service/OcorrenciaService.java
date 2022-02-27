package br.com.thiaguten.microservices.ocorrenciaservice.service;

import java.util.List;
import java.util.Optional;

import br.com.thiaguten.microservices.ocorrenciaservice.model.Ocorrencia;

public interface OcorrenciaService {

    Ocorrencia salvar(Ocorrencia ocorrencia);

    Optional<Ocorrencia> recuperar(Long id);

    Ocorrencia atualizar(Ocorrencia novaOcorrencia, Long id);

    List<Ocorrencia> listar();

    // pesquisar todas as ocorrencia para o usuario logado
    // List<Ocorrencia> pesquisarPorUsuario(Long usuarioId);

    Optional<Ocorrencia> pesquisarPorCodigo(String codigo);

}
