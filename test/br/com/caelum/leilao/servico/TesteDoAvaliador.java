package br.com.caelum.leilao.servico;

import static org.junit.Assert.*;

import org.junit.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;

public class TesteDoAvaliador {

	double delta = 0.00001;
	
	@Test
	public void deveEntenderLancesEmOrdemCrescente() {
		
		//parte 1: cen�rio
		Usuario joao = new Usuario("joao");
		Usuario jose = new Usuario("jose");
		Usuario maria = new Usuario("maria");
		
		Leilao leilao = new Leilao("PS3");
		leilao.propoe(new Lance(joao, 250));
		leilao.propoe(new Lance(maria, 300));
		leilao.propoe(new Lance(jose, 400));
		
		//parte 2: a��o
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		//parte 3: valida��o
		double maiorEsperado = 400;
		double menorEsperado = 250;

		assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), delta);
		assertEquals(menorEsperado,leiloeiro.getMenorLance(), delta);
	}
	
	@Test 
	public void deveCalcularAMedia() {
		Avaliador avaliador = new Avaliador();
		
		Usuario joao = new Usuario("joao");
		Usuario jose = new Usuario("jose");
		Usuario maria = new Usuario("maria");
		
		Leilao leilao = new Leilao("PS3");
		leilao.propoe(new Lance(joao, 100));
		leilao.propoe(new Lance(maria, 200));
		leilao.propoe(new Lance(jose, 300));
		avaliador.avalia(leilao);

		assertEquals(200.00, avaliador.getMedia(), delta);
	}
}