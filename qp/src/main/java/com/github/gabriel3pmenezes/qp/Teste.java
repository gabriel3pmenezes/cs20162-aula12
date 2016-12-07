/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */
package com.github.gabriel3pmenezes.qp;

import java.util.Objects;

public class Teste {
    
    private final String expressao;
    
    private final String atributo;
    
    private final String[] nomeAtributo;
    
    private final Float[] valorAtributo;
    
    private final Float resultadoEsperado;
    
    private Float resultadoObtido;
    
    private boolean statusTeste;
    
    /**
     * Construtor que também divide a linha de teste em blocos separados por tipo
     * @param linha String da linha de teste.
     */
    public Teste(String linha) {
        String teste = linha.replace(" ", "");
        String[] blocos = teste.split(";");
        expressao = blocos[0];
        atributo = blocos[1];
        
        if (atributo.isEmpty()) {
            nomeAtributo = null;
            valorAtributo = null;
        } else {
            String[] atributosSeparados = atributo.split(",");
            nomeAtributo = new String[atributosSeparados.length];
            valorAtributo = new Float[atributosSeparados.length];
            int i = 0;
            for (String atributo : atributosSeparados) {
                String[] atributosQuebrados = atributo.split("=");
                nomeAtributo[i] = atributosQuebrados[0];
                valorAtributo[i] = Float.parseFloat(atributosQuebrados[1]);
                i++;
            }
        }
        
        resultadoEsperado = Float.parseFloat(blocos[2]);
        resultadoObtido = null;
    }
    
    /**
     * Método que retorna a expressão da linha de teste.
     * @return String Conrém a expressão.
     */
    public String getExpressao() {
        return expressao;
    }
    
    /**
     * Método que retorna o nome de cada atributo 
     * @return Vetor contendo o nome dos atributo
     */
    public String[] getNomeAtributo() {
        return nomeAtributo;
    }
    
    /**
     * Método que retorna o valor de cada atributo
     * @return Vetor contendo o valor dos atributos.
     */
    public Float[] getValorAtributo() {
        return valorAtributo;
    }
    
    /**
     * Método que retorna a sequencia total de atributo 
     * @return String contendo os atributos do teste.
     */
    public String getAtributo() {
        return atributo;
    }
    
    /**
     * Método que verifica se foram definidosatributos no bloco
     * @return Valor true, se existir atributo definido, ou false, caso não.
     */
    public boolean atributoExiste() {
        return !(atributo.isEmpty());
    }
    
    /**
     * Método que retorna o valor do resultado esperado
     * @return Valor do resultado esperado
     */
    public Float getResultadoEsperado() {
        return resultadoEsperado;
    }
    
    /**
     * Método que retorna o valor do resultado obtido
     * @return Float com o valor do resultado obtido.
     */
    public Float getResultadoObtido() {
        return resultadoObtido;
    }
    
    /**
     * Método que verifica se o resultado obtido foi igual resultado esperado
     */
    public void setStatusDoTeste() {
        if (resultadoObtido == null) {
            this.statusTeste = false;
        } else {
            this.statusTeste = Objects.equals(
                    resultadoObtido, resultadoEsperado);
        }
    }
    
    /**
     * Método que retorna o rsultado do último método
     * @return Valor True se o teste obteve sucesso ou False para falha
     */
    public boolean getStatusTeste() {
        return statusTeste;
    }
    
    /**
     * Método que executa o método de realização do cálculo e tenta obter o
     * resultado. Caso não seja possível executar a expressão, define o status
     * do teste para False;
     * @throws Exception Gera exceção caso a expressão seja inválida.
     */
    public final void executarCalculo() throws Exception {
        try {
            this.resultadoObtido = Calculo.realizarCalculo(this);
        } catch (IllegalArgumentException ex) {
            resultadoObtido = null;
            setStatusDoTeste();
        }
    }
    
}
