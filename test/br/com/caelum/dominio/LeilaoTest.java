package br.com.caelum.dominio;

import static org.junit.Assert.*;

import org.junit.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;

public class LeilaoTest {
	
	private static double DELTA = 0.00001;
	
	@Test
	public void naoDeveAceitarDoisLancesSeguidosDoMesmoUsuario(){
		Usuario joao = new Usuario("Joao");
		Leilao leilao = new Leilao("PS3");
		
		leilao.propoe(new Lance(joao, 400));
		leilao.propoe(new Lance(joao, 300));
		
		assertTrue(leilao.getLances().size() == 1);
		assertEquals(400.00,leilao.getUltimoLance().getValor(), DELTA);
	}

	@Test 
	public void naoDeveAceitarMaisDoQue5LancesDeUmMesmoUsuario(){
		Usuario joao = new Usuario("Joao");
		Usuario mario = new Usuario("Mario");
		Leilao leilao = new Leilao("PS3");
		
		leilao.propoe(new Lance(joao, 400));
		leilao.propoe(new Lance(mario, 200));
		leilao.propoe(new Lance(joao, 600));
		leilao.propoe(new Lance(mario, 500));
		leilao.propoe(new Lance(joao, 350));
		leilao.propoe(new Lance(mario, 700));
		leilao.propoe(new Lance(joao, 360));
		leilao.propoe(new Lance(mario, 260));
		leilao.propoe(new Lance(joao, 390));
		leilao.propoe(new Lance(mario, 360));
		leilao.propoe(new Lance(joao, 590));
		
		assertTrue(leilao.getLances().size() == 10);
		assertEquals(360,leilao.getUltimoLance().getValor(), DELTA);
	}
	
	@Test
	public void deveDobrarUltimoLanceDadoPorUmUsuario(){
		Usuario joao = new Usuario("Joao");
		Usuario mario = new Usuario("Mario");
		Leilao leilao = new Leilao("PS3");
		
		leilao.propoe(new Lance(joao, 400));
		leilao.propoe(new Lance(mario, 200));
		
		leilao.dobraLance(joao);

		assertTrue(leilao.getLances().size() == 3);
		assertEquals(800,leilao.getUltimoLance().getValor(), DELTA);
	}
	
	@Test
	public void naoDeveCriarLanceDobradoSemUmLanceAnteriorDoUsuario(){
		Usuario joao = new Usuario("Joao");
		Usuario mario = new Usuario("Mario");
		Leilao leilao = new Leilao("PS3");
		
		leilao.propoe(new Lance(joao, 400));
		
		leilao.dobraLance(mario);

		assertTrue(leilao.getLances().size() == 1);
		assertEquals(400,leilao.getUltimoLance().getValor(), DELTA);
	}
}
