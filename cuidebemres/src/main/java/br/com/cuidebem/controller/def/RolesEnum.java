package br.com.cuidebem.controller.def;

public enum RolesEnum {

	ADMINRESDIDENCIA("adminResidencia"), 
	ADMIN("admin"), 
	CUIDADOR("cuidador"), 
	MEDICO("medico"), 
	RESPONSAVEL(
			"responsavel");

	private String value;

	private RolesEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
