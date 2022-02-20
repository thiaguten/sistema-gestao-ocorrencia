package br.com.thiaguten.microservices.localizacaoservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.thiaguten.microservices.localizacaoservice.client.CEPClientAPI;
import br.com.thiaguten.microservices.localizacaoservice.dto.EnderecoDTO;
import br.com.thiaguten.microservices.localizacaoservice.repository.CEPRepository;
import reactor.core.publisher.Mono;

@Service("cepService")
public class CEPServiceImpl implements CEPService {

    private final CEPClientAPI clientAPI;
    private final CEPRepository repository;

    @Autowired
    public CEPServiceImpl(CEPRepository repository, CEPClientAPI clientAPI) {
        this.clientAPI = clientAPI;
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
                // // @formatter:off
                // .switchIfEmpty(Mono.error(new RuntimeException(
                //         "Erro: CEP não existe localmente e nem remotamente.")));
                // // @formatter:on
                .defaultIfEmpty(new EnderecoDTO());
    }

}
