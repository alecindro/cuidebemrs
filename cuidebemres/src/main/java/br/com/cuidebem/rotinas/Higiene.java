package br.com.cuidebem.rotinas;

import java.util.ArrayList;
import java.util.List;

public enum Higiene{

	BANHO("Realização de Banho"),
	ORAL("Higiene Oral");
	
	private String descricao;

	private Higiene(String descricao) {
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
		for (Higiene higiene : Higiene.values()) {
			descricaos.add(higiene.getDescricao());
		}
		return descricaos;
	}
	
}
