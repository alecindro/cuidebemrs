package br.com.cuidebem.rotinas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Nutricao{

	CAFE("Café da manhã",Arrays.asList("Aceitou bem","Aceitou parcialmente","Não aceitou")),
	ALMOCO("Almoço",Arrays.asList("Aceitou bem","Aceitou parcialmente","Não aceitou")),
	JANTAR("Jantar",Arrays.asList("Aceitou bem","Aceitou parcialmente","Não aceitou")),
	CEIA("Ceia",Arrays.asList("Aceitou bem","Aceitou parcialmente","Não aceitou")),
	LANCHE("Lanche",Arrays.asList("Aceitou bem","Aceitou parcialmente","Não aceitou")),
	LIQUIDOS("Ingestão de Líquidos",Arrays.asList("Pouco","Moderada","Grande"));
	
	private String descricao;
	private List<String> opcoes;
	
	private Nutricao(String descricao, List<String> opcoes) {
		this.descricao = descricao;
		this.opcoes = opcoes;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<String> getOpcoes() {
		return opcoes;
	}

	public void setOpcoes(List<String> opcoes) {
		this.opcoes = opcoes;
	}
	
	public static List<String> getDescricaos() {
		List<String> descricaos = new ArrayList<String>();
		for (Nutricao nutricao : Nutricao.values()) {
			descricaos.add(nutricao.getDescricao());
		}
		return descricaos;
	}
	
}
