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

    private final Cache<String, EnderecoDTO> localInMemoryDB = Caffeine.newBuilder()
            .expireAfterWrite(Duration.ofMinutes(10))
            .maximumSize(1_000)
            .build();

    @Override
    public Mono<EnderecoDTO> obterEnderecoPeloCEP(String cep) {
        LOGGER.debug("Cache local: Pesquisando o CEP: {}", cep);
        var endereco = localInMemoryDB.getIfPresent(CEPUtils.apenasDigitos(cep));
        return Mono.justOrEmpty(endereco);
    }

    @Override
    public Mono<EnderecoDTO> salvarEnderecoPeloCEP(EnderecoDTO endereco) {
        LOGGER.debug("Cache local: Salvando o CEP: {}", endereco.getCep());
        localInMemoryDB.put(CEPUtils.apenasDigitos(endereco.getCep()), endereco);
        return Mono.just(endereco);
    }

}
