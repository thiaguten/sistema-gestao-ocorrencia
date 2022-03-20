package br.com.thiaguten.microservices.ocorrenciaservice.service;

import java.util.List;
import java.util.Optional;

import br.com.thiaguten.microservices.ocorrenciaservice.model.Servico;

public interface ServicoService {

    Servico obterReferencia(Long id);

    Servico salvarServico(Servico servico);

    Optional<Servico> recuperarServico(Long id);

    Servico atualizarServico(Servico novoServico, Long id);

    void deletarServico(Long id);

    List<Servico> listarServicos();

}
