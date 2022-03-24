package br.com.thiaguten.microservices.ocorrenciaservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class KeycloakUserAlreadyExistsException extends RuntimeException {
    
    public KeycloakUserAlreadyExistsException(String message) {
        super(message);
    }

}
