package br.com.cuidebem.model.def;


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
	  NEURO("Problemas Neurológicos");
	
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
	 
}

