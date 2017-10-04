/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cuidebem.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author aleci
 */
@Entity
@Table(catalog = "cuidebemres", schema = "", name="telefone")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Telefone.findAll", query = "SELECT t FROM Telefone t")
    , @NamedQuery(name = "Telefone.findByIdtelefone", query = "SELECT t FROM Telefone t WHERE t.idtelefone = :idtelefone")
    , @NamedQuery(name = "Telefone.findByTelefone", query = "SELECT t FROM Telefone t WHERE t.telefone = :telefone")
    , @NamedQuery(name = "Telefone.findByDdd", query = "SELECT t FROM Telefone t WHERE t.ddd = :ddd")
    , @NamedQuery(name = "Telefone.findByTipo", query = "SELECT t FROM Telefone t WHERE t.tipo = :tipo")
    , @NamedQuery(name = "Telefone.findByOperadora", query = "SELECT t FROM Telefone t WHERE t.operadora = :operadora")})
@NamedNativeQueries({
	@NamedNativeQuery(name = "Telefone.findByIdResponsavel", query="Select * from telefone t inner join responsavel_telefone rt on t.idtelefone = rt.idtelefone where rt.idresponsavel = ?1", resultClass=Telefone.class)
})
public class Telefone implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer idtelefone;
    private String telefone;
    private String ddd;
    private String tipo;
    private String operadora;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idtelefone", fetch = FetchType.LAZY)
    private Set<ResidenciaTelefone> residenciaTelefoneList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idtelefone", fetch = FetchType.LAZY)
    private Set<UsuarioTelefone> usuarioTelefoneList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idtelefone", fetch = FetchType.LAZY)
    private Set<ResponsavelTelefone> responsavelTelefoneList;

    public Telefone() {
    }

    public Telefone(Integer idtelefone) {
        this.idtelefone = idtelefone;
    }

    public Integer getIdtelefone() {
        return idtelefone;
    }

    public void setIdtelefone(Integer idtelefone) {
        this.idtelefone = idtelefone;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getOperadora() {
        return operadora;
    }

    public void setOperadora(String operadora) {
        this.operadora = operadora;
    }

    @XmlTransient
    public Set<ResidenciaTelefone> getResidenciaTelefoneList() {
    	if(residenciaTelefoneList == null){
    		residenciaTelefoneList = new HashSet<ResidenciaTelefone>();
    	}
        return residenciaTelefoneList;
    }

    public void setResidenciaTelefoneList(Set<ResidenciaTelefone> residenciaTelefoneList) {
        this.residenciaTelefoneList = residenciaTelefoneList;
    }

    @XmlTransient
    public Set<UsuarioTelefone> getUsuarioTelefoneList() {
    	if(usuarioTelefoneList == null){
    		usuarioTelefoneList = new HashSet<UsuarioTelefone>();
    	}
        return usuarioTelefoneList;
    }

    public void setUsuarioTelefoneList(Set<UsuarioTelefone> usuarioTelefoneList) {
        this.usuarioTelefoneList = usuarioTelefoneList;
    }

    @XmlTransient
    public Set<ResponsavelTelefone> getResponsavelTelefoneList() {
    	if(responsavelTelefoneList == null){
    		responsavelTelefoneList = new HashSet<ResponsavelTelefone>();
    	}
        return responsavelTelefoneList;
    }

    public void setResponsavelTelefoneList(Set<ResponsavelTelefone> responsavelTelefoneList) {
        this.responsavelTelefoneList = responsavelTelefoneList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtelefone != null ? idtelefone.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Telefone)) {
            return false;
        }
        Telefone other = (Telefone) object;
        if ((this.idtelefone == null && other.idtelefone != null) || (this.idtelefone != null && !this.idtelefone.equals(other.idtelefone))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.cuidebem.model.Telefone[ idtelefone=" + idtelefone + " ]";
    }
    
}
