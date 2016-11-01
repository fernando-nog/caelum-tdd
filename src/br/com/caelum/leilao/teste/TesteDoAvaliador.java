package br.com.caelum.leilao.teste;

import static org.junit.Assert.*;

import org.junit.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;

public class TesteDoAvaliador {

	@Test
	public void deveEntenderLancesEmOrdemCrescente() {
		
		//parte 1: cenário
		Usuario joao = new Usuario("joao");
		Usuario jose = new Usuario("jose");
		Usuario maria = new Usuario("maria");
		
		Leilao leilao = new Leilao("PS3");
		leilao.propoe(new Lance(joao, 250));
		leilao.propoe(new Lance(maria, 300));
		leilao.propoe(new Lance(jose, 400));
		
		//parte 2: ação
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		//parte 3: validação
		double maiorEsperado = 400;
		double menorEsperado = 250;
		double delta = 0.00001;

		assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), delta);
		assertEquals(menorEsperado,leiloeiro.getMenorLance(), delta);
	}
}
