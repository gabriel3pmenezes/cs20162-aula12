/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */
package com.github.gabriel3pmenezes.qp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Arquivo {

    /**
     * Método que seleciona o modo de leitura do arquivo remoto ou online
     * @param pathArquivo Contém o pathArquivo para o arquivo dos testesFinais
     * @param localArquivo Decide qual método será acionado.
     * @return Retorna uma lista contendo os testesFinais lidos do arquivo de texto.
     * @throws IOException Exceção gerada quando ocorre algum erro ao ler
     * o arquivo de texto.
     */
    public static List<String> obtemLinhas(
            String pathArquivo, boolean localArquivo) throws IOException {
        if (localArquivo) {
            return lerArquivoLocal(pathArquivo);
        } else {
            return lerArquivoRemoto(pathArquivo);
        }
    }

    /**
     * Método abre um arquivo de texto remoto e adiciona todas suas linhas em
     * uma lista.
     * @param caminho Endereço onde está o arquivo de .txt
     * @return Retorna uma lista de todas as linhas do arquivo .txt
     * @throws MalformedURLException Gerada quando o endereço fornecido não pode
     * ser lida.
     * @throws IOException Exceção gerada quando ocorre erro ao ler o arquivo 
     * .txt
     */
    public static List<String> lerArquivoRemoto(
            String caminho) throws MalformedURLException, IOException {
        List<String> testes = new ArrayList<>();
        URL url = new URL(caminho);
        HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
        conexao.setRequestMethod("GET");
        BufferedReader leitor = new BufferedReader(new InputStreamReader(
                conexao.getInputStream()));
        String linha = "";
        while ((linha = leitor.readLine()) != null) {
            testes.add(linha);
        }
        leitor.close();

        return testes;
    }

    /**
     * Método abre um arquivo de texto remoto e adiciona todas suas linhas em
     * uma lista.
     * @param caminho Endereço onde está o arquivo de .txt
     * @return Retorna uma lista de todas as linhas do arquivo .txt
     * @throws FileNotFoundException Exceção gerada quando o arquivo não pôde
     * ser encontrado no caminho especificado como parâmetro.
     * @throws IOException Exceção gerada quando ocorre erro ao ler o arquivo 
     * .txt
     */
    public static List<String> lerArquivoLocal(
            String caminho) throws FileNotFoundException, IOException {
        List<String> testesFinais = new ArrayList<>();
        BufferedReader leitor = new BufferedReader(new FileReader(caminho));
        String linha = "";
        while ((linha = leitor.readLine()) != null) {
            if (linha.length() > 0) {
                testesFinais.add(linha);
            }
        }
        leitor.close();
        
        return testesFinais;
    }

}
