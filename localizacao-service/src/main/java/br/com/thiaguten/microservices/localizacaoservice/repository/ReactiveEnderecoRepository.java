package br.com.thiaguten.microservices.localizacaoservice.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import br.com.thiaguten.microservices.localizacaoservice.domain.Endereco;
import reactor.core.publisher.Mono;

@Repository
public interface ReactiveEnderecoRepository extends ReactiveMongoRepository<Endereco, String> {

    Mono<Endereco> findByCep(String cep);

}
