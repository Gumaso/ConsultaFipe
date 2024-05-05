package com.gumaso.consultandoFipe.services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Requisicao {
    public String devolvendoJson(String enderecoBase) throws IOException, InterruptedException {
        // Método para fazer uma requisição HTTP GET e retornar o corpo da resposta como uma string JSON
        // Recebe o endereço base como argumento

        HttpClient client = HttpClient.newHttpClient();
        // Cria uma nova instância de HttpClient para enviar a solicitação HTTP

        HttpRequest request = HttpRequest.newBuilder(URI.create(enderecoBase))
                .build();
        // Cria um objeto HttpRequest usando um Builder e a URI criada a partir do endereço base
        // Representa uma solicitação HTTP GET para o endereço fornecido

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        // Envia a solicitação HTTP e recebe a resposta como um HttpResponse<String>
        // Usa BodyHandlers.ofString() para indicar que queremos o corpo da resposta como uma string

        return response.body();
        // Retorna o corpo da resposta como uma string JSON
    }


}
