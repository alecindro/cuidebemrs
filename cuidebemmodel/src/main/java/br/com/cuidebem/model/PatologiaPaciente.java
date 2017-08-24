/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cuidebem.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author aleci
 */
@Entity
@Table(name = "patologia_paciente", catalog = "cuidebemres", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PatologiaPaciente.findAll", query = "SELECT p FROM PatologiaPaciente p")
    , @NamedQuery(name = "PatologiaPaciente.findByIdpaciente", query = "SELECT p FROM PatologiaPaciente p WHERE p.idpaciente = :idpaciente")
    , @NamedQuery(name = "PatologiaPaciente.findByPatologia", query = "SELECT p FROM PatologiaPaciente p WHERE p.patologia = :patologia")})
public class PatologiaPaciente implements Serializable {

    private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	private Integer idpatologiapaciente;

    @Basic(optional = false)
    private Integer idpaciente;
    @Basic(optional = false)
    private String patologia;
    @JoinColumn(name = "idpaciente", referencedColumnName = "idpaciente", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Paciente paciente;

    public PatologiaPaciente() {
    }

    public PatologiaPaciente(Integer idpatologiapaciente) {
        this.idpatologiapaciente = idpatologiapaciente;
    }

    public PatologiaPaciente(Integer idpaciente, String patologia) {
        this.idpaciente = idpaciente;
        this.patologia = patologia;
    }
    
    public PatologiaPaciente(Integer idpatologiapaciente, Integer idpaciente, String patologia) {
		super();
		this.idpatologiapaciente = idpatologiapaciente;
		this.idpaciente = idpaciente;
		this.patologia = patologia;
	}

	public Integer getIdpatologiapaciente() {
		return idpatologiapaciente;
	}

	public void setIdpatologiapaciente(Integer idpatologiapaciente) {
		this.idpatologiapaciente = idpatologiapaciente;
	}

	public Integer getIdpaciente() {
        return idpaciente;
    }

    public void setIdpaciente(Integer idpaciente) {
        this.idpaciente = idpaciente;
    }

    public String getPatologia() {
        return patologia;
    }

    public void setPatologia(String patologia) {
        this.patologia = patologia;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpaciente != null ? idpaciente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PatologiaPaciente)) {
            return false;
        }
        PatologiaPaciente other = (PatologiaPaciente) object;
        if ((this.idpaciente == null && other.idpaciente != null) || (this.idpaciente != null && !this.idpaciente.equals(other.idpaciente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.cuidebem.model.PatologiaPaciente[ idpaciente=" + idpaciente + " ]";
    }
    
}
