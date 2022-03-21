package br.com.thiaguten.microservices.ocorrenciaservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UsuarioNotFoundException extends RuntimeException {

    public UsuarioNotFoundException(Long id) {
        super("Não foi possível encontrar o usuário: (ID) " + id);
    }

    public UsuarioNotFoundException(String idpId) {
        super("Não foi possível encontrar o usuário: (IDP_ID) " + idpId);
    }

}
