package br.com.thiaguten.microservices.ocorrenciaservice.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.NaturalId;

@Entity
public class UsuarioIdentificado implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @JsonIgnore
    // Essa entidade filha mapeia/espelha a mesma PK da entidade pai (usuario)
    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    private Usuario usuario;

    // Chave negocial
    @NaturalId
    @Column(nullable = false, unique = true, updatable = false)
    private String cpf;

    private String primeroNome;
    private String ultimoNome;
    private String email;

    public UsuarioIdentificado() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPrimeroNome() {
        return primeroNome;
    }

    public void setPrimeroNome(String primeroNome) {
        this.primeroNome = primeroNome;
    }

    public String getUltimoNome() {
        return ultimoNome;
    }

    public void setUltimoNome(String ultimoNome) {
        this.ultimoNome = ultimoNome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof UsuarioIdentificado))
            return false;
        UsuarioIdentificado other = (UsuarioIdentificado) obj;
        return Objects.equals(cpf, other.cpf);
    }

}
