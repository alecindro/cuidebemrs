package br.com.cuidebem.rotinas;

import java.util.ArrayList;
import java.util.List;

import br.com.cuidebem.model.Evento;

public enum Patologias  implements IRotina{

	  HA("Hipertensão Arterial","/app/evento/blank.xhtml")
	  {public void genResumo(Evento evento){
			evento.setRespeventos(
					evento.getObsevento());
		}},
	  DIABETE("Diabetes","/app/evento/blank.xhtml")
	  {public void genResumo(Evento evento){
			evento.setRespeventos(
					evento.getObsevento());
		}},
	  HIV ("HIV","/app/evento/blank.xhtml")
	  {public void genResumo(Evento evento){
			evento.setRespeventos(
					evento.getObsevento());
		}},
	  CARDIO("Cardiopatia (problemas cardiacos)","/app/evento/blank.xhtml")
	  {public void genResumo(Evento evento){
			evento.setRespeventos(
					evento.getObsevento());
		}},
	  ASMA("Asma/bronquite","/app/evento/blank.xhtml")
	  {public void genResumo(Evento evento){
			evento.setRespeventos(
					evento.getObsevento());
		}},
	  PNEUMOPATIA("Pneumopatia (enfizema, bronquite, pneumonia)","/app/evento/blank.xhtml")
	  {public void genResumo(Evento evento){
			evento.setRespeventos(
					evento.getObsevento());
		}},
	  AVC("Acidente vascular cerebral","/app/evento/blank.xhtml")
	  {public void genResumo(Evento evento){
			evento.setRespeventos(
					evento.getObsevento());
		}},
	  OBESIDADE("Obesidade","/app/evento/blank.xhtml")
	  {public void genResumo(Evento evento){
			evento.setRespeventos(
					evento.getObsevento());
		}},
	  RENAL("Renal crônico","/app/evento/blank.xhtml")
	  {public void genResumo(Evento evento){
			evento.setRespeventos(
					evento.getObsevento());
		}},
	  EPILEPSIA("Epilepsia","/app/evento/blank.xhtml")
	  {public void genResumo(Evento evento){
			evento.setRespeventos(
					evento.getObsevento());
		}},
	  CANCER("Cancer","/app/evento/blank.xhtml")
	  {public void genResumo(Evento evento){
			evento.setRespeventos(
					evento.getObsevento());
		}},
	  TRAQUE("Faz uso de Traqueostomia","/app/evento/blank.xhtml")
	  {public void genResumo(Evento evento){
			evento.setRespeventos(
					evento.getObsevento());
		}},
	  OXIGEN("Faz uso de Oxigenioterapia","/app/evento/blank.xhtml")
	  {public void genResumo(Evento evento){
			evento.setRespeventos(
					evento.getObsevento());
		}},
	  HEPATITE("Hepatite","/app/evento/blank.xhtml")
	  {public void genResumo(Evento evento){
			evento.setRespeventos(
					evento.getObsevento());
		}},
	  NEURO("Problemas Neurológicos","/app/evento/blank.xhtml")
	  {public void genResumo(Evento evento){
			evento.setRespeventos(
					evento.getObsevento());
		}},
	  OUTROS("Outros","/app/evento/blank.xhtml")
	  {public void genResumo(Evento evento){
			evento.setRespeventos(
					evento.getObsevento());
		}},;
	
	
	private String descricao;
	private String page;
	public abstract void genResumo(Evento evento);
	
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
