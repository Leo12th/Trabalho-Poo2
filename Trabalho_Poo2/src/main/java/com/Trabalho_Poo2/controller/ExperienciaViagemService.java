package com.Trabalho_Poo2.controller;

import com.Trabalho_Poo2.model.ExperienciaViagem;
import com.Trabalho_Poo2.model.Midia;

import java.util.ArrayList;
import java.util.List;

public class ExperienciaViagemService {

    private List<ExperienciaViagem> experiencias;

    public ExperienciaViagemService() {
        this.experiencias = new ArrayList<>();
    }

    public void criarExperienciaViagem(ExperienciaViagem experienciaViagem) {
        experiencias.add(experienciaViagem);
    }

    public List<ExperienciaViagem> obterExperiencias() {
        return experiencias;
    }
}
