package br.com.cuidebem.rotinas;

import java.util.ArrayList;
import java.util.List;

public enum Rotinas {

	SINALVITAL("Sinais Vitais", SinaisVitais.getDescricaos()), 
	RESPIRATORIO("Respiratório", Respiratorio.getDescricaos()), 
	NUTRICAO("Nutrição", Nutricao.getDescricaos()), 
	MEDICACAO("Medicação", Patologias.getDescricaos()), 
	INTEROCORRENCIAS("Interocorrências", InterOcorrencias.getDescricaos()), 
	HIGIENE("Higiene", Higiene.getDescricaos()), 
	ELIMINACOES("Eliminações", Eliminacoes.getDescricaos()), 
	DOR("Dor", Dor.getDescricaos()), 
	CONSULTAS("Consultas",Consultas.getDescricaos()), 
	BEMESTAR("Bem Estar", BemEstar.getDescricaos());

	private String descricao;
	private List<String> subGrupoEventos;

	public String getDescricao() {
		return descricao;
	}

	private Rotinas(String descricao, List<String> subGrupoEventos) {
		this.descricao = descricao;
		this.subGrupoEventos = subGrupoEventos;
	}

	public List<String> getSubGrupoEventos() {
		return subGrupoEventos;
	}

	public Rotinas[] getValues() {
		return Rotinas.values();
	}

	public static List<String> getGrupoEventos() {
		List<String> descricaos = new ArrayList<String>();
		for (Rotinas rotinas : Rotinas.values()) {
			descricaos.add(rotinas.getDescricao());
		}
		return descricaos;
	}
	
	public static List<String> getSubGrupoEventos(String descricao) {
		return fromDescricao(descricao).getSubGrupoEventos();
	}
	
	public static Rotinas fromDescricao(String descricao) {
		for (Rotinas rotinas : Rotinas.values()) {
			if (rotinas.getDescricao().equals(descricao)) {
				return rotinas;
			}
		}
		return null;
	}
	
	

}
