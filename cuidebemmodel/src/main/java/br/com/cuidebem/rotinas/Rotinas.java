package br.com.cuidebem.rotinas;

import java.util.ArrayList;
import java.util.List;

import br.com.cuidebem.model.Evento;

public enum Rotinas {

	SINALVITAL("Sinais Vitais", SinaisVitais.getDescricaos(),SinaisVitais.values()), 
	RESPIRATORIO("Respiratório", Respiratorio.getDescricaos(),Respiratorio.values()), 
	NUTRICAO("Nutrição", Nutricao.getDescricaos(),Nutricao.values()), 
	MEDICACAO("Medicação", Patologias.getDescricaos(),Patologias.values()), 
	INTEROCORRENCIAS("Interocorrências", InterOcorrencias.getDescricaos(),InterOcorrencias.values()), 
	HIGIENE("Higiene", Higiene.getDescricaos(),Higiene.values()), 
	ELIMINACOES("Eliminações", Eliminacoes.getDescricaos(),Eliminacoes.values()), 
	DOR("Dor", Dor.getDescricaos(),Dor.values()), 
	CONSULTAS("Consultas",Consultas.getDescricaos(),Consultas.values()), 
	BEMESTAR("Bem Estar", BemEstar.getDescricaos(),BemEstar.values()),
	ATIVIDADES("Atividades",Atividades.getDescricaos(),Atividades.values());

	private String descricao;
	private List<String> subGrupoEventos;
	private IRotina[] arrayRotinas;

	public String getDescricao() {
		return descricao;
	}

	private Rotinas(String descricao, List<String> subGrupoEventos,IRotina[] arrayRotinas) {
		this.descricao = descricao;
		this.subGrupoEventos = subGrupoEventos;
		this.arrayRotinas = arrayRotinas;
	}

	public List<String> getSubGrupoEventos() {
		return subGrupoEventos;
	}

	public Rotinas[] getValues() {
		return Rotinas.values();
	}
	
	public IRotina[] getArrayRotinas() {
		return arrayRotinas;
	}

	public void setArrayRotinas(IRotina[] arrayRotinas) {
		this.arrayRotinas = arrayRotinas;
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
	
	public static String getPage(String descricao, String subgrupoEvento){
		Rotinas rotina = Rotinas.fromDescricao(descricao);
		for(IRotina iRotina : rotina.getArrayRotinas()){
			IRotina subgrupo = iRotina.fromDescricao(subgrupoEvento);
			if(subgrupo != null){
				return subgrupo.getPage();
			}
		}
		return null;
	}
	
	public static String genResumo(Evento evento){
		try{
		Rotinas rotina = Rotinas.fromDescricao(evento.getGrupoevento());
		if(rotina == null){
			return evento.getGrupoevento();
		}
		for(IRotina iRotina : rotina.getArrayRotinas()){
			if(evento.getSubgrupoevento() == null){
				return evento.getGrupoevento();
			}
			IRotina subgrupo = iRotina.fromDescricao(evento.getSubgrupoevento());
			if(subgrupo != null){
				return subgrupo.genResumo(evento);
			}
		}
		}catch(Exception e){
			System.out.println("Evento:" + evento.toString());
		}
		return "";
	}
	
	

}
