package br.com.thiaguten.microservices.ocorrenciaservice.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Atendente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "atendente", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private AtendenteIdentificado detalhe;

    // // @formatter:off
    // @ManyToMany(
    //     cascade = {
    //         CascadeType.PERSIST,
    //         CascadeType.MERGE
    //     }
    // )
    // @JoinTable(
    //     name = "atendimento_ocorrencia", 
    //     joinColumns = @JoinColumn(name = "atendente_id"), 
    //     inverseJoinColumns = @JoinColumn(name = "ocorrencia_id")
    // )
    // private Set<Ocorrencia> ocorrencias = new HashSet<>();
    // // @formatter:on

    // @formatter:off
    // O mapeamento @ManyToMany/@JoinTable Set<Ocorrencia> ocorrencias acima foi
    // comentado porque existe a necessidade de uma coluna extra no relacionamento
    // além das chaves de atendente e ocorrencia.
    //
    // Então para ser possível persistir colunas extras em um relacionamento com
    // @ManyToMany, precisa-se materializar uma entidade dedicada para mapear colunas
    // extras para o relacionamento.
    //
    // Portanto a entidade dedicada (AtendimentoOcorrencia) foi criada.
    // @formatter:on
    @OneToMany(mappedBy = "atendente", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AtendimentoOcorrencia> ocorrencias = new HashSet<>();

    public Atendente() {

    }

    // Métodos utilitários (add..., remove...) usados para sincronizar os dois lados
    // da associação bidirecional entre Atendente e Ocorrencia.
    // =============================================================================

    // @formatter:off
    // public void addOcorrencia(Ocorrencia ocorrencia) {
    //     this.ocorrencias.add(ocorrencia);
    //     ocorrencia.getAtendentes().add(this);
    // }

    // public void removeOcorrencia(Ocorrencia ocorrencia) {
    //     this.ocorrencias.remove(ocorrencia);
    //     ocorrencia.getAtendentes().remove(this);
    // }
    // // @formatter:on

    // Métodos utilitários (add..., remove...) usados para sincronizar os dois lados
    // da associação bidirecional entre Atendente e Ocorrencia via entidade de
    // relacionamento dedicada AtendimentoOcorrencia.
    // =============================================================================

    public void addOcorrencia(Ocorrencia ocorrencia) {
        AtendimentoOcorrencia atendimentoOcorrencia = new AtendimentoOcorrencia(this, ocorrencia);
        this.ocorrencias.add(atendimentoOcorrencia);
        ocorrencia.getAtendimentos().add(atendimentoOcorrencia);
    }

    public void removeOcorrencia(Ocorrencia ocorrencia) {
        for (Iterator<AtendimentoOcorrencia> iterator = this.ocorrencias.iterator(); iterator.hasNext();) {
            AtendimentoOcorrencia atendimentoOcorrencia = iterator.next();

            Atendente atendenteAtendimento = atendimentoOcorrencia.getAtendente();
            Ocorrencia ocorrenciaAtendimento = atendimentoOcorrencia.getOcorrencia();

            if (atendenteAtendimento.equals(this) && ocorrenciaAtendimento.equals(ocorrencia)) {
                iterator.remove();
                ocorrenciaAtendimento.getAtendimentos().remove(atendimentoOcorrencia);
                atendimentoOcorrencia.setAtendente(null);
                atendimentoOcorrencia.setOcorrencia(null);
                break;
            }
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AtendenteIdentificado getDetalhe() {
        return detalhe;
    }

    public void setDetalhe(AtendenteIdentificado detalhe) {
        this.detalhe = detalhe;
    }

    public Set<AtendimentoOcorrencia> getOcorrencias() {
        return ocorrencias;
    }

    public void setOcorrencias(Set<AtendimentoOcorrencia> ocorrencias) {
        this.ocorrencias = ocorrencias;
    }

}
