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
@Table(name = "usuario_residencia", catalog = "cuidebemres", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuarioResidencia.findAll", query = "SELECT u FROM UsuarioResidencia u")
    , @NamedQuery(name = "UsuarioResidencia.findByIdusuarioResidencia", query = "SELECT u FROM UsuarioResidencia u WHERE u.idusuarioResidencia = :idusuarioResidencia")
    ,@NamedQuery(name = "UsuarioResidencia.findByIdusuario", query = "SELECT u FROM UsuarioResidencia u WHERE u.idusuario.idusuario = :idusuario")})
public class UsuarioResidencia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idusuario_residencia")
    private Integer idusuarioResidencia;
    @JoinColumn(name = "idresidencia", referencedColumnName = "idresidencia")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Residencia idresidencia;
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario idusuario;

    public UsuarioResidencia() {
    }

    public UsuarioResidencia(Integer idusuarioResidencia) {
        this.idusuarioResidencia = idusuarioResidencia;
    }

    public Integer getIdusuarioResidencia() {
        return idusuarioResidencia;
    }

    public void setIdusuarioResidencia(Integer idusuarioResidencia) {
        this.idusuarioResidencia = idusuarioResidencia;
    }

    public Residencia getIdresidencia() {
        return idresidencia;
    }

    public void setIdresidencia(Residencia idresidencia) {
        this.idresidencia = idresidencia;
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
        hash += (idusuarioResidencia != null ? idusuarioResidencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioResidencia)) {
            return false;
        }
        UsuarioResidencia other = (UsuarioResidencia) object;
        if ((this.idusuarioResidencia == null && other.idusuarioResidencia != null) || (this.idusuarioResidencia != null && !this.idusuarioResidencia.equals(other.idusuarioResidencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.cuidebem.model.UsuarioResidencia[ idusuarioResidencia=" + idusuarioResidencia + " ]";
    }
    
}
