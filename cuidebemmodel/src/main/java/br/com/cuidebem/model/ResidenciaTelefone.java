/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cuidebem.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
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

import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 *
 * @author aleci
 */
@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "residencia_telefone", catalog = "cuidebemres", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ResidenciaTelefone.findAll", query = "SELECT r FROM ResidenciaTelefone r")
    , @NamedQuery(name = "ResidenciaTelefone.findByIdresidenciaTelefone", query = "SELECT r FROM ResidenciaTelefone r WHERE r.idresidenciaTelefone = :idresidenciaTelefone")
    ,@NamedQuery(name = "ResidenciaTelefone.findByIdResidencia", query = "SELECT r FROM ResidenciaTelefone r WHERE r.idresidencia.idresidencia = :idresidencia")})

public class ResidenciaTelefone implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idresidencia_telefone")
    private Integer idresidenciaTelefone;
    @JoinColumn(name = "idresidencia", referencedColumnName = "idresidencia")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Residencia idresidencia;
    @JoinColumn(name = "idtelefone", referencedColumnName = "idtelefone")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Telefone idtelefone;

    public ResidenciaTelefone() {
    }

    public ResidenciaTelefone(Integer idresidenciaTelefone) {
        this.idresidenciaTelefone = idresidenciaTelefone;
    }

    public Integer getIdresidenciaTelefone() {
        return idresidenciaTelefone;
    }

    public void setIdresidenciaTelefone(Integer idresidenciaTelefone) {
        this.idresidenciaTelefone = idresidenciaTelefone;
    }

    public Residencia getIdresidencia() {
        return idresidencia;
    }

    public void setIdresidencia(Residencia idresidencia) {
        this.idresidencia = idresidencia;
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
        hash += (idresidenciaTelefone != null ? idresidenciaTelefone.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ResidenciaTelefone)) {
            return false;
        }
        ResidenciaTelefone other = (ResidenciaTelefone) object;
        if ((this.idresidenciaTelefone == null && other.idresidenciaTelefone != null) || (this.idresidenciaTelefone != null && !this.idresidenciaTelefone.equals(other.idresidenciaTelefone))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.cuidebem.model.ResidenciaTelefone[ idresidenciaTelefone=" + idresidenciaTelefone + " ]";
    }
    
}
