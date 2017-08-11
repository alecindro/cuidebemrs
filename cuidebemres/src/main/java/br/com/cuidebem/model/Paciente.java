/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cuidebem.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author aleci
 */
@Entity
@Table(catalog = "cuidebemres", schema = "")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Paciente.findAll", query = "SELECT p FROM Paciente p"),
		@NamedQuery(name = "Paciente.findByIdpaciente", query = "SELECT p FROM Paciente p WHERE p.idpaciente = :idpaciente and p.enabled = true"),
		@NamedQuery(name = "Paciente.findByNome", query = "SELECT p FROM Paciente p WHERE p.nome = :nome"),
		@NamedQuery(name = "Paciente.findByApelido", query = "SELECT p FROM Paciente p WHERE p.apelido = :apelido"),
		@NamedQuery(name = "Paciente.findByGenero", query = "SELECT p FROM Paciente p WHERE p.genero = :genero"),
		@NamedQuery(name = "Paciente.findByEnabled", query = "SELECT p FROM Paciente p WHERE p.enabled = :enabled and p.idresidencia.idresidencia = :idresidencia"),
		@NamedQuery(name = "Paciente.findByDatanascimento", query = "SELECT p FROM Paciente p WHERE p.datanascimento = :datanascimento"),
		@NamedQuery(name = "Paciente.findByStatus", query = "SELECT p FROM Paciente p WHERE p.status = :status") })
public class Paciente implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	private Integer idpaciente;
	@Basic(optional = false)
	private String nome;
	private String apelido;
	@Basic(optional = false)
	private boolean genero;
	@Basic(optional = false)
	private boolean enabled;
	@Temporal(TemporalType.DATE)
	private Date datanascimento;
	private Boolean status;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idpaciente", fetch = FetchType.LAZY)
	private List<ResponsavelPaciente> responsavelPacienteList;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "paciente", fetch = FetchType.LAZY)
	private List<PatologiaPaciente> patologiaPaciente;
	@JoinColumn(name = "idresidencia", referencedColumnName = "idresidencia")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private Residencia idresidencia;

	public Paciente() {
	}

	public Paciente(Integer idpaciente) {
		this.idpaciente = idpaciente;
	}

	public Paciente(Integer idpaciente, String nome, boolean genero, boolean enabled) {
		this.idpaciente = idpaciente;
		this.nome = nome;
		this.genero = genero;
		this.enabled = enabled;
	}

	public Integer getIdpaciente() {
		return idpaciente;
	}

	public void setIdpaciente(Integer idpaciente) {
		this.idpaciente = idpaciente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public boolean getGenero() {
		return genero;
	}

	public void setGenero(boolean genero) {
		this.genero = genero;
	}

	public boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Date getDatanascimento() {
		return datanascimento;
	}

	public void setDatanascimento(Date datanascimento) {
		this.datanascimento = datanascimento;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	@XmlTransient
	public List<ResponsavelPaciente> getResponsavelPacienteList() {
		return responsavelPacienteList;
	}

	public void setResponsavelPacienteList(List<ResponsavelPaciente> responsavelPacienteList) {
		this.responsavelPacienteList = responsavelPacienteList;
	}

	@XmlTransient
	public List<PatologiaPaciente> getPatologiaPaciente() {
		if (patologiaPaciente == null) {
			patologiaPaciente = new ArrayList<PatologiaPaciente>();
		}
		return patologiaPaciente;
	}

	public void setPatologiaPaciente(List<PatologiaPaciente> patologiaPaciente) {
		this.patologiaPaciente = patologiaPaciente;
	}
		
	@XmlTransient
	public Residencia getIdresidencia() {
		return idresidencia;
	}

	public void setIdresidencia(Residencia idresidencia) {
		this.idresidencia = idresidencia;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idpaciente != null ? idpaciente.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Paciente)) {
			return false;
		}
		Paciente other = (Paciente) object;
		if ((this.idpaciente == null && other.idpaciente != null)
				|| (this.idpaciente != null && !this.idpaciente.equals(other.idpaciente))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.com.cuidebem.model.Paciente[ idpaciente=" + idpaciente + " ]";
	}

}
