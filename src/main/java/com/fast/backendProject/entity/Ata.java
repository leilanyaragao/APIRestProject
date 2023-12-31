package com.fast.backendProject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.util.List;

@Entity
public class Ata {

    @Id
    private Integer id;
    @OneToOne
    private Workshop workshop;
    @OneToMany
    private List<Colaborador> colaboradores;

    public Ata(Integer id, Workshop workshop, List<Colaborador> colaboradores) {
        this.id = id;
        this.workshop = workshop;
        this.colaboradores = colaboradores;
    }

    public Ata() {
    }

    public List<Colaborador> getColaboradores() {
        return colaboradores;
    }

    public void setColaboradores(List<Colaborador> colaboradores) {
        this.colaboradores = colaboradores;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Workshop getWorkshop() {
        return workshop;
    }

    public void setWorkshop(Workshop workshop) {
        this.workshop = workshop;
    }
}
