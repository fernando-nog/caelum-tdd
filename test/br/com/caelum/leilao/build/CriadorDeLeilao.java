package br.com.caelum.leilao.build;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;

//Test Data Builder - padrão de projeto para código de testes.
public class CriadorDeLeilao {

	private Leilao leilao;
	
	public CriadorDeLeilao(){};
	
	public CriadorDeLeilao para(String descricao) {
        this.leilao = new Leilao(descricao);
        return this;
    }

    public CriadorDeLeilao lance(Usuario usuario, double valor) {
        this.leilao.propoe(new Lance(usuario, valor));
        return this;
    }

    public Leilao constroi() { 
        return this.leilao;
    }
}
