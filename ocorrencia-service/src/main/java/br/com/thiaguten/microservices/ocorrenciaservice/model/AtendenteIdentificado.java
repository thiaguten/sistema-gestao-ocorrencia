package br.com.thiaguten.microservices.ocorrenciaservice.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.NaturalId;

@Entity
public class AtendenteIdentificado implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @JsonIgnore
    // Essa entidade filha mapeia/espelha a mesma PK da entidade pai (atendente)
    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Atendente atendente;

    // Chave negocial
    @NaturalId
    @Column(nullable = false, unique = true, updatable = false)
    private String matricula;

    private String nome;

    public AtendenteIdentificado() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Atendente getAtendente() {
        return atendente;
    }

    public void setAtendente(Atendente atendente) {
        this.atendente = atendente;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public int hashCode() {
        return Objects.hash(matricula);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof AtendenteIdentificado))
            return false;
        AtendenteIdentificado other = (AtendenteIdentificado) obj;
        return Objects.equals(matricula, other.matricula);
    }

}
