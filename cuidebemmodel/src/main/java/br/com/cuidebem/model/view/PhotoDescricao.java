package br.com.cuidebem.model.view;

import java.io.Serializable;

public class PhotoDescricao implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String imgbase64;
	private String filename;
	private String type;
	private String descricao;
	private Integer idpaciente;
	private Integer idusuario;
	
	public String getImgbase64() {
		return imgbase64;
	}
	public void setImgbase64(String imgbase64) {
		this.imgbase64 = imgbase64;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	public Integer getIdpaciente() {
		return idpaciente;
	}
	public void setIdpaciente(Integer idpaciente) {
		this.idpaciente = idpaciente;
	}
	
	
	public Integer getIdusuario() {
		return idusuario;
	}
	public void setIdusuario(Integer idusuario) {
		this.idusuario = idusuario;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((imgbase64 == null) ? 0 : imgbase64.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PhotoDescricao other = (PhotoDescricao) obj;
		if (imgbase64 == null) {
			if (other.imgbase64 != null)
				return false;
		} else if (!imgbase64.equals(other.imgbase64))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "PhotoDescricao [imgbase64=" + imgbase64 + ", filename=" + filename + ", type=" + type + ", descricao="
				+ descricao + "]";
	}
	
	
	
	

}
