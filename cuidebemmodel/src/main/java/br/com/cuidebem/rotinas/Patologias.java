package br.com.cuidebem.rotinas;

import java.util.ArrayList;
import java.util.List;

import br.com.cuidebem.model.Evento;

public enum Patologias  implements IRotina{

	  HA("Hipertensão Arterial","/app/evento/blank.xhtml")
	  {public String genResumo(Evento evento){
			String result = evento.getGrupoevento().concat("<br/>");
			result = result.concat(evento.getSubgrupoevento());
			if (evento.getObsevento() != null) {
				result = result.concat("<br/>").concat(evento.getObsevento());
			}
			return result;
		}
	  },
	  DIABETE("Diabetes","/app/evento/blank.xhtml")
	  {public String genResumo(Evento evento){
		  String result = evento.getGrupoevento().concat("<br/>");
			result = result.concat(evento.getSubgrupoevento());
			if (evento.getObsevento() != null) {
				result = result.concat("<br/>").concat(evento.getObsevento());
			}
			return result;
			}
	  },
	  HIV ("HIV","/app/evento/blank.xhtml")
	  {public String genResumo(Evento evento){
		  String result = evento.getGrupoevento().concat("<br/>");
			result = result.concat(evento.getSubgrupoevento());
			if (evento.getObsevento() != null) {
				result = result.concat("<br/>").concat(evento.getObsevento());
			}
			return result;
			}
	  },
	  CARDIO("Cardiopatia (problemas cardiacos)","/app/evento/blank.xhtml")
	  {public String genResumo(Evento evento){
		  String result = evento.getGrupoevento().concat("<br/>");
			result = result.concat(evento.getSubgrupoevento());
			if (evento.getObsevento() != null) {
				result = result.concat("<br/>").concat(evento.getObsevento());
			}
			return result;
			}
	  },
	  ASMA("Asma/bronquite","/app/evento/blank.xhtml")
	  {public String genResumo(Evento evento){
		  String result = evento.getGrupoevento().concat("<br/>");
			result = result.concat(evento.getSubgrupoevento());
			if (evento.getObsevento() != null) {
				result = result.concat("<br/>").concat(evento.getObsevento());
			}
			return result;
			}
	  },
	  PNEUMOPATIA("Pneumopatia (enfizema, bronquite, pneumonia)","/app/evento/blank.xhtml")
	  {public String genResumo(Evento evento){
		  String result = evento.getGrupoevento().concat("<br/>");
			result = result.concat(evento.getSubgrupoevento());
			if (evento.getObsevento() != null) {
				result = result.concat("<br/>").concat(evento.getObsevento());
			}
			return result;
			}
	  },
	  AVC("Acidente vascular cerebral","/app/evento/blank.xhtml")
	  {public String genResumo(Evento evento){
		  String result = evento.getGrupoevento().concat("<br/>");
			result = result.concat(evento.getSubgrupoevento());
			if (evento.getObsevento() != null) {
				result = result.concat("<br/>").concat(evento.getObsevento());
			}
			return result;
			}
	  },
	  OBESIDADE("Obesidade","/app/evento/blank.xhtml")
	  {public String genResumo(Evento evento){
		  String result = evento.getGrupoevento().concat("<br/>");
			result = result.concat(evento.getSubgrupoevento());
			if (evento.getObsevento() != null) {
				result = result.concat("<br/>").concat(evento.getObsevento());
			}
			return result;
			}
	  },
	  RENAL("Renal crônico","/app/evento/blank.xhtml")
	  {public String genResumo(Evento evento){
		  String result = evento.getGrupoevento().concat("<br/>");
			result = result.concat(evento.getSubgrupoevento());
			if (evento.getObsevento() != null) {
				result = result.concat("<br/>").concat(evento.getObsevento());
			}
			return result;
			}
	  },
	  EPILEPSIA("Epilepsia","/app/evento/blank.xhtml")
	  {public String genResumo(Evento evento){
		  String result = evento.getGrupoevento().concat("<br/>");
			result = result.concat(evento.getSubgrupoevento());
			if (evento.getObsevento() != null) {
				result = result.concat("<br/>").concat(evento.getObsevento());
			}
			return result;
			}
	  },
	  CANCER("Cancer","/app/evento/blank.xhtml")
	  {public String genResumo(Evento evento){
		  String result = evento.getGrupoevento().concat("<br/>");
			result = result.concat(evento.getSubgrupoevento());
			if (evento.getObsevento() != null) {
				result = result.concat("<br/>").concat(evento.getObsevento());
			}
			return result;
			}
	  },
	  TRAQUE("Faz uso de Traqueostomia","/app/evento/blank.xhtml")
	  {public String genResumo(Evento evento){
		  String result = evento.getGrupoevento().concat("<br/>");
			result = result.concat(evento.getSubgrupoevento());
			if (evento.getObsevento() != null) {
				result = result.concat("<br/>").concat(evento.getObsevento());
			}
			return result;
			}
	  },
	  OXIGEN("Faz uso de Oxigenioterapia","/app/evento/blank.xhtml")
	  {public String genResumo(Evento evento){
		  String result = evento.getGrupoevento().concat("<br/>");
			result = result.concat(evento.getSubgrupoevento());
			if (evento.getObsevento() != null) {
				result = result.concat("<br/>").concat(evento.getObsevento());
			}
			return result;
			}
	  },
	  HEPATITE("Hepatite","/app/evento/blank.xhtml")
	  {public String genResumo(Evento evento){
		  String result = evento.getGrupoevento().concat("<br/>");
			result = result.concat(evento.getSubgrupoevento());
			if (evento.getObsevento() != null) {
				result = result.concat("<br/>").concat(evento.getObsevento());
			}
			return result;
			}
	  },
	  NEURO("Problemas Neurológicos","/app/evento/blank.xhtml")
	  {public String genResumo(Evento evento){
		  String result = evento.getGrupoevento().concat("<br/>");
			result = result.concat(evento.getSubgrupoevento());
			if (evento.getObsevento() != null) {
				result = result.concat("<br/>").concat(evento.getObsevento());
			}
			return result;
			}
	  },
	  OUTROS("Outros","/app/evento/blank.xhtml")
	  {public String genResumo(Evento evento){
		  String result = evento.getGrupoevento().concat("<br/>");
			result = result.concat(evento.getSubgrupoevento());
			if (evento.getObsevento() != null) {
				result = result.concat("<br/>").concat(evento.getObsevento());
			}
			return result;
			}
	  };
	
	
	private String descricao;
	private String page;
	
	private Patologias(String descricao,String page) {
		this.descricao = descricao;
		this.page = page;
	}

	public String getDescricao() {
		return descricao;
	}
	
	
	public static Patologias find(String descricao){
		for(Patologias patologia : Patologias.values()){
		if(patologia.getDescricao().equals(descricao)){
			return patologia;
		}
		}
		return null;
	}
	
	public static List<String> getDescricaos() {
		List<String> descricaos = new ArrayList<String>();
		for (Patologias patologias : Patologias.values()) {
			descricaos.add(patologias.getDescricao());
		}
		return descricaos;
	}
	
	@Override
	public IRotina fromDescricao(String descricao) {
		for (Patologias _enum : Patologias.values()) {
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
