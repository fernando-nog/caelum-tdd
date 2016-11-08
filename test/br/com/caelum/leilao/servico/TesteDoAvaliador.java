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
	
	@Test
	public void deveEntenderLeilaoComUmLance(){
		Avaliador avaliador = new Avaliador();
		Usuario joao = new Usuario("joao");
		Leilao leilao = new Leilao("PS3");
		leilao.propoe(new Lance(joao, 200));
		avaliador.avalia(leilao);
		assertEquals(200.00, avaliador.getMaiorLance(), delta);
		assertEquals(200.00, avaliador.getMenorLance(), delta);
		assertEquals(200.00, avaliador.getMedia(), delta);
	}
	
	@Test
	public void deveEntenderLancesDeFormaAleatoria(){
		Avaliador avaliador = new Avaliador();
		Usuario joao = new Usuario("Joao");
		Usuario maria = new Usuario("Maria");
		Leilao leilao = new Leilao("PS3");
		
		leilao.propoe(new Lance(joao, 200));
		leilao.propoe(new Lance(joao, 450));
		leilao.propoe(new Lance(joao, 120));
		
		leilao.propoe(new Lance(maria, 700));
		leilao.propoe(new Lance(maria, 630));
		leilao.propoe(new Lance(maria, 230));

		avaliador.avalia(leilao);
		
		assertEquals(700.00, avaliador.getMaiorLance(), delta);
		assertEquals(120.00, avaliador.getMenorLance(), delta);
	}
	
	
	@Test
	public void deveEntenderLancesEmOrdemDecrescente(){
		Avaliador avaliador = new Avaliador();
		Usuario joao = new Usuario("Joao");
		Leilao leilao = new Leilao("PS3");
		
		leilao.propoe(new Lance(joao, 400));
		leilao.propoe(new Lance(joao, 300));
		leilao.propoe(new Lance(joao, 100));
		
		avaliador.avalia(leilao);
		
		assertEquals(400.00, avaliador.getMaiorLance(), delta);
		assertEquals(100.00, avaliador.getMenorLance(), delta);
	}
	
	//TODO impelementar seguintes testes:
//    Um leilão com 5 lances, deve encontrar os três maiores
//    Um leilão com 2 lances, deve devolver apenas os dois lances que encontrou
//    Um leilão sem nenhum lance, devolve lista vazia
}
