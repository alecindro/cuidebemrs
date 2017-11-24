package br.com.cuidebem.rotinas;

import java.util.ArrayList;
import java.util.List;

import br.com.cuidebem.model.Evento;

public enum Atividades implements IRotina{
	
	ARTERAPIA("Arterapia","/app/evento/atividades.xhtml")
	{public String genResumo(Evento evento){
		String result = evento.getGrupoevento().concat("<br/>");
		result = result.concat(evento.getSubgrupoevento()).concat("<br/>");
		result = result.concat(evento.getDescricao());
		if (evento.getObsevento() != null) {
			result = result.concat("<br/>").concat(evento.getObsevento());
		}
		return result;
	}
	
	},
	ESTIMULACAOCOGNITIVA("Estimulação Cognitiva","/app/evento/atividades.xhtml")
	{public String genResumo(Evento evento){
		String result = evento.getGrupoevento().concat("<br/>");
		result = result.concat(evento.getSubgrupoevento()).concat("<br/>");
		result = result.concat(evento.getDescricao());
		if (evento.getObsevento() != null) {
			result = result.concat("<br/>").concat(evento.getObsevento());
		}
		return result;
	}
	
	},
	MASSAGEM("Massagem","/app/evento/atividades.xhtml")
	{public String genResumo(Evento evento){
		String result = evento.getGrupoevento().concat("<br/>");
		result = result.concat(evento.getSubgrupoevento()).concat("<br/>");
		result = result.concat(evento.getDescricao());
		if (evento.getObsevento() != null) {
			result = result.concat("<br/>").concat(evento.getObsevento());
		}
		return result;
	}
	
	},
	DANCA("Dança","/app/evento/atividades.xhtml")
	{public String genResumo(Evento evento){
		String result = evento.getGrupoevento().concat("<br/>");
		result = result.concat(evento.getSubgrupoevento()).concat("<br/>");
		result = result.concat(evento.getDescricao());
		if (evento.getObsevento() != null) {
			result = result.concat("<br/>").concat(evento.getObsevento());
		}
		return result;
	}
	
	},
	ATIVIDADEFISICA("Atividade Física","/app/evento/atividades.xhtml")
	{public String genResumo(Evento evento){
		String result = evento.getGrupoevento().concat("<br/>");
		result = result.concat(evento.getSubgrupoevento()).concat("<br/>");
		result = result.concat(evento.getDescricao());
		if (evento.getObsevento() != null) {
			result = result.concat("<br/>").concat(evento.getObsevento());
		}
		return result;
	}
	
	},
	ATIVIDADESLUDICAS("Atividades Lúdicas","/app/evento/atividades.xhtml")
	{public String genResumo(Evento evento){
		String result = evento.getGrupoevento().concat("<br/>");
		result = result.concat(evento.getSubgrupoevento()).concat("<br/>");
		result = result.concat(evento.getDescricao());
		if (evento.getObsevento() != null) {
			result = result.concat("<br/>").concat(evento.getObsevento());
		}
		return result;
	}
	
	},
	OUTRAS("Outras","/app/evento/atividades.xhtml")
	{public String genResumo(Evento evento){
		String result = evento.getGrupoevento().concat("<br/>");
		result = result.concat(evento.getSubgrupoevento()).concat("<br/>");
		result = result.concat(evento.getDescricao());
		if (evento.getObsevento() != null) {
			result = result.concat("<br/>").concat(evento.getObsevento());
		}
		return result;
	}

	};
	
	private String descricao;
	private String page;
	
	
	

	private Atividades(String descricao, String page) {
		this.descricao = descricao;
		this.page = page;
	}
	
	public String getDescricao() {
		return descricao;
	}

	@Override
	public String getPage() {
		return page;
	}

	@Override
	public IRotina fromDescricao(String descricao) {
		for (Atividades atividades : Atividades.values()) {
			if (atividades.getDescricao().equals(descricao)) {
				return atividades;
			}
		}
		return null;
	}
	
	public static List<String> getDescricaos() {
		List<String> descricaos = new ArrayList<String>();
		for (Atividades atividades : Atividades.values()) {
			descricaos.add(atividades.getDescricao());
		}
		return descricaos;
	}

	
}
