/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */
package com.github.gabriel3pmenezes.qp;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Relatorio {
    
    private final List<Teste> testes = new ArrayList<>();

    private float tempoTotalDaExecucao;
    
    private float tempoMedioDaExecucao;
    
    private float consumoDeMemoria;
    
    private boolean relatorioHtml;
    
    /**
     * Construtor da classe Relatorio, recebendo as linhas de teste e definição 
     * do tipo de relatório
     * @param linhas Lista contendo as linhas de testes.
     * @param tipoDoRelatorio Valor que se True, definirá que tipo será HTML, e 
     * False se JSON
     */
    public Relatorio(List<String> linhas, boolean tipoDoRelatorio) {
        for (String linha : linhas) {
            testes.add(new Teste(linha));
        }
        
        this.relatorioHtml = tipoDoRelatorio;
    }
    
    private void geraHtml(String diretorio) throws IOException {
        int totalDeTestes, numDeFalhas = 0, numDeSucessos = 0;
        totalDeTestes = testes.size();
        for (Teste teste : testes) {
            if (!teste.getStatusTeste()) {
                numDeFalhas++;
            } else {
                numDeSucessos++;
            }
        }
        
        List<String> relatorio = new ArrayList<>();
        relatorio.add("<!DOCTYPE html>");
        relatorio.add("<html lang=\"pt-br\">");
        relatorio.add("<head>");
        relatorio.add("<title>Relatório de Testes</title>");
        relatorio.add("<meta charset=\"utf-8\">");
        relatorio.add("</head>");
        relatorio.add("<body>");
        relatorio.add("<h1>Resultado geral</h1>");
        relatorio.add("<table>");
        relatorio.add("<tr>");
        relatorio.add("<td><b>Tempo total</b></td>");
        relatorio.add("<td>" + String.format("%.0f", tempoTotalDaExecucao) + "ms</td>");
        relatorio.add("</tr>");
        relatorio.add("<tr>");
        relatorio.add("<td><b>Tempo médio</b></td>");
        relatorio.add("<td>" + String.format("%.0f", tempoMedioDaExecucao) + "ms</td>");
        relatorio.add("</tr>");
        relatorio.add("<tr>");
        relatorio.add("<td><b>Total de testes</b></td>");
        relatorio.add("<td>" + totalDeTestes + "</td>");
        relatorio.add("</tr>");
        relatorio.add("<tr>");
        relatorio.add("<td><b>Corretos</b></td>");
        relatorio.add("<td>" + numDeSucessos + "</td>");
        relatorio.add("</tr>");
        relatorio.add("<tr>");
        relatorio.add("<td><b>Falhas</b></td>");
        relatorio.add("<td>" + numDeFalhas + "</td>");
        relatorio.add("</tr>");
        relatorio.add("<tr>");
        relatorio.add("<td><b>Consumo de memória</b></td>");
        relatorio.add("<td>" + String.format(
                "%.2f", consumoDeMemoria) + " bytes</td>");
        relatorio.add("</tr>");
        relatorio.add("</table>");
        relatorio.add("");
        relatorio.add("<h1>Relatório detalhado dos testes</h1>");
        relatorio.add("<table>");
        relatorio.add("<tr>");
        relatorio.add("<th>Exmpressão</th>");
        relatorio.add("<th>Esperado</th>");
        relatorio.add("<th>Obtido</th>");
        relatorio.add("</tr>");
        for (Teste teste : testes) {
            relatorio.add("<tr>");
            relatorio.add("<td>" + teste.getExpressao() + "</td>");
            relatorio.add("<td>" + String.format(
                    "%.4f", teste.getResultadoEsperado()) + "</td>");
            relatorio.add("<td>" + String.format(
                    "%.4f", teste.getResultadoObtido()) + "</td>");
            relatorio.add("<tr>");
        }
        relatorio.add("</table>");
        relatorio.add("</body>");
        relatorio.add("</html>");
        
        Path arquivo = Paths.get(diretorio + "/relatorio.html");
        Files.write(arquivo, relatorio, Charset.forName("UTF-8"));
    }
}
