package com.kipper.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name= "cep")

public class Cep {

	//Elementos com @são chamados de Anotaçoes eles referenciam os atributos da tabela no banco de  dados
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cep")
    private Integer idCep;

    @Column(name = "numero_cep")
    private String cep;

    @Column(name = "rua")
    private String logradouro;
       
    @Column(name = "cidade")
    private String localidade;

    @Column(name = "bairro")
    private String bairro;
    
  
    
    // Getters e Setters manuais para garantir que o Jackson funcione

    public Integer getIdCep() {
        return idCep;
    }

    public void setIdCep(Integer idCep) {
        this.idCep = idCep;
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

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

}