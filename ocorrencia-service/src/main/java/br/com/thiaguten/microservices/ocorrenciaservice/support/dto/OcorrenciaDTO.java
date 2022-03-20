package br.com.thiaguten.microservices.ocorrenciaservice.support.dto;

public class OcorrenciaDTO {

    private Long id;
    private String descricao;
    private Long usuarioId;
    private String usuarioIdpId;
    private Long servicoId;

    public OcorrenciaDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getUsuarioIdpId() {
        return usuarioIdpId;
    }

    public void setUsuarioIdpId(String usuarioIdpId) {
        this.usuarioIdpId = usuarioIdpId;
    }

    public Long getServicoId() {
        return servicoId;
    }

    public void setServicoId(Long servicoId) {
        this.servicoId = servicoId;
    }

}
