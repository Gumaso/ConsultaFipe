package com.gumaso.consultandoFipe.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.util.List;

public class ConverteDados implements IConverteDados{
    private ObjectMapper mapper = new ObjectMapper();
    @Override
    public <T> T obtendoDados(String requisicaoJson, Class<T> tClass) throws JsonProcessingException {
        T objetoObtido = mapper.readValue(requisicaoJson, tClass);
        return objetoObtido;

    }

    @Override
    public <T> List<T> obtendoListaDeDados(String json, Class<T> tClass) {
        CollectionType lista = mapper.getTypeFactory().constructCollectionType(List.class, tClass);
        try {
            return mapper.readValue(json, lista);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}