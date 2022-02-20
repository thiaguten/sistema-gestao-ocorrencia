package br.com.thiaguten.microservices.localizacaoservice.repository;

import br.com.thiaguten.microservices.localizacaoservice.dto.EnderecoDTO;
import reactor.core.publisher.Mono;

public interface CEPRepository {

    Mono<EnderecoDTO> obterEnderecoPeloCEP(String cep);

    Mono<EnderecoDTO> salvarEnderecoPeloCEP(EnderecoDTO endereco);
    
}
