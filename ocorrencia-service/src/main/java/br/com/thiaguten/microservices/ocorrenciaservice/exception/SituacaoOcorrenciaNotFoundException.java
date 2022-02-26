package br.com.thiaguten.microservices.ocorrenciaservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SituacaoOcorrenciaNotFoundException extends RuntimeException {

    public SituacaoOcorrenciaNotFoundException(Integer codigo) {
        super("Não foi possível encontrar a situação: " + codigo);
    }

    public SituacaoOcorrenciaNotFoundException(String descricao) {
        super("Não foi possível encontrar a situação: " + descricao);
    }

}
