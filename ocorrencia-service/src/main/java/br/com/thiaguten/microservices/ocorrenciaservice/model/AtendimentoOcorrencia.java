package br.com.thiaguten.microservices.ocorrenciaservice.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "atendimento_ocorrencia")
public class AtendimentoOcorrencia implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private AtendimentoOcorrenciaPK id;

    @MapsId("atendenteId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Atendente atendente;

    @MapsId("ocorrenciaId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Ocorrencia ocorrencia;

    @Column(name = "data_atendimento")
    private LocalDateTime dataAtendimento;

    private String observacao;

    public AtendimentoOcorrencia() {
        this.dataAtendimento = LocalDateTime.now();
    }

    public AtendimentoOcorrencia(Atendente atendente, Ocorrencia ocorrencia) {
        this.atendente = atendente;
        this.ocorrencia = ocorrencia;
        this.dataAtendimento = LocalDateTime.now();
        this.id = new AtendimentoOcorrenciaPK(atendente.getId(), ocorrencia.getId());
    }

    public AtendimentoOcorrenciaPK getId() {
        return id;
    }

    public void setId(AtendimentoOcorrenciaPK id) {
        this.id = id;
    }

    public Ocorrencia getOcorrencia() {
        return ocorrencia;
    }

    public void setOcorrencia(Ocorrencia ocorrencia) {
        this.ocorrencia = ocorrencia;
    }

    public Atendente getAtendente() {
        return atendente;
    }

    public void setAtendente(Atendente atendente) {
        this.atendente = atendente;
    }

    public LocalDateTime getDataAtendimento() {
        return dataAtendimento;
    }

    public void setDataAtendimento(LocalDateTime dataAtendimento) {
        this.dataAtendimento = dataAtendimento;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    @Override
    public int hashCode() {
        return Objects.hash(atendente, ocorrencia);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof AtendimentoOcorrencia))
            return false;
        AtendimentoOcorrencia other = (AtendimentoOcorrencia) obj;
        return Objects.equals(atendente, other.atendente) && Objects.equals(ocorrencia, other.ocorrencia);
    }
    
}
