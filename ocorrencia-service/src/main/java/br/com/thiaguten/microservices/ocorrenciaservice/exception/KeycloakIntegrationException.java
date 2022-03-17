package br.com.thiaguten.microservices.ocorrenciaservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class KeycloakIntegrationException extends RuntimeException {

    public KeycloakIntegrationException(int status, String message) {
        super("Status: " + status + " - " + message);
    }

}
