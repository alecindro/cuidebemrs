package br.com.cuidebem.rotinas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.cuidebem.model.Evento;

public enum BemEstar implements IRotina {

	ACORDAR("Sono - Acordou", Arrays.asList("Dormiu bem", "Agitou", "Insônia"), "/app/evento/acordar.xhtml") {
		public String genResumo(Evento evento) {
			String result = evento.getGrupoevento().concat("<br/>");
			result = result.concat(evento.getSubgrupoevento()).concat("<br/>");
			result = result.concat(evento.getDescricao());
			if (evento.getObsevento() != null) {
				result = result.concat("<br/>").concat(evento.getObsevento());
			}
			return result;
		}

	},
	DORMIR("Sono - Dormiu", Arrays.asList("Normal", "A base de remédios"), "/app/evento/dormir.xhtml") {
		public String genResumo(Evento evento) {
			String result = evento.getGrupoevento().concat("<br/>");
			result = result.concat(evento.getSubgrupoevento()).concat("<br/>");
			result = result.concat(evento.getDescricao());
			if (evento.getObsevento() != null) {
				result = result.concat("<br/>").concat(evento.getObsevento());
			}
			return result;
		}
		
	},
	PASSEIOS("Passeios", Arrays.asList("Iniciou", "Retornou"), "/app/evento/passeios.xhtml") {
		public String genResumo(Evento evento) {
			String result = evento.getGrupoevento().concat("<br/>");
			result = result.concat(evento.getSubgrupoevento()).concat("<br/>");
			result = result.concat(evento.getDescricao());
			if (evento.getObsevento() != null) {
				result = result.concat("<br/>").concat(evento.getObsevento());
			}
			return result;
		}
		
	},
	CONSCIENCIA("Nível de Consciência", Arrays.asList("Acordado", "Sonolento", "Irresponsivo"),
			"/app/evento/consciencia.xhtml") {
		public String genResumo(Evento evento) {
			String result = evento.getGrupoevento().concat("<br/>");
			result = result.concat(evento.getSubgrupoevento()).concat("<br/>");
			result = result.concat(evento.getDescricao());
			if (evento.getObsevento() != null) {
				result = result.concat("<br/>").concat(evento.getObsevento());
			}
			return result;
		}
		
	},
	HUMOR("Humor", Arrays.asList("Alegre", "Calmo", "Apático", "Depressivo", "Agressivo", "Agitado"),
			"/app/evento/humor.xhtml") {
		public String genResumo(Evento evento) {
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
	private List<String> opcoes;
	private String page;


	private BemEstar(String descricao, List<String> opcoes, String page) {
		this.descricao = descricao;
		this.opcoes = opcoes;
		this.page = page;
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
		for (BemEstar bemEstar : BemEstar.values()) {
			descricaos.add(bemEstar.getDescricao());
		}
		return descricaos;
	}

	@Override
	public IRotina fromDescricao(String descricao) {
		for (BemEstar _enum : BemEstar.values()) {
			if (_enum.getDescricao().equals(descricao)) {
				return _enum;
			}
		}
		return null;
	}

	@Override
	public String getPage() {
		return page;
	}

}
