package com.example.demo.model;

public enum Traducao {
    PORTUGUES("pt"),
    INGLES("en"),
    FRANCES("fr"),
    ESPANHOL("es");

    private String valor;

    Traducao(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    public static Traducao fromString(String valor) {
        for (Traducao traducao : Traducao.values()) {
            if (traducao.getValor().equals(valor)) {
                return traducao;
            }
        }
        throw new IllegalArgumentException("Valor inválido: " + valor);
    }
}