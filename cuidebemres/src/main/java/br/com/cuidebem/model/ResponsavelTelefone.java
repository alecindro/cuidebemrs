/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cuidebem.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
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
@Table(name = "responsavel_telefone", catalog = "cuidebemres", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ResponsavelTelefone.findAll", query = "SELECT r FROM ResponsavelTelefone r")
    , @NamedQuery(name = "ResponsavelTelefone.findByIdresponsavelTelefone", query = "SELECT r FROM ResponsavelTelefone r WHERE r.idresponsavelTelefone = :idresponsavelTelefone")
    ,@NamedQuery(name = "ResponsavelTelefone.findByIdResponsavel", query = "SELECT r FROM ResponsavelTelefone r WHERE r.idresponsavel.idresponsavel = :idresponsavel")})
public class ResponsavelTelefone implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idresponsavel_telefone")
    private Integer idresponsavelTelefone;
    @JoinColumn(name = "idresponsavel", referencedColumnName = "idresponsavel")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Responsavel idresponsavel;
    @JoinColumn(name = "idtelefone", referencedColumnName = "idtelefone")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Telefone idtelefone;

    public ResponsavelTelefone() {
    }

    public ResponsavelTelefone(Integer idresponsavelTelefone) {
        this.idresponsavelTelefone = idresponsavelTelefone;
    }

    public Integer getIdresponsavelTelefone() {
        return idresponsavelTelefone;
    }

    public void setIdresponsavelTelefone(Integer idresponsavelTelefone) {
        this.idresponsavelTelefone = idresponsavelTelefone;
    }

    public Responsavel getIdresponsavel() {
        return idresponsavel;
    }

    public void setIdresponsavel(Responsavel idresponsavel) {
        this.idresponsavel = idresponsavel;
    }

    public Telefone getIdtelefone() {
        return idtelefone;
    }

    public void setIdtelefone(Telefone idtelefone) {
        this.idtelefone = idtelefone;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idresponsavelTelefone != null ? idresponsavelTelefone.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ResponsavelTelefone)) {
            return false;
        }
        ResponsavelTelefone other = (ResponsavelTelefone) object;
        if ((this.idresponsavelTelefone == null && other.idresponsavelTelefone != null) || (this.idresponsavelTelefone != null && !this.idresponsavelTelefone.equals(other.idresponsavelTelefone))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.cuidebem.model.ResponsavelTelefone[ idresponsavelTelefone=" + idresponsavelTelefone + " ]";
    }
    
}
