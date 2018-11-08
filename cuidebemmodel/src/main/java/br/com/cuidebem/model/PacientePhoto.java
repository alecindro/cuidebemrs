/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cuidebem.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author aleci
 */
@Entity
@Cacheable(value=false)
@Table(name = "paciente_photo", catalog = "cuidebemres", schema = "")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "PacientePhoto.findAll", query = "SELECT p FROM PacientePhoto p"),
		@NamedQuery(name = "PacientePhoto.findByIdpaciente", query = "SELECT p FROM PacientePhoto p WHERE p.idpaciente = :idpaciente"),
		@NamedQuery(name = "PacientePhoto.findByIdpacientePrincipal", query = "SELECT p FROM PacientePhoto p WHERE p.idpaciente = :idpaciente and p.principal = true")})
@NamedNativeQueries({
	@NamedNativeQuery(name = "PacientePhoto.findByPhotoDiaria", query = "SELECT p.idpacientephoto as idpacientephoto,p.idpaciente as idpaciente,'' as photo, p.descricao as descricao, p.principal as principal,p.type as type,p.dataregistro as dataregistro,p.idusuario as idusuario FROM paciente_photo p WHERE p.idpaciente = ?1 and date(p.dataregistro) = date(?2) and p.principal = 0 order by p.dataregistro desc", resultClass=PacientePhoto.class)
})
public class PacientePhoto implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	private Integer idpacientephoto;

	@Basic(optional = false)
	private Integer idpaciente;
	@Lob
	private byte[] photo;
	private String descricao;
	private boolean principal;
	private String type;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataregistro;
	private Integer idusuario;

	public PacientePhoto() {
	}

	public Integer getIdpacientephoto() {
		return idpacientephoto;
	}

	public void setIdpacientephoto(Integer idpacientephoto) {
		this.idpacientephoto = idpacientephoto;
	}

	public PacientePhoto(Integer idpaciente) {
		this.idpaciente = idpaciente;
	}

	public Integer getIdpaciente() {
		return idpaciente;
	}

	public void setIdpaciente(Integer idpaciente) {
		this.idpaciente = idpaciente;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean isPrincipal() {
		return principal;
	}

	public void setPrincipal(boolean principal) {
		this.principal = principal;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getDataregistro() {
		return dataregistro;
	}

	public void setDataregistro(Date dataregistro) {
		this.dataregistro = dataregistro;
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
		result = prime * result + ((dataregistro == null) ? 0 : dataregistro.hashCode());
		result = prime * result + ((idpaciente == null) ? 0 : idpaciente.hashCode());
		result = prime * result + (principal ? 1231 : 1237);
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
		PacientePhoto other = (PacientePhoto) obj;
		if (dataregistro == null) {
			if (other.dataregistro != null)
				return false;
		} else if (!dataregistro.equals(other.dataregistro))
			return false;
		if (idpaciente == null) {
			if (other.idpaciente != null)
				return false;
		} else if (!idpaciente.equals(other.idpaciente))
			return false;
		if (principal != other.principal)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PacientePhoto [idpaciente=" + idpaciente + ", photo=" + Arrays.toString(photo) + ", descricao="
				+ descricao + ", principal=" + principal + ", type=" + type + ", dataregistro=" + dataregistro + "]";
	}

}
