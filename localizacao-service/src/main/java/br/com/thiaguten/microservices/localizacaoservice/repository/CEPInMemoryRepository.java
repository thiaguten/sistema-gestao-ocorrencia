package br.com.thiaguten.microservices.localizacaoservice.repository;

import java.time.Duration;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import br.com.thiaguten.microservices.localizacaoservice.dto.EnderecoDTO;
import reactor.core.publisher.Mono;

@Repository
public class CEPInMemoryRepository implements CEPRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(CEPInMemoryRepository.class);

    private Cache<String, EnderecoDTO> db = Caffeine.newBuilder()
            .expireAfterWrite(Duration.ofMinutes(10))
            .maximumSize(1_000)
            .build();

    @Override
    public Mono<EnderecoDTO> obterEnderecoPeloCEP(String cep) {
        LOGGER.info("Pesquisando o CEP: {}", cep);
        var endereco = db.getIfPresent(cep);
        return Mono.justOrEmpty(endereco);
    }

    @Override
    public Mono<EnderecoDTO> salvarEnderecoPeloCEP(EnderecoDTO endereco) {
        // remove qualquer coisa que não seja dígito da string CEP.
        var cep = StringUtils.stripToNull(RegExUtils.removeAll(endereco.getCep(), "\\D"));
        LOGGER.info("Salvando o CEP: {}", cep);
        db.put(cep, endereco);
        return Mono.just(endereco);
    }

}
