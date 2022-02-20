package br.com.thiaguten.microservices.localizacaoservice.client.cep;

import br.com.thiaguten.microservices.localizacaoservice.dto.EnderecoDTO;
import reactor.core.publisher.Mono;

public interface CEPClientAPI {
    
    Mono<EnderecoDTO> obterEnderecoPeloCEP(String cep);

}
