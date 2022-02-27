package br.com.thiaguten.microservices.ocorrenciaservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.thiaguten.microservices.ocorrenciaservice.model.Servico;
import br.com.thiaguten.microservices.ocorrenciaservice.repository.ServicoRepository;

@Service("servicoService")
public class ServicoServiceImpl implements ServicoService {

    private final ServicoRepository repository;

    @Autowired
    public ServicoServiceImpl(ServicoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Servico obterReferencia(Long id) {
        return repository.getById(id);
    }

}
