package com.Trabalho_Poo2.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class ExperienciaViagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String texto;

    @OneToMany(mappedBy = "experienciaViagem", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Midia> midias;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    // Construtor com os campos obrigatórios
    public ExperienciaViagem(String titulo, String texto, List<Midia> midias) {
        this.titulo = titulo;
        this.texto = texto;
        this.midias = midias;
    }

    // Construtor padrão sem argumentos (necessário para JPA)
    public ExperienciaViagem() {
    }

    // Método para associar a experiência ao usuário
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
