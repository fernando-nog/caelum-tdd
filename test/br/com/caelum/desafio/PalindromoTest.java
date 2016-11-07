package br.com.caelum.desafio;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PalindromoTest {

	@Test
	public void deveValidarQueEUmPalindromo(){
		assertTrue(Palindromo.ehPalindromo("Socorram-me subi no onibus em Marrocos"));
		assertTrue(Palindromo.ehPalindromo("Anotaram a data da maratona"));
		assertTrue(Palindromo.ehPalindromo("reviver"));
		assertTrue(Palindromo.ehPalindromo("raiar"));
	}
	
	@Test
	public void deveValidarQueNaoEPalindromo(){
		assertFalse(Palindromo.ehPalindromo("Anotaram minha marotona"));
	}
}