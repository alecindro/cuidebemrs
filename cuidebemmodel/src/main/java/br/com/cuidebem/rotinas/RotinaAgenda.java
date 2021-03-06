package br.com.cuidebem.rotinas;

import java.util.ArrayList;
import java.util.List;

import br.com.cuidebem.model.Evento;

public enum RotinaAgenda {

	SINALVITAL("Sinais Vitais", SinaisVitais.getDescricaos(),SinaisVitais.values()), 
	RESPIRATORIO("Respiratório", Respiratorio.getDescricaos(),Respiratorio.values()), 
	NUTRICAO("Nutrição", Nutricao.getDescricaos(),Nutricao.values()), 
	MEDICACAO("Medicação", Patologias.getDescricaos(),Patologias.values()), 
	HIGIENE("Higiene", Higiene.getDescricaos(),Higiene.values()), 
	CONSULTAS("Consultas",Consultas.getDescricaos(),Consultas.values()), 
	BEMESTAR("Bem Estar", BemEstar.getDescricaos(),BemEstar.values()),
	ATIVIDADES("Atividades",Atividades.getDescricaos(),Atividades.values());

	private String descricao;
	private List<String> subGrupoEventos;
	private IRotina[] arrayRotinas;

	public String getDescricao() {
		return descricao;
	}

	private RotinaAgenda(String descricao, List<String> subGrupoEventos,IRotina[] arrayRotinas) {
		this.descricao = descricao;
		this.subGrupoEventos = subGrupoEventos;
		this.arrayRotinas = arrayRotinas;
	}

	public List<String> getSubGrupoEventos() {
		return subGrupoEventos;
	}

	public RotinaAgenda[] getValues() {
		return RotinaAgenda.values();
	}
	
	public IRotina[] getArrayRotinas() {
		return arrayRotinas;
	}

	public void setArrayRotinas(IRotina[] arrayRotinas) {
		this.arrayRotinas = arrayRotinas;
	}

	public static List<String> getGrupoEventos() {
		List<String> descricaos = new ArrayList<String>();
		for (RotinaAgenda rotinas : RotinaAgenda.values()) {
			descricaos.add(rotinas.getDescricao());
		}
		return descricaos;
	}
	
	public static List<String> getSubGrupoEventos(String descricao) {
		return fromDescricao(descricao).getSubGrupoEventos();
	}
	
	public static RotinaAgenda fromDescricao(String descricao) {
		for (RotinaAgenda rotinas : RotinaAgenda.values()) {
			if (rotinas.getDescricao().equals(descricao)) {
				return rotinas;
			}
		}
		return null;
	}
	
	public static String getPage(String descricao, String subgrupoEvento){
		RotinaAgenda rotina = RotinaAgenda.fromDescricao(descricao);
		for(IRotina iRotina : rotina.getArrayRotinas()){
			IRotina subgrupo = iRotina.fromDescricao(subgrupoEvento);
			if(subgrupo != null){
				return subgrupo.getPage();
			}
		}
		return null;
	}
	
	public static void genResumo(Evento evento){
		RotinaAgenda rotina = RotinaAgenda.fromDescricao(evento.getGrupoevento());
		for(IRotina iRotina : rotina.getArrayRotinas()){
			IRotina subgrupo = iRotina.fromDescricao(evento.getSubgrupoevento());
			if(subgrupo != null){
				subgrupo.genResumo(evento);
			}
		}
		
	}
	
	

}

