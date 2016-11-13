package br.com.caelum.dominio;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;

public class LeilaoTest {
	
	@Test
	public void naoDeveAceitarDoisLancesSeguidosDoMesmoUsuario(){
		Usuario joao = new Usuario("Joao");
		Leilao leilao = new Leilao("PS3");
		
		leilao.propoe(new Lance(joao, 400));
		leilao.propoe(new Lance(joao, 300));
		
		assertTrue(leilao.getLances().size() == 1);
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
	}
}
