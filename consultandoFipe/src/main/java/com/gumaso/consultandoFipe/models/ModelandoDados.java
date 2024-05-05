package com.gumaso.consultandoFipe.models;

public class ModelandoDados {
    private Integer codigoMarca;

    public Integer getCodigoMarca() {
        return codigoMarca;
    }

    public String getNomeMarca() {
        return nomeMarca;
    }

    private String nomeMarca;

    public ModelandoDados(Dados dadosMarca){
        this.codigoMarca = dadosMarca.codigoMarca();
        this.nomeMarca = dadosMarca.nomeMarca();
    };

    @Override
    public String toString() {
        return
                "Código: " + codigoMarca +
                        " Nome: " + nomeMarca;
    }
}