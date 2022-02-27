package br.com.thiaguten.microservices.ocorrenciaservice.support.persistence;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.com.thiaguten.microservices.ocorrenciaservice.model.SituacaoOcorrecia;

@Converter
public class SituacaoOcorrenciaConverter implements AttributeConverter<SituacaoOcorrecia, Integer> {

    @Override
    public Integer convertToDatabaseColumn(SituacaoOcorrecia situacao) {
        return situacao.getCodigo();
    }

    @Override
    public SituacaoOcorrecia convertToEntityAttribute(Integer codigo) {
        return SituacaoOcorrecia.of(codigo);
    }

}
