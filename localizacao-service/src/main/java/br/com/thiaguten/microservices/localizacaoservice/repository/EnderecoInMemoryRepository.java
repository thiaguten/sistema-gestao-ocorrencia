package br.com.thiaguten.microservices.localizacaoservice.repository;

import java.time.Duration;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import br.com.thiaguten.microservices.localizacaoservice.dto.EnderecoDTO;
import br.com.thiaguten.microservices.localizacaoservice.support.util.CEPUtils;
import reactor.core.publisher.Mono;

@Repository
public class EnderecoInMemoryRepository implements EnderecoRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(EnderecoInMemoryRepository.class);

    private Cache<String, EnderecoDTO> localInMemoryDB = Caffeine.newBuilder()
            .expireAfterWrite(Duration.ofMinutes(10))
            .maximumSize(1_000)
            .build();

    @Override
    public Mono<EnderecoDTO> obterEnderecoPeloCEP(String cep) {
        LOGGER.info("Pesquisando o CEP: {}", cep);
        var endereco = localInMemoryDB.getIfPresent(cep);
        return Mono.justOrEmpty(endereco);
    }

    @Override
    public Mono<EnderecoDTO> salvarEnderecoPeloCEP(EnderecoDTO endereco) {
        var cep = CEPUtils.apenasDigitos(endereco.getCep());
        LOGGER.info("Salvando o CEP: {}", cep);
        localInMemoryDB.put(cep, endereco);
        return Mono.just(endereco);
    }

}
