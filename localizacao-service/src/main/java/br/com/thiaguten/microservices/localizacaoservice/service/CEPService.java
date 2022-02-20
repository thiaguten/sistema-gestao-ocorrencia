package br.com.thiaguten.microservices.localizacaoservice.service;

import br.com.thiaguten.microservices.localizacaoservice.dto.EnderecoDTO;
import reactor.core.publisher.Mono;

public interface CEPService {

    Mono<EnderecoDTO> obterEnderecoPeloCEP(String cep);

}
