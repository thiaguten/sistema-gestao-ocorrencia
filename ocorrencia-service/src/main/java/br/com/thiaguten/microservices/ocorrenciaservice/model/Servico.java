package br.com.thiaguten.microservices.ocorrenciaservice.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Servico implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    private String nome;

    // // @formatter:off
    /*
     * @OneToMany mapping with PRIMARY KEY bidirectional association
     * is the best way to map a one-to-many database relationship when we really
     * need the collection on the parent side of the association.
     * 
     * The down side of this bidirectional approach using collection is that we can
     * only use them when the number of child records is rather limited.
     * In reality, @OneToMany is practical only when many means few.
     * Maybe @OneToFew would have been a more suggestive name for this annotation.
     * The problem is that you cannot limit the size of a @OneToMany collection
     * like it would be the case if you used query-level pagination.
     * 
     * The cascading allow us to delete the all associated Children entities when
     * the Parent entity is deleted.
     * You should only cascade from Parent entities to Children and not the other
     * way around.
     * You should always use only the casacde operations that are demanded by your
     * business logic requirements, and not turn the CascadeType.ALL into a default
     * Parent – Child association entity state propagation configuration.
     * 
     * The orphan-removal allows us to delete the Child entity whenever it's no
     * longer referenced by its Parent.
     * 
     * Avoid the MultipleBagFetchException by using a Set instead of a List.
     */
    // @JsonIgnore
    // @OneToMany(mappedBy = "servico", cascade = CascadeType.ALL, orphanRemoval = true)
    // private Set<Ocorrencia> ocorrencias = new HashSet<>();
    // // @formatter:on

    public Servico() {

    }

    // // @formatter:off
    // // métodos utilitários (add..., remove...)
    // // usados para sincronizar os dois lados da associação bidirecional.
    // // =================================================================

    // public Set<Ocorrencia> getOcorrencias() {
    //     return ocorrencias;
    // }

    // public void addOcorrencia(Ocorrencia ocorrencia) {
    //     ocorrencias.add(ocorrencia);
    //     ocorrencia.setServico(this);
    // }

    // public void removeOcorrencia(Ocorrencia ocorrencia) {
    //     ocorrencias.remove(ocorrencia);
    //     ocorrencia.setServico(null);
    // }
    // // @formatter:on

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
