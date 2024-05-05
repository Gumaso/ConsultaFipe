package com.gumaso.consultandoFipe.principal;

import com.gumaso.consultandoFipe.models.*;
import com.gumaso.consultandoFipe.services.ConverteDados;
import com.gumaso.consultandoFipe.services.Requisicao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    private Requisicao requisicao = new Requisicao();
    private String BASE_URL = "https://parallelum.com.br/fipe/api/v1/";
    private Scanner sc = new Scanner(System.in);
    private ConverteDados converteDados = new ConverteDados();
    public void exibeMenu() throws IOException, InterruptedException {
        System.out.println("""
                
                ***********OPÇÕES**************
                carros
                motos
                caminhoes
                """);

        String escolhaMenu = sc.nextLine().toLowerCase();
        String json = "";
        if (escolhaMenu.toLowerCase().contains("carr")){
            BASE_URL += "carros/marcas/";
            json = requisicao.devolvendoJson(BASE_URL);
        } else if (escolhaMenu.toLowerCase().contains("mot")) {
            BASE_URL += "motos/marcas/";
            json = requisicao.devolvendoJson(BASE_URL);
        } else if (escolhaMenu.toLowerCase().contains("caminh")) {
            BASE_URL += "caminhoes/marcas/";
            json = requisicao.devolvendoJson(BASE_URL);
        }
        List<Dados> dadosLista = converteDados.obtendoListaDeDados(json, Dados.class);
    // Converte o JSON em uma lista de objetos Dados usando o método obtendoListaDeDados da classe ConverteDados

        List<ModelandoDados> modelandoDadosList = new ArrayList<>();
    // Inicializa uma lista para armazenar objetos ModelandoDados

        dadosLista.stream().forEach(obj -> modelandoDadosList.add(new ModelandoDados(obj)));
    // Para cada objeto Dados na lista dadosLista, converte-o em um objeto ModelandoDados e adiciona-o à lista modelandoDadosList

        modelandoDadosList.forEach(System.out::println);
    // Imprime os objetos ModelandoDados na lista modelandoDadosList

        System.out.println("Digite o código da marca: ");
    // Solicita que o usuário digite o código da marca

        int codigoMarca = sc.nextInt();
    // Lê o código da marca fornecido pelo usuário

        sc.nextLine();
    // Limpa o buffer do scanner

        BASE_URL += codigoMarca  + "/modelos/";
    // Atualiza a URL para consultar modelos com base no código da marca fornecido

        json = requisicao.devolvendoJson(BASE_URL);
    // Faz uma requisição à API para obter o JSON correspondente aos modelos da marca selecionada

        Modelos modelosLista = converteDados.obtendoDados(json, Modelos.class);
    // Converte o JSON em um objeto Modelos usando o método obtendoDados da classe ConverteDados

        List<ModelandoDados> modelandoDadosListModelos = new ArrayList<>();
    // Inicializa uma lista para armazenar objetos ModelandoDados para os modelos

        modelosLista.modelos().forEach(obj -> modelandoDadosListModelos.add(new ModelandoDados(obj)));
    // Para cada modelo na lista de modelos do objeto modelosLista, converte-o em um objeto ModelandoDados e adiciona-o à lista modelandoDadosListModelos

        modelandoDadosListModelos.forEach(System.out::println);
    // Imprime os objetos ModelandoDados na lista modelandoDadosListModelos

        System.out.println("Digite um trecho do nome do modelo: ");
    // Solicita que o usuário digite um trecho do nome do modelo

        String trechoNomeModelo = sc.nextLine().toLowerCase();
    // Lê o trecho do nome do modelo fornecido pelo usuário e converte para minúsculas

        var modelandoDadosListModelosFiltrados = modelandoDadosListModelos.stream().filter(obj -> obj.getNomeMarca().toLowerCase().contains(trechoNomeModelo)).collect(Collectors.toList());
    // Filtra os objetos ModelandoDados na lista modelandoDadosListModelos com base no trecho do nome do modelo fornecido pelo usuário

        modelandoDadosListModelosFiltrados.forEach(System.out::println);
    // Imprime os objetos ModelandoDados filtrados

        int codigoModelo = sc.nextInt();
    // Lê o código do modelo fornecido pelo usuário

        sc.nextLine();
    // Limpa o buffer do scanner

        BASE_URL += codigoModelo + "/anos/";
    // Atualiza a URL para consultar os anos disponíveis para o modelo selecionado

        json = requisicao.devolvendoJson(BASE_URL);
    // Faz uma requisição à API para obter o JSON correspondente aos anos disponíveis para o modelo selecionado

        List<DadosAnos> dadosAnosList = converteDados.obtendoListaDeDados(json, DadosAnos.class);
    // Converte o JSON em uma lista de objetos DadosAnos usando o método obtendoListaDeDados da classe ConverteDados

        List<ModelandoDadosVeiculos> dadosVeiculoLista = new ArrayList<>();
    // Inicializa uma lista para armazenar objetos ModelandoDadosVeiculos para os veículos

        for (var objeto: dadosAnosList){
            json = requisicao.devolvendoJson(BASE_URL + objeto.codigo());
            // Para cada objeto DadosAnos na lista dadosAnosList, atualiza a URL e faz uma requisição à API para obter o JSON correspondente aos veículos para esse ano

            DadosVeiculo veiculo = converteDados.obtendoDados(json, DadosVeiculo.class);
            // Converte o JSON em um objeto DadosVeiculo usando o método obtendoDados da classe ConverteDados

            dadosVeiculoLista.add(new ModelandoDadosVeiculos(veiculo));
            // Adiciona um novo objeto ModelandoDadosVeiculos à lista dadosVeiculoLista com base no objeto DadosVeiculo convertido
        }

        dadosVeiculoLista.stream().forEach(obj -> System.out.println(obj.toString()));
    // Imprime os objetos ModelandoDadosVeiculos na lista dadosVeiculoLista

    }
}
