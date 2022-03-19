package br.com.thiaguten.microservices.ocorrenciaservice.support.dto;

public class UsuarioDTO {

    private Long id;
    private String cpf;
    private String nomeUsuario;
    private String primeiroNome;
    private String ultimoNome;
    private String email;
    private String idpId;
    private String senha;
    private Boolean notificacaoEmailAtivo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getPrimeiroNome() {
        return primeiroNome;
    }

    public void setPrimeiroNome(String primeiroNome) {
        this.primeiroNome = primeiroNome;
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

    public String getIdpId() {
        return idpId;
    }

    public void setIdpId(String idpId) {
        this.idpId = idpId;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Boolean getNotificacaoEmailAtivo() {
        return notificacaoEmailAtivo;
    }

    public void setNotificacaoEmailAtivo(Boolean notificacaoEmailAtivo) {
        this.notificacaoEmailAtivo = notificacaoEmailAtivo;
    }

}
