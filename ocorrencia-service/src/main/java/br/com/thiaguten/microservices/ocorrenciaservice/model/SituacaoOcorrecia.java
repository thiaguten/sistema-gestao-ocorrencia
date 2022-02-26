package br.com.thiaguten.microservices.ocorrenciaservice.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import com.fasterxml.jackson.annotation.JsonValue;

import br.com.thiaguten.microservices.ocorrenciaservice.exception.SituacaoOcorrenciaNotFoundException;

public enum SituacaoOcorrecia {

    NOVA(1, "Nova"),
    APROVADA(2, "Aprovada"),
    DEVOLVIDA(3, "Devolvida"),
    FINALIZADA(4, "Finalizada"),
    CANCELADA(5, "Cancelada");

    private static class Holder {
        public static final Map<Integer, SituacaoOcorrecia> CODIGO_MAP = new HashMap<>();
        public static final Map<String, SituacaoOcorrecia> DESCRICAO_MAP = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    }

    private final Integer codigo;
    private final String descricao;

    SituacaoOcorrecia(final Integer codigo, final String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
        Holder.CODIGO_MAP.put(codigo, this);
        Holder.DESCRICAO_MAP.put(descricao, this);
    }

    public Integer getCodigo() {
        return codigo;
    }

    @JsonValue
    public String getDescricao() {
        return descricao;
    }

    public static SituacaoOcorrecia of(Integer codigo) {
        return Optional.ofNullable(Holder.CODIGO_MAP.get(codigo))
                .orElseThrow(() -> new SituacaoOcorrenciaNotFoundException(codigo));
    }

    public static SituacaoOcorrecia of(String descricao) {
        return Optional.ofNullable(Holder.DESCRICAO_MAP.get(descricao))
                .orElseThrow(() -> new SituacaoOcorrenciaNotFoundException(descricao));
    }

}
