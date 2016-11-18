package br.com.caelum.leilao.servico;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.leilao.build.CriadorDeLeilao;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;

public class TesteDoAvaliador {

	private static double DELTA = 0.00001;
	
	private Avaliador leiloeiro;
	private Usuario joao;
    private Usuario jose;
    private Usuario maria;

	
	@Before
	public void criaAvaliador(){
		this.leiloeiro = new Avaliador();
		this.joao = new Usuario("João");
		this.maria = new Usuario("Maria");
		this.jose = new Usuario("Jose");
	}
	
	@Test
	public void deveEntenderLancesEmOrdemCrescente() {
		double maiorEsperado = 400;
		double menorEsperado = 250;
		
		//parte 1: cenario
		Leilao leilao =  new CriadorDeLeilao().para("PS3")
									.lance(joao, menorEsperado)
									.lance(maria, 300)
									.lance(jose, maiorEsperado)
									.constroi();
		
		//parte 2: acao
		leiloeiro.avalia(leilao);
		
		//parte 3: validacao
		assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), DELTA);
		assertEquals(menorEsperado, leiloeiro.getMenorLance(), DELTA);
	}
	
	@Test
	public void deveEntenderLeilaoComUmLance(){
		Leilao leilao =  new CriadorDeLeilao().para("PS3")
									.lance(joao, 200)
									.constroi();
		
		leiloeiro.avalia(leilao);
		assertEquals(200.00, leiloeiro.getMaiorLance(), DELTA);
		assertEquals(200.00, leiloeiro.getMenorLance(), DELTA);
	}
	
	@Test
	public void deveEntenderLancesDeFormaAleatoria(){
		double maiorEsperado = 700;
		double menorEsperado = 120;
		Leilao leilao =  new CriadorDeLeilao().para("PS3")
									.lance(joao, 200)
									.lance(maria, maiorEsperado)
									.lance(joao, 450)
									.lance(maria, 630)
									.lance(joao, menorEsperado)
									.lance(maria, 230)
									.constroi();
		
		leiloeiro.avalia(leilao);
		
		assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), DELTA);
		assertEquals(menorEsperado, leiloeiro.getMenorLance(), DELTA);
	}
	
	
	@Test
	public void deveEntenderLancesEmOrdemDecrescente(){
		double maiorEsperado = 400;
		double menorEsperado = 100;
		Leilao leilao =  new CriadorDeLeilao().para("PS3")
									.lance(joao, maiorEsperado)
									.lance(maria, 350)
									.lance(joao, 300)
									.lance(maria, menorEsperado)
									.constroi();
		
		leiloeiro.avalia(leilao);
		
		assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), DELTA);
		assertEquals(menorEsperado, leiloeiro.getMenorLance(), DELTA);
	}
	
	@Test
	public void deveEncontrarTresMaioresLances(){
		Leilao leilao =  new CriadorDeLeilao().para("PS3")
									.lance(joao, 400)
									.lance(maria, 300)
									.lance(joao, 100)
									.lance(maria, 700)
									.lance(joao, 850)
									.constroi();
		
		leiloeiro.avalia(leilao);
		
		assertEquals(3, leiloeiro.getTresMaiores().size());
		assertEquals(850.00, leiloeiro.getTresMaiores().get(0).getValor(), DELTA);
		assertEquals(700.00, leiloeiro.getTresMaiores().get(1).getValor(), DELTA);
		assertEquals(400.00, leiloeiro.getTresMaiores().get(2).getValor(), DELTA);
	}
	
	@Test
	public void deveDevolverTodosLancesCasoNaoHajaNoMinimo3(){
		Leilao leilao =  new CriadorDeLeilao().para("PS3")
				.lance(joao, 400)
				.lance(maria, 300)
				.constroi();
		
		leiloeiro.avalia(leilao);
		
		assertEquals(2, leiloeiro.getTresMaiores().size());
		assertEquals(400.00, leiloeiro.getTresMaiores().get(0).getValor(), DELTA);
		assertEquals(300.00, leiloeiro.getTresMaiores().get(1).getValor(), DELTA);
	}
		
	@Test
	public void deveEntenderLeilaoSemLances(){
		Leilao leilao =  new CriadorDeLeilao().para("PS3").constroi();
		
		leiloeiro.avalia(leilao);
		
		assertTrue(leiloeiro.getTresMaiores().size() == 0);
	}
}
