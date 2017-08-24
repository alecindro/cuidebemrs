package br.com.cuidebem.rotinas;

import java.util.ArrayList;
import java.util.List;

public enum SinaisVitais{

	PRESSAOARTERIAL("Pressão Arterial"),
	FREQUENCIACARDIACA("Frequência Cardíaca"),
	TEMPERATURA("Temperatura Corporal"),
	FREQUENCIARESPIRATORIA("Frequência Respiratória"),
	SATURACAOOXIGENIO("Saturação de Oxigênio (oximetria)"),
	CONTROLEGLICEMIA("Controle da Glicemia");
	
private String descricao;
	
	
	private SinaisVitais(String descricao) {
		this.descricao = descricao;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
    
	public SinaisVitais[] getValues(){
		return SinaisVitais.values();
	}
	
	public static List<String> getDescricaos() {
		List<String> descricaos = new ArrayList<String>();
		for (SinaisVitais sinaisVitais : SinaisVitais.values()) {
			descricaos.add(sinaisVitais.getDescricao());
		}
		return descricaos;
	}
}
