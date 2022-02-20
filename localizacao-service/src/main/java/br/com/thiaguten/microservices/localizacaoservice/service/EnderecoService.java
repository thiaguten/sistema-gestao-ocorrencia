package br.com.thiaguten.microservices.localizacaoservice.service;

import br.com.thiaguten.microservices.localizacaoservice.dto.EnderecoDTO;
import reactor.core.publisher.Mono;

public interface EnderecoService {

    Mono<EnderecoDTO> obterEnderecoPeloCEP(String cep);

}
