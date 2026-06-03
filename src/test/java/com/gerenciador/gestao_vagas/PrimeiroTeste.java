package com.gerenciador.gestao_vagas;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrimeiroTeste {

    @Test
    public void deveCalcularDoisNumeros(){
        var result = calculate(2,3);
        assertEquals(result, 5);

    }


    public static int calculate (int num1, int num2) {
        return num1 + num2;
    }


}
