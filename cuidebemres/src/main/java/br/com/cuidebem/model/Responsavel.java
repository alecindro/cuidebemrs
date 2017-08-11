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
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
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
@NamedQueries({
    @NamedQuery(name = "Responsavel.findAll", query = "SELECT r FROM Responsavel r")
    , @NamedQuery(name = "Responsavel.findByIdresponsavel", query = "SELECT r FROM Responsavel r WHERE r.idresponsavel = :idresponsavel")
    , @NamedQuery(name = "Responsavel.findByNome", query = "SELECT r FROM Responsavel r WHERE r.nome = :nome")
    , @NamedQuery(name = "Responsavel.findByApelido", query = "SELECT r FROM Responsavel r WHERE r.apelido = :apelido")
	,@NamedQuery(name = "Responsavel.findByEnabled", query = "SELECT r FROM Responsavel r WHERE r.enabled = :enabled")
    , @NamedQuery(name = "Responsavel.findByEmail", query = "SELECT r FROM Responsavel r WHERE r.email = :email")})
@NamedNativeQueries({
	@NamedNativeQuery(name="Responsavel.findByPaciente", query="select * from responsavel r where r.idresponsavel in (select rp.idresponsavel from responsavel_paciente rp where rp.idpaciente = ?1) and r.enabled = 1", resultClass=Responsavel.class)
})
public class Responsavel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer idresponsavel;
    private String nome;
    private String apelido;
    private String email;
    @Basic(optional = false)
	private boolean enabled;
    @Temporal(TemporalType.DATE)
	private Date datanascimento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idresponsavel", fetch = FetchType.LAZY)
    private List<ResponsavelPaciente> responsavelPacienteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idresponsavel", fetch = FetchType.LAZY)
    private List<ResponsavelTelefone> responsavelTelefoneList;

    public Responsavel() {
    }

    public Responsavel(Integer idresponsavel) {
        this.idresponsavel = idresponsavel;
    }

    public Integer getIdresponsavel() {
        return idresponsavel;
    }

    public void setIdresponsavel(Integer idresponsavel) {
        this.idresponsavel = idresponsavel;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public boolean isEnabled() {
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

	@XmlTransient
    public List<ResponsavelPaciente> getResponsavelPacienteList() {
		if(responsavelPacienteList == null){
			responsavelPacienteList = new ArrayList<ResponsavelPaciente>();
		}
        return responsavelPacienteList;
    }

    public void setResponsavelPacienteList(List<ResponsavelPaciente> responsavelPacienteList) {
        this.responsavelPacienteList = responsavelPacienteList;
    }


    @XmlTransient
    public List<ResponsavelTelefone> getResponsavelTelefoneList() {
    	if(responsavelTelefoneList == null){
    		responsavelTelefoneList = new ArrayList<ResponsavelTelefone>();
    	}
        return responsavelTelefoneList;
    }

    public void setResponsavelTelefoneList(List<ResponsavelTelefone> responsavelTelefoneList) {
        this.responsavelTelefoneList = responsavelTelefoneList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idresponsavel != null ? idresponsavel.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Responsavel)) {
            return false;
        }
        Responsavel other = (Responsavel) object;
        if ((this.idresponsavel == null && other.idresponsavel != null) || (this.idresponsavel != null && !this.idresponsavel.equals(other.idresponsavel))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.cuidebem.model.Responsavel[ idresponsavel=" + idresponsavel + " ]";
    }
    
}
