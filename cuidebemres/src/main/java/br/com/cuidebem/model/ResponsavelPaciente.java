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
@Table(name = "responsavel_paciente", catalog = "cuidebemres", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ResponsavelPaciente.findAll", query = "SELECT r FROM ResponsavelPaciente r")
    , @NamedQuery(name = "ResponsavelPaciente.findByIdresponsavelPaciente", query = "SELECT r FROM ResponsavelPaciente r WHERE r.idresponsavelPaciente = :idresponsavelPaciente")
    ,@NamedQuery(name = "ResponsavelPaciente.findByUnique", query = "SELECT r FROM ResponsavelPaciente r WHERE r.idresponsavel.idresponsavel = :idresponsavel and r.idpaciente.idpaciente = :idpaciente")})
public class ResponsavelPaciente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idresponsavel_paciente")
    private Integer idresponsavelPaciente;
    @JoinColumn(name = "idpaciente", referencedColumnName = "idpaciente")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Paciente idpaciente;
    @JoinColumn(name = "idresponsavel", referencedColumnName = "idresponsavel")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Responsavel idresponsavel;

    public ResponsavelPaciente() {
    }

    public ResponsavelPaciente(Integer idresponsavelPaciente) {
        this.idresponsavelPaciente = idresponsavelPaciente;
    }

    public Integer getIdresponsavelPaciente() {
        return idresponsavelPaciente;
    }

    public void setIdresponsavelPaciente(Integer idresponsavelPaciente) {
        this.idresponsavelPaciente = idresponsavelPaciente;
    }

    public Paciente getIdpaciente() {
        return idpaciente;
    }

    public void setIdpaciente(Paciente idpaciente) {
        this.idpaciente = idpaciente;
    }

    public Responsavel getIdresponsavel() {
        return idresponsavel;
    }

    public void setIdresponsavel(Responsavel idresponsavel) {
        this.idresponsavel = idresponsavel;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idresponsavelPaciente != null ? idresponsavelPaciente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ResponsavelPaciente)) {
            return false;
        }
        ResponsavelPaciente other = (ResponsavelPaciente) object;
        if ((this.idresponsavelPaciente == null && other.idresponsavelPaciente != null) || (this.idresponsavelPaciente != null && !this.idresponsavelPaciente.equals(other.idresponsavelPaciente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.cuidebem.model.ResponsavelPaciente[ idresponsavelPaciente=" + idresponsavelPaciente + " ]";
    }
    
}
