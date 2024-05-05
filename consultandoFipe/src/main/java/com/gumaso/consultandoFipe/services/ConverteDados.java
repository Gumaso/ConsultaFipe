package com.gumaso.consultandoFipe.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.util.List;

public class ConverteDados implements IConverteDados {
    // Classe responsável por converter dados de JSON para objetos Java e vice-versa

    private ObjectMapper mapper = new ObjectMapper();
    // Instância de ObjectMapper, uma classe fornecida pela biblioteca Jackson para mapear entre objetos Java e JSON

    @Override
    public <T> T obtendoDados(String requisicaoJson, Class<T> tClass) throws JsonProcessingException {
        // Método para converter um JSON em um único objeto Java

        T objetoObtido = mapper.readValue(requisicaoJson, tClass);
        // Usa o ObjectMapper para mapear o JSON para um objeto Java do tipo especificado

        return objetoObtido;
        // Retorna o objeto Java convertido
    }

    @Override
    public <T> List<T> obtendoListaDeDados(String json, Class<T> tClass) {
        // Método para converter um JSON em uma lista de objetos Java

        CollectionType lista = mapper.getTypeFactory().constructCollectionType(List.class, tClass);
        // Cria um tipo CollectionType que representa uma lista de objetos do tipo especificado

        try {
            return mapper.readValue(json, lista);
            // Usa o ObjectMapper para mapear o JSON para uma lista de objetos Java do tipo especificado
        } catch (JsonProcessingException e) {
            // Captura qualquer exceção de processamento JSON
            throw new RuntimeException(e);
            // Lança uma exceção RuntimeException encapsulando a exceção original
        }
    }
}
