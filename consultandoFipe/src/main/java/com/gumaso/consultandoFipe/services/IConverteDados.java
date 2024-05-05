package com.gumaso.consultandoFipe.services;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface IConverteDados {
    <T> T obtendoDados(String requisicaoJson, Class<T> tClass) throws JsonProcessingException;

    <T> List<T> obtendoListaDeDados(String json, Class<T> tClass);
}
