package br.com.caelum.desafio;

import static org.junit.Assert.*;

import org.junit.Test;

public class AnoBissextoTest {

	@Test
	public void deveVerificarAnoBissexto(){
		assertTrue(AnoBissexto.ehBissexto(2000));
	}
	
	@Test
	public void deveValidarUmAnoQueNaoEBissexto(){
		assertFalse(AnoBissexto.ehBissexto(2017));		
	}
}
