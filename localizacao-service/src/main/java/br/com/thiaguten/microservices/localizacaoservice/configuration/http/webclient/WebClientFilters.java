package br.com.thiaguten.microservices.localizacaoservice.configuration.http.webclient;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;

import br.com.thiaguten.microservices.localizacaoservice.exception.HttpStatusException;
import reactor.core.publisher.Mono;

public class WebClientFilters {

	private static final Logger LOGGER = LoggerFactory.getLogger(WebClientFilters.class);

	public static ExchangeFilterFunction logRequestFilter() {
		return ExchangeFilterFunction.ofRequestProcessor(WebClientFilters::logRequest);
	}

	public static ExchangeFilterFunction logResponseFilter() {
		return ExchangeFilterFunction.ofResponseProcessor(WebClientFilters::logResponse);
	}

	private static Mono<ClientRequest> logRequest(ClientRequest request) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("----- HTTP REQUEST -----");
			LOGGER.debug("Request URL: {}", request.url());
			LOGGER.debug("Request Method: {}", request.method());

			// HttpHeaders headers = new HttpHeaders();
			// headers.addAll(request.headers());
			// LOGGER.debug("Request Headers: {}", headers);
		}
		return Mono.just(request);
	}

	private static Mono<ClientResponse> logResponse(ClientResponse response) {
		if (hasError(response)) {
			return response.toEntity(String.class)
					.flatMap(responseEntity -> {
						String body = responseEntity.getBody();
						HttpStatus responseStatus = responseEntity.getStatusCode();
						logResponseEntity(responseEntity);
						return Mono.error(new HttpStatusException(body, responseStatus));
					});
		} else {
			return Mono.just(response);
		}
	}

	public static <T> void logResponseEntity(ResponseEntity<T> responseEntity) {
		T body = responseEntity.getBody();
		HttpStatus responseStatus = responseEntity.getStatusCode();
		LOGGER.error("----- HTTP RESPONSE -----");
		LOGGER.error("Response Status: {} - {}", responseStatus.value(), responseStatus.getReasonPhrase());
		LOGGER.error("Response Headers: {}", responseEntity.getHeaders());
		LOGGER.error("Response Body: {}", body);
	}

	public static boolean hasError(ClientResponse response) {
		int rawStatusCode = response.rawStatusCode();
		HttpStatus statusCode = HttpStatus.resolve(rawStatusCode);
		return (statusCode != null ? statusCode.isError() : hasError(rawStatusCode));
	}

	private static boolean hasError(int unknownStatusCode) {
		HttpStatus.Series series = HttpStatus.Series.resolve(unknownStatusCode);
		return (series == HttpStatus.Series.CLIENT_ERROR || series == HttpStatus.Series.SERVER_ERROR);
	}

	public static void logOnError(Throwable error) {
		LOGGER.error("----- ERRO -----");
		StringBuilder sb = new StringBuilder();

		String errorMessage = ExceptionUtils.getMessage(error);
		String errorCauseMessage = ExceptionUtils.getRootCauseMessage(error);

		if (!StringUtils.isBlank(errorMessage)) {
			sb.append("Erro: ").append(errorMessage);
		}

		if (!StringUtils.isBlank(errorCauseMessage)) {
			sb.append(" Causa: ").append(errorCauseMessage);
		}

		String logMessage = sb.toString();
		LOGGER.error("{}", logMessage);
	}

	public static Mono<? extends Throwable> handleClientOrServerError(ClientResponse clientResponse) {
		Mono<ResponseEntity<String>> errorMessage = clientResponse.toEntity(String.class);
		return errorMessage
				.flatMap(responseEntity -> {
					String body = responseEntity.getBody();
					HttpStatus responseStatus = responseEntity.getStatusCode();
					WebClientFilters.logResponseEntity(responseEntity);
					return Mono.error(new HttpStatusException(String.format(
							"Erro HTTP - Status: %s - Body: %s", responseStatus, body), responseStatus));
				});
	}
}
