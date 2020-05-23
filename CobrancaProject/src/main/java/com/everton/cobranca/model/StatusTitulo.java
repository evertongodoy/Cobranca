package com.everton.cobranca.model;

public enum StatusTitulo {
    
    PENDENTE("Pendente.."),
    RECEBIDO("Recebido.."),
    CANCELADO("Cancelado..");

    private String desc;

    //Construtor
    private StatusTitulo(String descricao) {
        this.desc = descricao;
    }
    
    public String getDescricao() {
        return desc;
    }
}
