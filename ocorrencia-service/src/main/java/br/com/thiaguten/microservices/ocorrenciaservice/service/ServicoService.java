package br.com.thiaguten.microservices.ocorrenciaservice.service;

import br.com.thiaguten.microservices.ocorrenciaservice.model.Servico;

public interface ServicoService {

    Servico obterReferencia(Long id);

}
