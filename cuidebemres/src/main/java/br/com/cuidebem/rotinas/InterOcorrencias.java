package br.com.cuidebem.rotinas;

import java.util.ArrayList;
import java.util.List;

public enum InterOcorrencias{

	INTEROCORRENCIAS("Interocorrências");
	
	private String descricao;
	
	private InterOcorrencias(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public static List<String> getDescricaos() {
		List<String> descricaos = new ArrayList<String>();
		for (InterOcorrencias interOcorrencias : InterOcorrencias.values()) {
			descricaos.add(interOcorrencias.getDescricao());
		}
		return descricaos;
	}

}
