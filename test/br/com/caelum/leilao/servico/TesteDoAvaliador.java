package br.com.caelum.leilao.servico;

import static org.junit.Assert.*;

import org.junit.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;

public class TesteDoAvaliador {

	private static double DELTA = 0.00001;
	
	@Test
	public void deveEntenderLancesEmOrdemCrescente() {
		
		//parte 1: cenerio
		Usuario joao = new Usuario("joao");
		Usuario jose = new Usuario("jose");
		Usuario maria = new Usuario("maria");
		
		Leilao leilao = new Leilao("PS3");
		leilao.propoe(new Lance(joao, 250));
		leilao.propoe(new Lance(maria, 300));
		leilao.propoe(new Lance(jose, 400));
		
		//parte 2: acao
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		//parte 3: validacao
		double maiorEsperado = 400;
		double menorEsperado = 250;

		assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), DELTA);
		assertEquals(menorEsperado,leiloeiro.getMenorLance(), DELTA);
	}
	
	@Test
	public void deveEntenderLeilaoComUmLance(){
		Avaliador avaliador = new Avaliador();
		Usuario joao = new Usuario("joao");
		Leilao leilao = new Leilao("PS3");
		leilao.propoe(new Lance(joao, 200));
		avaliador.avalia(leilao);
		assertEquals(200.00, avaliador.getMaiorLance(), DELTA);
		assertEquals(200.00, avaliador.getMenorLance(), DELTA);
	}
	
	@Test
	public void deveEntenderLancesDeFormaAleatoria(){
		Avaliador avaliador = new Avaliador();
		Usuario joao = new Usuario("Joao");
		Usuario maria = new Usuario("Maria");
		Leilao leilao = new Leilao("PS3");
		
		leilao.propoe(new Lance(joao, 200));
		leilao.propoe(new Lance(maria, 700));
		leilao.propoe(new Lance(joao, 450));
		leilao.propoe(new Lance(maria, 630));
		leilao.propoe(new Lance(joao, 120));
		leilao.propoe(new Lance(maria, 230));

		avaliador.avalia(leilao);
		
		assertEquals(700.00, avaliador.getMaiorLance(), DELTA);
		assertEquals(120.00, avaliador.getMenorLance(), DELTA);
	}
	
	
	@Test
	public void deveEntenderLancesEmOrdemDecrescente(){
		Avaliador avaliador = new Avaliador();
		Usuario joao = new Usuario("Joao");
		Usuario maria = new Usuario("Maria");
		Leilao leilao = new Leilao("PS3");
		
		leilao.propoe(new Lance(joao, 400));
		leilao.propoe(new Lance(maria, 350));
		leilao.propoe(new Lance(joao, 300));
		leilao.propoe(new Lance(maria, 250));
		leilao.propoe(new Lance(joao, 100));
		
		avaliador.avalia(leilao);
		
		assertEquals(400.00, avaliador.getMaiorLance(), DELTA);
		assertEquals(100.00, avaliador.getMenorLance(), DELTA);
	}
	
	@Test
	public void deveEncontrarTresMaioresLances(){
		Avaliador avaliador = new Avaliador();
		Usuario joao = new Usuario("Joao");
		Usuario maria = new Usuario("Maria");
		Leilao leilao = new Leilao("PS3");
		
		leilao.propoe(new Lance(joao, 400));
		leilao.propoe(new Lance(maria, 300));
		leilao.propoe(new Lance(joao, 100));
		leilao.propoe(new Lance(maria, 700));
		leilao.propoe(new Lance(joao, 850));
		
		avaliador.avalia(leilao);
		
		assertEquals(850.00, avaliador.getTresMaiores().get(0).getValor(), DELTA);
		assertEquals(700.00, avaliador.getTresMaiores().get(1).getValor(), DELTA);
		assertEquals(400.00, avaliador.getTresMaiores().get(2).getValor(), DELTA);
	}
	
	@Test
	public void deveDevolverTodosLancesCasoNaoHajaNoMinimo3(){
		Avaliador avaliador = new Avaliador();
		Usuario joao = new Usuario("Joao");
		Usuario maria = new Usuario("Maria");
		Leilao leilao = new Leilao("PS3");
		
		leilao.propoe(new Lance(joao, 400));
		leilao.propoe(new Lance(maria, 300));
		
		avaliador.avalia(leilao);
		
		assertEquals(2, avaliador.getTresMaiores().size());
		assertEquals(400.00, avaliador.getTresMaiores().get(0).getValor(), DELTA);
		assertEquals(300.00, avaliador.getTresMaiores().get(1).getValor(), DELTA);
	}
		
	@Test
	public void deveEntenderLeilaoSemLances(){
		Avaliador avaliador = new Avaliador();
		Leilao leilao = new Leilao("PS3");
		
		avaliador.avalia(leilao);
		
		assertTrue(avaliador.getTresMaiores().size() == 0);
	}
}
