package com.gumaso.consultandoFipe.principal;

import com.gumaso.consultandoFipe.models.Dados;
import com.gumaso.consultandoFipe.models.DadosAnos;
import com.gumaso.consultandoFipe.models.ModelandoDados;
import com.gumaso.consultandoFipe.models.Modelos;
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
        List<ModelandoDados> modelandoDadosList = new ArrayList<>();
        dadosLista.stream().forEach(obj -> modelandoDadosList.add(new ModelandoDados(obj)));
        modelandoDadosList.forEach(System.out::println);
        System.out.println("Digite o código da marca: ");
        int codigoMarca = sc.nextInt();
        sc.nextLine();
        BASE_URL += codigoMarca  + "/modelos/";
        json = requisicao.devolvendoJson(BASE_URL);
        Modelos modelosLista = converteDados.obtendoDados(json, Modelos.class);
        List<ModelandoDados> modelandoDadosListModelos = new ArrayList<>();

        modelosLista.modelos().forEach(obj -> modelandoDadosListModelos.add(new ModelandoDados(obj)));
        modelandoDadosListModelos.forEach(System.out::println);
        System.out.println("Digite um trecho do nome do modelo: ");
        String trechoNomeModelo = sc.nextLine().toLowerCase();
        var modelandoDadosListModelosFiltrados = modelandoDadosListModelos.stream().filter(obj -> obj.getNomeMarca().toLowerCase().contains(trechoNomeModelo)).collect(Collectors.toList());
        modelandoDadosListModelosFiltrados.forEach(System.out::println);
        int codigoModelo = sc.nextInt();
        sc.nextLine();

        BASE_URL += codigoModelo + "/anos/";
        json = requisicao.devolvendoJson(BASE_URL);
        List<DadosAnos> dadosAnosList = converteDados.obtendoListaDeDados(json, DadosAnos.class);
        dadosAnosList.forEach(obj -> System.out.println(obj.codigo()));
    }
}
