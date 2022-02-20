package br.com.thiaguten.microservices.localizacaoservice.client;

import java.time.Duration;
import java.util.function.Predicate;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import br.com.thiaguten.microservices.localizacaoservice.configuration.http.webclient.WebClientFilters;
import br.com.thiaguten.microservices.localizacaoservice.dto.EnderecoDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import reactor.core.publisher.Mono;

@Component("cepClientAPI")
public class CEPClientAPIImpl implements CEPClientAPI {

    private static final Logger LOGGER = LoggerFactory.getLogger(CEPClientAPIImpl.class);

    @Value("${api-cep.path:}")
    private String apiCEPPath;

    @Value("${api-cep.timeout-in-millis:30000}")
    private long apiCEPTimeout;

    private final WebClient webClient;

    @Autowired
    public CEPClientAPIImpl(
            WebClient.Builder builder,
            @Value("${api-cep.base-url:}") String baseUrl) {
        this.webClient = builder.baseUrl(baseUrl).build();
    }

    @CircuitBreaker(name = "api-cep", fallbackMethod = "cepApiFallback")
    @Override
    public Mono<EnderecoDTO> obterEnderecoPeloCEP(String cep) {
        var uriTemplate = ObjectUtils.requireNonEmpty(apiCEPPath) + "/{cepCode}";
        var uriVariable = ObjectUtils.requireNonEmpty(cep) + ".json";
        return webClient.get()
                .uri(uriTemplate, uriVariable)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::isError, WebClientFilters::handleClientOrServerError)
                .bodyToMono(CEPClientAPIResponse.class)
                .timeout(Duration.ofSeconds(apiCEPTimeout))
                // // @formatter:off
                // .onErrorMap(cause -> new CEPClientAPIException(
                //         "Falha na requisição HTTP GET - API: " + url, cause))
                // // @formatter:on
                .doOnError(WebClientFilters::logOnError)
                .filter(this::isRespostaValida)
                .map(apiCepResponse -> {
                    EnderecoDTO enderecoDTO = new EnderecoDTO();
                    enderecoDTO.setLogradouro(apiCepResponse.getAddress());
                    enderecoDTO.setLocalidade(apiCepResponse.getCity());
                    enderecoDTO.setCep(apiCepResponse.getCode());
                    enderecoDTO.setBairro(apiCepResponse.getDistrict());
                    enderecoDTO.setUf(apiCepResponse.getState());
                    return enderecoDTO;
                });
    }

    private boolean isRespostaValida(CEPClientAPIResponse response) {
        Predicate<CEPClientAPIResponse> predicate = ((Predicate<CEPClientAPIResponse>) ObjectUtils::isNotEmpty)
                .and(r -> ObjectUtils.isNotEmpty(r.getAddress()))
                .and(r -> ObjectUtils.isNotEmpty(r.getCity()))
                .and(r -> ObjectUtils.isNotEmpty(r.getDistrict()))
                .and(r -> ObjectUtils.isNotEmpty(r.getState()))
                .and(r -> ObjectUtils.isNotEmpty(r.getCode()));
        return isRespostaValida(response, predicate);
    }

    private boolean isRespostaValida(CEPClientAPIResponse response, Predicate<CEPClientAPIResponse> predicate) {
        return predicate.test(response);
    }

    public Mono<EnderecoDTO> cepApiFallback(String cep, Exception ex) {
        var erroMsg = ExceptionUtils.getRootCauseMessage(ex);
        LOGGER.error("Executando fallback devido ao erro na busca do CEP: {}, Erro: {}", cep, erroMsg);
        // ...
        // return Mono.just(new EnderecoDTO());
        return Mono.empty();
    }
}
