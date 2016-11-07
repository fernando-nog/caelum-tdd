package br.com.caelum.desafio;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PalindromoTest {

	@Test
	public void deveValidarQueNaoEPalindromo(){
		assertFalse(Palindromo.ehPalindromo("Anotaram minha marotona"));
	}
	
	@Test
    public void deveIdentificarPalindromoEFiltrarCaracteresInvalidos() {
        assertTrue(Palindromo.ehPalindromo("Socorram-me subi no onibus em Marrocos"));
    }

    @Test
    public void deveIdentificarPalindromo() {
        assertTrue(Palindromo.ehPalindromo("Anotaram a data da maratona"));
    }

    @Test
    public void deveIdentificarSeNaoEhPalindromo() {
        assertFalse(Palindromo.ehPalindromo("E preciso amar as pessoas como se nao houvesse amanha"));
    }
}