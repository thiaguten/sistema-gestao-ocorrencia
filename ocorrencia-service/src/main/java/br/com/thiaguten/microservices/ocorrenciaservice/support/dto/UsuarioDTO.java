package br.com.thiaguten.microservices.ocorrenciaservice.support.dto;

public class UsuarioDTO {

    private Long id;
    private String cpf;
    private String primeroNome;
    private String ultimoNome;
    private String email;
    private Boolean notificacaoEmailAtiva;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getNotificacaoEmailAtiva() {
        return notificacaoEmailAtiva;
    }

    public void setNotificacaoEmailAtiva(Boolean notificacaoEmailAtiva) {
        this.notificacaoEmailAtiva = notificacaoEmailAtiva;
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

}
