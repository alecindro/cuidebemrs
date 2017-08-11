package br.com.cuidebem.rotinas;

import java.util.ArrayList;
import java.util.List;

public enum Consultas{

	CONSULTA("Consultas de Saúde");
	
private String descricao;

	
	private Consultas(String descricao) {
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
		for (Consultas consultas : Consultas.values()) {
			descricaos.add(consultas.getDescricao());
		}
		return descricaos;
	}

}
