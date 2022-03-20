package br.com.thiaguten.microservices.ocorrenciaservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ServicoNotFoundException extends RuntimeException {

    public ServicoNotFoundException(Long id) {
        super("Não foi possível encontrar o serviço: " + id);
    }
}
