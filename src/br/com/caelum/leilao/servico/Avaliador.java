package br.com.caelum.leilao.servico;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;

public class Avaliador {

	private double maiorLance = Double.NEGATIVE_INFINITY;
	private double menorLance = Double.POSITIVE_INFINITY;
	private double media = 0; 
	
	public void avalia(Leilao leilao){
		double total = 0;
		for (Lance lance : leilao.getLances()) {
			if (lance.getValor() > maiorLance) {
				maiorLance = lance.getValor();
			}
			if (lance.getValor() < menorLance ) {
				menorLance = lance.getValor();
			}
			total += lance.getValor();
		}
		int totalLances = leilao.getLances().size();
		
		if(totalLances > 0)
			media = total/totalLances;
	}
	
	public double getMaiorLance() {
		return maiorLance;
	}
	
	public double getMenorLance() {
		return menorLance;
	}
	
	public double getMedia(){
		return media;
	}
}
