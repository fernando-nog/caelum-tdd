package br.com.caelum.leilao.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Leilao {

	private String descricao;
	private List<Lance> lances;
	
	public Leilao(String descricao) {
		this.descricao = descricao;
		this.lances = new ArrayList<Lance>();
	}
	
	public void propoe(Lance lance) {
		if(!heUmLanceSeguido(lance) && !jaPossuiCincoLances(lance.getUsuario()))
			lances.add(lance);
	}

	public String getDescricao() {
		return descricao;
	}

	public List<Lance> getLances() {
		return Collections.unmodifiableList(lances);
	}
	
	public Lance getUltimoLance(){
		if(getLances().size() >= 1)
			return getLances().get( getLances().size() - 1);
		else 
			return null;
	}
	
	private boolean heUmLanceSeguido(Lance novoLance){
		if(getUltimoLance() != null)
			return novoLance.getUsuario().equals(getUltimoLance().getUsuario());
		else
			return false;
	}
	
	private boolean jaPossuiCincoLances(Usuario usuario){
		int qtdLances = 0;
		for (Lance lance : lances) {
			if (usuario.equals(lance.getUsuario())) {
				qtdLances += 1;
			}
		}
		
		if(qtdLances < 5)
			return false;
		else
			return true;
	}
	
	public void dobraLance(Usuario usuario){
		Lance ultimoLancePorUsuario = getUltimoLancePorUsuario(usuario);
		
		if(ultimoLancePorUsuario != null){
			propoe( new Lance(usuario, ultimoLancePorUsuario.getValor() * 2));
		}
	}
	
	private Lance getUltimoLancePorUsuario(Usuario usuario){
		Lance ultimoLancePorUsuario = null;
		
		for (int i = lances.size() - 1; i >= 0; i--) {
			if(lances.get(i).getUsuario().equals(usuario)){
				ultimoLancePorUsuario = lances.get(i);
				break;
			}
		}
		return ultimoLancePorUsuario;
	}
}
