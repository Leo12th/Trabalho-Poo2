package com.Trabalho_Poo2.model;

public enum TipoMidia {
    IMAGEM("Imagem"),
    VIDEO("Vídeo"),
    DOCUMENTO("Documento"),
    AUDIO("Áudio");

    private final String descricao;

    TipoMidia(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}