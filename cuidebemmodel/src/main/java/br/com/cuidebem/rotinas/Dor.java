package br.com.cuidebem.rotinas;

import java.util.ArrayList;
import java.util.List;

public enum Dor{

	DOR("Dor");
	
	private String descricao;

	
	private Dor(String descricao) {
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
		for (Dor dor : Dor.values()) {
			descricaos.add(dor.getDescricao());
		}
		return descricaos;
	}
	
}
