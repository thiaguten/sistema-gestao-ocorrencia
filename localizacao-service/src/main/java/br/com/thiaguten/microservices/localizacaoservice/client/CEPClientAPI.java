package br.com.thiaguten.microservices.localizacaoservice.client;

import br.com.thiaguten.microservices.localizacaoservice.dto.EnderecoDTO;
import reactor.core.publisher.Mono;

public interface CEPClientAPI {
    
    Mono<EnderecoDTO> obterEnderecoPeloCEP(String cep);

}
