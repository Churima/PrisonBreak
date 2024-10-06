package com.prisonbreak.prisonbreak.model;

import java.util.ArrayList;
import java.util.List;

public class Prisoner {

    private Long id;
    private String nome;
    private int tempoPrisao;
    private String local;
    private List<Crime> crimes = new ArrayList<>();

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

    public int getTempoPrisao() {
        return tempoPrisao;
    }

    public void setTempoPrisao(int tempoPrisao) {
        this.tempoPrisao = tempoPrisao;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public List<Crime> getCrimes() {
        return crimes;
    }

    public void adicionarCrime(Crime crime) {
        this.crimes.add(crime);
    }
}
