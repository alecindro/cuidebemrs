package br.com.cuidebem.model.def;

public enum TipoUsuario {

	ADMINRESDIDENCIA("adminResidencia"),
	CUIDADOR("cuidador");
	
	private String value;

	private TipoUsuario(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	
}
