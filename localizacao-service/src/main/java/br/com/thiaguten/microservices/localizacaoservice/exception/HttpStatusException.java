package br.com.thiaguten.microservices.localizacaoservice.exception;

import org.springframework.http.HttpStatus;

public class HttpStatusException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final HttpStatus status;

    public HttpStatusException(HttpStatus status) {
        this(status.getReasonPhrase(), status);
    }

    public HttpStatusException(String message, HttpStatus status) {
        this(message, (Throwable) null, status);
    }

    public HttpStatusException(String message, Throwable cause, HttpStatus status) {
        super(message, cause);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }

}
