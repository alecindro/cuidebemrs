/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cuidebem.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(catalog = "cuidebemres", schema = "",name="residencia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Residencia.findAll", query = "SELECT r FROM Residencia r")
    , @NamedQuery(name = "Residencia.findByIdresidencia", query = "SELECT r FROM Residencia r WHERE r.idresidencia = :idresidencia")
    , @NamedQuery(name = "Residencia.findByRazao", query = "SELECT r FROM Residencia r WHERE r.razao = :razao")
    , @NamedQuery(name = "Residencia.findByFantasia", query = "SELECT r FROM Residencia r WHERE r.fantasia = :fantasia")
    , @NamedQuery(name = "Residencia.findByEndereco", query = "SELECT r FROM Residencia r WHERE r.endereco = :endereco")
    , @NamedQuery(name = "Residencia.findByCep", query = "SELECT r FROM Residencia r WHERE r.cep = :cep")
    , @NamedQuery(name = "Residencia.findByCnpj", query = "SELECT r FROM Residencia r WHERE r.cnpj = :cnpj")})
public class Residencia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer idresidencia;
    @Basic(optional = false)
    private String razao;
    private String fantasia;
    private String endereco;
    private String cep;
    private String cnpj;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idresidencia", fetch = FetchType.LAZY)
    private List<ResidenciaTelefone> residenciaTelefoneList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idresidencia", fetch = FetchType.LAZY)
    private List<UsuarioResidencia> usuarioResidenciaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idresidencia", fetch = FetchType.LAZY)
    private List<Paciente> pacienteList;

    public Residencia() {
    }

    public Residencia(Integer idresidencia) {
        this.idresidencia = idresidencia;
    }

    public Residencia(Integer idresidencia, String razao) {
        this.idresidencia = idresidencia;
        this.razao = razao;
    }

    public Integer getIdresidencia() {
        return idresidencia;
    }

    public void setIdresidencia(Integer idresidencia) {
        this.idresidencia = idresidencia;
    }

    public String getRazao() {
        return razao;
    }

    public void setRazao(String razao) {
        this.razao = razao;
    }

    public String getFantasia() {
        return fantasia;
    }

    public void setFantasia(String fantasia) {
        this.fantasia = fantasia;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    @XmlTransient
    public List<ResidenciaTelefone> getResidenciaTelefoneList() {
        return residenciaTelefoneList;
    }

    public void setResidenciaTelefoneList(List<ResidenciaTelefone> residenciaTelefoneList) {
        this.residenciaTelefoneList = residenciaTelefoneList;
    }

    @XmlTransient
    public List<UsuarioResidencia> getUsuarioResidenciaList() {
        return usuarioResidenciaList;
    }

    public void setUsuarioResidenciaList(List<UsuarioResidencia> usuarioResidenciaList) {
        this.usuarioResidenciaList = usuarioResidenciaList;
    }

    @XmlTransient
    public List<Paciente> getPacienteList() {
        return pacienteList;
    }

    public void setPacienteList(List<Paciente> pacienteList) {
        this.pacienteList = pacienteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idresidencia != null ? idresidencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Residencia)) {
            return false;
        }
        Residencia other = (Residencia) object;
        if ((this.idresidencia == null && other.idresidencia != null) || (this.idresidencia != null && !this.idresidencia.equals(other.idresidencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.cuidebem.model.Residencia[ idresidencia=" + idresidencia + " ]";
    }
    
}
