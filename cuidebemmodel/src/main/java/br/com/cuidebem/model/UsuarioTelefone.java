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
@Table(name = "usuario_telefone", catalog = "cuidebemres", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuarioTelefone.findAll", query = "SELECT u FROM UsuarioTelefone u")
    , @NamedQuery(name = "UsuarioTelefone.findByIdusuarioTelefone", query = "SELECT u FROM UsuarioTelefone u WHERE u.idusuarioTelefone = :idusuarioTelefone")
    , @NamedQuery(name = "UsuarioTelefone.findByIdusuario", query = "SELECT u FROM UsuarioTelefone u WHERE u.idusuario.idusuario = :idusuario")})
public class UsuarioTelefone implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idusuario_telefone")
    private Integer idusuarioTelefone;
    @JoinColumn(name = "idtelefone", referencedColumnName = "idtelefone")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Telefone idtelefone;
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario idusuario;

    public UsuarioTelefone() {
    }

    public UsuarioTelefone(Integer idusuarioTelefone) {
        this.idusuarioTelefone = idusuarioTelefone;
    }

    public Integer getIdusuarioTelefone() {
        return idusuarioTelefone;
    }

    public void setIdusuarioTelefone(Integer idusuarioTelefone) {
        this.idusuarioTelefone = idusuarioTelefone;
    }

    public Telefone getIdtelefone() {
        return idtelefone;
    }

    public void setIdtelefone(Telefone idtelefone) {
        this.idtelefone = idtelefone;
    }

    public Usuario getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Usuario idusuario) {
        this.idusuario = idusuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idusuarioTelefone != null ? idusuarioTelefone.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioTelefone)) {
            return false;
        }
        UsuarioTelefone other = (UsuarioTelefone) object;
        if ((this.idusuarioTelefone == null && other.idusuarioTelefone != null) || (this.idusuarioTelefone != null && !this.idusuarioTelefone.equals(other.idusuarioTelefone))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.cuidebem.model.UsuarioTelefone[ idusuarioTelefone=" + idusuarioTelefone + " ]";
    }
    
}
