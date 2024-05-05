package com.gumaso.consultandoFipe.models;

public class ModelandoDadosVeiculos {
    private String valor;
    private String marca;
    private String modelo;
    private Integer ano;
    private String combustivel;


    public ModelandoDadosVeiculos(DadosVeiculo dadosVeiculo) {
        this.valor = dadosVeiculo.valor();
        this.marca = dadosVeiculo.marca();
        this.modelo = dadosVeiculo.modelo();
        this.ano = dadosVeiculo.ano();
        this.combustivel = dadosVeiculo.combustivel();
    }


    public String getValor() {
        return valor;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public Integer getAno() {
        return ano;
    }

    public String getCombustivel() {
        return combustivel;
    }

    @Override
    public String toString() {
        return "Informações do veiculo[ " +
                " Marca: " + this.marca +
                " Modelo: " + this.modelo +
                " Ano de produção: " + this.ano +
                " Valor: " + this.valor +
                " Tipo de combustivel: " + this.combustivel + " ]";
    }
}
