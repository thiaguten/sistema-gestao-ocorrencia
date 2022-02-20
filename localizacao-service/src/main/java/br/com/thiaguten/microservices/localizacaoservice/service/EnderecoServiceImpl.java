package br.com.thiaguten.microservices.localizacaoservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.thiaguten.microservices.localizacaoservice.client.cep.CEPClientAPI;
import br.com.thiaguten.microservices.localizacaoservice.dto.EnderecoDTO;
import br.com.thiaguten.microservices.localizacaoservice.exception.EnderecoNotFoundException;
import br.com.thiaguten.microservices.localizacaoservice.repository.EnderecoRepository;
import reactor.core.publisher.Mono;

@Service
public class EnderecoServiceImpl implements EnderecoService {

    private final CEPClientAPI clientAPI;
    private final EnderecoRepository repository;

    @Autowired
    public EnderecoServiceImpl(EnderecoRepository repository, CEPClientAPI cepClientAPI) {
        this.clientAPI = cepClientAPI;
        this.repository = repository;
    }

    @Override
    public Mono<EnderecoDTO> obterEnderecoPeloCEP(String cep) {
        return repository
                .obterEnderecoPeloCEP(cep)
                .switchIfEmpty(buscarCEPExternalWebService(cep));
    }

    private Mono<EnderecoDTO> buscarCEPExternalWebService(String cep) {
        return clientAPI
                .obterEnderecoPeloCEP(cep)
                .flatMap(repository::salvarEnderecoPeloCEP)
                // .switchIfEmpty(Mono.error(new EnderecoNotFoundException(cep)));
                .defaultIfEmpty(new EnderecoDTO());
    }

}
