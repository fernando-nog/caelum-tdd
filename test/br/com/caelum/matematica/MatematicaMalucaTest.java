package br.com.caelum.matematica;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MatematicaMalucaTest {

    @Test
    public void deveMultiplicarNumerosMaioresQue30() {
        assertEquals(40*4, MatematicaMaluca.contaMaluca(40));
    }

    @Test
    public void deveMultiplicarNumerosMaioresQue10EMenoresQue30() {
        assertEquals(20*3, MatematicaMaluca.contaMaluca(20));
    }

    @Test
    public void deveMultiplicarNumerosMenoresQue10() {
        assertEquals(2*2, MatematicaMaluca.contaMaluca(2));
    }
}
