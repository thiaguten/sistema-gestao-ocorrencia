package br.com.thiaguten.microservices.ocorrenciaservice.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AtendimentoOcorrenciaPK implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "atendente_id")
    private Long atendenteId;

    @Column(name = "ocorrencia_id")
    private Long ocorrenciaId;

    public AtendimentoOcorrenciaPK() {

    }

    public AtendimentoOcorrenciaPK(Long atendenteId, Long ocorrenciaId) {
        this.atendenteId = atendenteId;
        this.ocorrenciaId = ocorrenciaId;
    }

    public Long getAtendenteId() {
        return atendenteId;
    }

    public void setAtendenteId(Long atendenteId) {
        this.atendenteId = atendenteId;
    }

    public Long getOcorrenciaId() {
        return ocorrenciaId;
    }

    public void setOcorrenciaId(Long ocorrenciaId) {
        this.ocorrenciaId = ocorrenciaId;
    }

    @Override
    public int hashCode() {
        return new org.apache.commons.lang3.builder.HashCodeBuilder()
                .append(atendenteId)
                .append(ocorrenciaId)
                .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof AtendimentoOcorrenciaPK))
            return false;
        AtendimentoOcorrenciaPK that = (AtendimentoOcorrenciaPK) obj;
        return new org.apache.commons.lang3.builder.EqualsBuilder()
                .append(atendenteId, that.atendenteId)
                .append(ocorrenciaId, that.ocorrenciaId)
                .isEquals();
    }
}
