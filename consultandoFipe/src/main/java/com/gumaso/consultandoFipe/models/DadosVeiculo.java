package com.gumaso.consultandoFipe.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosVeiculo(String Valor, String Marca, String Modelo, @JsonAlias("AnoModelo") Integer Ano, String Combustivel) {
}
