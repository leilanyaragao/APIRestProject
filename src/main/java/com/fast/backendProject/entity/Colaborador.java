package com.fast.backendProject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Colaborador {
    @Id
    private Integer id;
    private String nome;

    public Colaborador(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Colaborador() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
