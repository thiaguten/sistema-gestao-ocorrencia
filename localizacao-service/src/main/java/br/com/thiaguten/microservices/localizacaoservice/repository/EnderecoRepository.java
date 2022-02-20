package br.com.thiaguten.microservices.localizacaoservice.repository;

import br.com.thiaguten.microservices.localizacaoservice.dto.EnderecoDTO;
import reactor.core.publisher.Mono;

public interface EnderecoRepository {

    Mono<EnderecoDTO> obterEnderecoPeloCEP(String cep);

    Mono<EnderecoDTO> salvarEnderecoPeloCEP(EnderecoDTO endereco);
    
}
