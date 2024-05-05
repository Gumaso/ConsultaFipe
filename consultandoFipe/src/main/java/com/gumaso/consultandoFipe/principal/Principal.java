package com.gumaso.consultandoFipe.principal;

import com.gumaso.consultandoFipe.models.Dados;
import com.gumaso.consultandoFipe.models.ModelandoDados;
import com.gumaso.consultandoFipe.services.ConverteDados;
import com.gumaso.consultandoFipe.services.Requisicao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

        String escolhaMenu = sc.nextLine();
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
    }
}
