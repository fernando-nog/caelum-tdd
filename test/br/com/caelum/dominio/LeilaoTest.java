package br.com.caelum.dominio;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.leilao.build.CriadorDeLeilao;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;

public class LeilaoTest {
	
	private static double DELTA = 0.00001;
	private Usuario joao;
	private Usuario mario; 
    
	@Before
	public void criaUsuarios(){
		this.joao = new Usuario("João");
		this.mario = new Usuario("Mario");
	}
	
	@Test
	public void naoDeveAceitarDoisLancesSeguidosDoMesmoUsuario(){
		Leilao leilao =  new CriadorDeLeilao().para("PS3")
									.lance(joao, 400)
									.lance(joao, 300)
									.constroi();
		
		assertTrue(leilao.getLances().size() == 1);
		assertEquals(400.00,leilao.getUltimoLance().getValor(), DELTA);
	}

	@Test 
	public void naoDeveAceitarMaisDoQue5LancesDeUmMesmoUsuario(){
		Leilao leilao =  new CriadorDeLeilao().para("PS3")
									.lance(joao, 400)
									.lance(mario, 200)
									.lance(joao, 600)
									.lance(mario, 500)
									.lance(joao, 350)
									.lance(mario, 700)
									.lance(joao, 360)
									.lance(mario, 260)
									.lance(joao, 390)
									.lance(mario, 360)
									.lance(joao, 590)
									.constroi();
		
		assertTrue(leilao.getLances().size() == 10);
		assertEquals(360,leilao.getUltimoLance().getValor(), DELTA);
	}
	
	@Test
	public void deveDobrarUltimoLanceDadoPorUmUsuario(){
		Leilao leilao =  new CriadorDeLeilao().para("PS3")
									 .lance(joao, 400)
									 .lance(mario, 200)
									 .constroi();
							
		leilao.dobraLance(joao);

		assertTrue(leilao.getLances().size() == 3);
		assertEquals(800,leilao.getUltimoLance().getValor(), DELTA);
	}
	
	@Test
	public void naoDeveCriarLanceDobradoSemUmLanceAnteriorDoUsuario(){
		Leilao leilao =  new CriadorDeLeilao().para("PS3")
				 .lance(joao, 400)
				 .constroi();
		
		leilao.dobraLance(mario);

		assertTrue(leilao.getLances().size() == 1);
		assertEquals(400,leilao.getUltimoLance().getValor(), DELTA);
	}
}
