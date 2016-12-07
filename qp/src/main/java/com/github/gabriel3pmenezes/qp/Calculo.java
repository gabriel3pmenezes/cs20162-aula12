/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */
package com.github.gabriel3pmenezes.qp;

import com.github.kyriosdata.parser.Lexer;
import com.github.kyriosdata.parser.Parser;
import com.github.kyriosdata.parser.Token;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Calculo {

    /**
     * Método que faz o cálculo da expressão da linha de teste
     * @param linha Linha de teste contendo no a expressão matemática
     * @return Float Valor obtido após o cálculo.
     * @throws Exception Exceção gerada quando a expressão é incorreta.
     */
    public static float realizarCalculo(Teste linha) throws Exception {
        if (linha.getAtributo().isEmpty()) {
            float resultado = 0;
            List<Token> tokens = new Lexer(linha.getExpressao()).tokenize();
            try {
                Parser parser = new Parser(tokens);
                resultado = parser.expressao().valor();
            } catch(IllegalArgumentException ex) {
                throw new Exception();
            }

            return resultado;
        } else {
            Map<String, Float> contexto = new HashMap<>();
            for (int i = 0; i < linha.getNomeAtributo().length; i++) {
                contexto.put(
                        linha.getNomeAtributo()[i],
                        linha.getValorAtributo()[i]);
            }

            float resultadoFinal = 0;
            List<Token> tokens = new Lexer(linha.getExpressao()).tokenize();
            try {
                Parser parser = new Parser(tokens);
                resultadoFinal = parser.expressao().valor(contexto);
            } catch(IllegalArgumentException ex) {
                throw new Exception();
            }

            return resultadoFinal;
        }
    }

}
