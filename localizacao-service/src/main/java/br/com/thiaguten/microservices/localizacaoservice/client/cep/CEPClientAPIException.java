package br.com.thiaguten.microservices.localizacaoservice.client.cep;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class CEPClientAPIException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CEPClientAPIException(String message, Throwable cause) {
        super(message, cause);
    }

}
