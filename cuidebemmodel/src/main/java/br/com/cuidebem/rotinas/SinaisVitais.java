package br.com.cuidebem.rotinas;

import java.util.ArrayList;
import java.util.List;

import br.com.cuidebem.model.Evento;

public enum SinaisVitais implements IRotina {

	PRESSAOARTERIAL("Pressão Arterial", "/app/evento/pressaoarterial.xhtml") {
		public String genResumo(Evento evento) {
			String result = evento.getGrupoevento().concat("<br/>");
			result = result.concat(evento.getSubgrupoevento()).concat("<br/>");
			result = result.concat(evento.getPressaoinicial() + " x " + evento.getPressaofinal() + " mmHg");
			if (evento.getObsevento() != null) {
				result = result.concat("<br/>").concat(evento.getObsevento());
			}

			return result;
		}
	},
	FREQUENCIACARDIACA("Frequência Cardíaca", "/app/evento/frequenciacardiaca.xhtml") {
		public String genResumo(Evento evento) {
			String result = evento.getGrupoevento().concat("<br/>");
			result = result.concat(evento.getSubgrupoevento()).concat("<br/>");
			result = result.concat(evento.getValue() + " bpm");
			if (evento.getObsevento() != null) {
				result = result.concat("<br/>").concat(evento.getObsevento());
			}
			return result;
		}
	},
	TEMPERATURA("Temperatura Corporal", "/app/evento/temperatura.xhtml") {
		public String genResumo(Evento evento) {
			String result = evento.getGrupoevento().concat("<br/>");
			result = result.concat(evento.getSubgrupoevento()).concat("<br/>");
			result = result.concat(evento.getValue() + " ºC");
			if (evento.getObsevento() != null) {
				result = result.concat("<br/>").concat(evento.getObsevento());
			}
			return result;
		}
	},
	FREQUENCIARESPIRATORIA("Frequência Respiratória", "/app/evento/frequenciarespiratoria.xhtml") {
		public String genResumo(Evento evento) {
			String result = evento.getGrupoevento().concat("<br/>");
			result = result.concat(evento.getSubgrupoevento()).concat("<br/>");
			result = result.concat(evento.getValue() + " vezes/min");
			if (evento.getObsevento() != null) {
				result = result.concat("<br/>").concat(evento.getObsevento());
			}
			return result;
		}
	},
	SATURACAOOXIGENIO("Saturação de Oxigênio (oximetria)", "/app/evento/oximetria.xhtml") {
		public String genResumo(Evento evento) {
			String result = evento.getGrupoevento().concat("<br/>");
			result = result.concat(evento.getSubgrupoevento()).concat("<br/>");
			result = result.concat(evento.getValue() + " %");
			if (evento.getObsevento() != null) {
				result = result.concat("<br/>").concat(evento.getObsevento());
			}
			return result;
		}
	},
	CONTROLEGLICEMIA("Controle da Glicemia", "/app/evento/glicemia.xhtml") {
		public String genResumo(Evento evento) {
			String result = evento.getGrupoevento().concat("<br/>");
			result = result.concat(evento.getSubgrupoevento()).concat("<br/>");
			result = result.concat(evento.getValue() + " mmHg");
			if (evento.getObsevento() != null) {
				result = result.concat("<br/>").concat(evento.getObsevento());
			}
			return result;
		}
	};

	private String descricao;
	private String page;


	private SinaisVitais(String descricao, String page) {
		this.descricao = descricao;
		this.page = page;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public SinaisVitais[] getValues() {
		return SinaisVitais.values();
	}

	public static List<String> getDescricaos() {
		List<String> descricaos = new ArrayList<String>();
		for (SinaisVitais sinaisVitais : SinaisVitais.values()) {
			descricaos.add(sinaisVitais.getDescricao());
		}
		return descricaos;
	}

	@Override
	public String getPage() {
		return page;
	}

	@Override
	public IRotina fromDescricao(String descricao) {
		for (SinaisVitais sinalVital : SinaisVitais.values()) {
			if (sinalVital.getDescricao().equals(descricao)) {
				return sinalVital;
			}
		}
		return null;
	}
}
