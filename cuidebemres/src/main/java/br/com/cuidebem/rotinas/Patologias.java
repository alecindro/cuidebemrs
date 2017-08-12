package br.com.cuidebem.rotinas;

import java.util.ArrayList;
import java.util.List;

public enum Patologias {

	  HA("Hipertensão Arterial"),
	  DIABETE("Diabetes"),
	  HIV ("HIV"),
	  CARDIO("Cardiopatia (problemas cardiacos)"),
	  ASMA("Asma/bronquite"),
	  PNEUMOPATIA("Pneumopatia (enfizema, bronquite, pneumonia)"),
	  AVC("Acidente vascular cerebral"),
	  OBESIDADE("Obesidade"),
	  RENAL("Renal crônico"),
	  EPILEPSIA("Epilepsia"),
	  CANCER("Cancer"),
	  TRAQUE("Faz uso de Traqueostomia"),
	  OXIGEN("Faz uso de Oxigenioterapia"),
	  HEPATITE("Hepatite"),
	  NEURO("Problemas Neurológicos"),
	  OUTROS("Outros");
	
	
	private String descricao;
	
	private Patologias(String descricao) {
		this.descricao = descricao;
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
	 
}
