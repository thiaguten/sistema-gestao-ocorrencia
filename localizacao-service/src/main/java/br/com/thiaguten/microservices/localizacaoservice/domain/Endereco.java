package br.com.thiaguten.microservices.localizacaoservice.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "enderecos")
public class Endereco {

    // // ID com nome customizado. De _id para cep
    // @Field("cep")
    // private String id;

    @Id
    private String id;
    @Indexed(unique = true)
    private String cep;

    private String logradouro;
    private String bairro;
    private String localidade;
    private String uf;

    /**
     * Provide an all-args constructor — Even if you cannot or don’t want to model
     * your entities as immutable values, there’s still value in providing a
     * constructor that takes all properties of the entity as arguments, including
     * the mutable ones, as this allows the object mapping to skip the property
     * population for optimal performance.
     * 
     * @param id
     * @param logradouro
     * @param bairro
     * @param localidade
     * @param uf
     * @param cep
     */
    public Endereco(String id, String logradouro, String bairro, String localidade, String uf, String cep) {
        this.id = id;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.localidade = localidade;
        this.uf = uf;
        this.cep = cep;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

}
