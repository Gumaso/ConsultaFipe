package com.gumaso.consultandoFipe.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Dados(@JsonAlias("codigo") Integer codigoMarca, @JsonAlias("nome") String nomeMarca) {
}