package com.Trabalho_Poo2.model;

import lombok.Data;

import javax.persistence.*;
import com.Trabalho_Poo2.model.TipoMidia;


@Entity
@Data
public class Midia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;
    private String caminhoArquivo;
    private TipoMidia tipoMidia;

    // Mapeamento ManyToOne para ExperienciaViagem
    @ManyToOne
    @JoinColumn(name = "experiencia_id")
    private ExperienciaViagem experienciaViagem;

    // Construtor com os campos obrigatórios
    public Midia(String descricao, String caminhoArquivo, TipoMidia tipoMidia, ExperienciaViagem experienciaViagem) {
        this.descricao = descricao;
        this.caminhoArquivo = caminhoArquivo;
        this.tipoMidia = tipoMidia;
        this.experienciaViagem = experienciaViagem;
    }

    // Construtor padrão sem argumentos (necessário para JPA)
    public Midia() {
    }
}
