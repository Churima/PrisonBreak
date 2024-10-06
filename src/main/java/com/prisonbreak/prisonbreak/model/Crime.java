package com.prisonbreak.prisonbreak.model;

import java.time.LocalDate;

public class Crime {

    private String descricao;
    private LocalDate data;

    public Crime(String descricao, LocalDate data) {
        this.descricao = descricao;
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDate getData() {
        return data;
    }
}
