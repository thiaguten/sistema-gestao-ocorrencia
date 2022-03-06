package br.com.thiaguten.microservices.ocorrenciaservice.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.groupon.uuid.UUID;

import org.hibernate.annotations.NaturalId;

@Entity
public class Ocorrencia implements Serializable {

    private static final long serialVersionUID = 1L;

    static {
        UUID.useSequentialIds();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Chave negocial
    @NaturalId
    @Column(nullable = false, unique = true, updatable = false)
    private String codigo;

    private String descricao;

    @OneToOne(mappedBy = "ocorrencia", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private Endereco endereco;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @Column(name = "data_modificacao")
    private LocalDateTime dataModificacao;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('NOVA', 'APROVADA', 'DEVOLVIDA', 'FINALIZADA', 'CANCELADA')")
    // @Enumerated
    // @Column(columnDefinition = "SMALLINT")
    // @Convert(converter = SituacaoOcorrenciaConverter.class)
    private SituacaoOcorrecia situacao;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "servico_id", referencedColumnName = "id", nullable = false)
    private Servico servico;

    // @JsonIgnore
    // @ManyToMany(mappedBy = "ocorrencias")
    // private Set<Atendente> atendentes = new HashSet<>();

    // @formatter:off
    // O mapeamento @ManyToMany Set<Atendente> atendentes acima foi
    // comentado porque existe a necessidade de uma coluna extra no relacionamento
    // além das chaves de atendente e ocorrencia.
    //
    // Então para ser possível persistir colunas extras em um relacionamento com
    // @ManyToMany, precisa-se materializar uma entidade dedicada para mapear colunas
    // extras para o relacionamento.
    //
    // Portanto a entidade dedicada (AtendimentoOcorrencia) foi criada.
    // @formatter:on
    @JsonIgnore
    @OneToMany(mappedBy = "ocorrencia", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AtendimentoOcorrencia> atendimentos = new HashSet<>();

    public Ocorrencia() {
        setSituacao(SituacaoOcorrecia.NOVA);
        setDataCriacao(LocalDateTime.now());
        setCodigo(new UUID().toString());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public SituacaoOcorrecia getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoOcorrecia situacao) {
        this.situacao = situacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getDataModificacao() {
        return dataModificacao;
    }

    public void setDataModificacao(LocalDateTime dataModificacao) {
        this.dataModificacao = dataModificacao;
    }

    public Set<AtendimentoOcorrencia> getAtendimentos() {
        return atendimentos;
    }

    public void setAtendimentos(Set<AtendimentoOcorrencia> atendimentos) {
        this.atendimentos = atendimentos;
    }

}
