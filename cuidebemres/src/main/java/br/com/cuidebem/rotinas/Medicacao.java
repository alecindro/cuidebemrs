package br.com.cuidebem.rotinas;

import java.util.ArrayList;
import java.util.List;

public enum Medicacao{

	MEDICAO("Medicações");
	
	private String descricao;

	
	private Medicacao(String descricao) {
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
		for (Medicacao medicacao : Medicacao.values()) {
			descricaos.add(medicacao.getDescricao());
		}
		return descricaos;
	}
}
