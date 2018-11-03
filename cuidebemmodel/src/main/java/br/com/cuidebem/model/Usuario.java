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
import javax.persistence.Cacheable;
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
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author aleci
 */
@Entity
@Cacheable
@Table(catalog = "cuidebemres", schema = "", name="usuario")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
	@NamedQuery(name = "Usuario.findAllEnabled", query = "SELECT u FROM Usuario u where u.enabled = true"),
		@NamedQuery(name = "Usuario.findByIdusuario", query = "SELECT u FROM Usuario u WHERE u.idusuario = :idusuario"),
		@NamedQuery(name = "Usuario.findByEmail", query = "SELECT u FROM Usuario u WHERE u.email = :email"),
		@NamedQuery(name = "Usuario.findByNome", query = "SELECT u FROM Usuario u WHERE u.nome = :nome"),
		@NamedQuery(name = "Usuario.findByApelido", query = "SELECT u FROM Usuario u WHERE u.apelido = :apelido"),
		@NamedQuery(name = "Usuario.findByEnabled", query = "SELECT u FROM Usuario u WHERE u.enabled = :enabled"),
		@NamedQuery(name = "Usuario.findByGenero", query = "SELECT u FROM Usuario u WHERE u.genero = :genero"),
		@NamedQuery(name = "Usuario.findByDatanascimento", query = "SELECT u FROM Usuario u WHERE u.datanascimento = :datanascimento"),
		@NamedQuery(name = "Usuario.findByTipousuario", query = "SELECT u FROM Usuario u WHERE u.tipoUsuario = :tipoUsuario and u.enabled = true"),
		@NamedQuery(name = "Usuario.findAllDisabled", query = "SELECT u FROM Usuario u where u.enabled = false")
		})
@NamedNativeQueries({
	@NamedNativeQuery(name="Usuario.findByResidenciaEnabled", query="select u.* from usuario u inner join usuario_residencia ur on u.idusuario = ur.idusuario where ur.idresidencia = ?1 and u.enabled = ?2",resultClass=Usuario.class),
	@NamedNativeQuery(name="Usuario.findByTipoUsuarioEnabled", query="select u.* from usuario u inner join usuario_residencia ur on u.idusuario = ur.idusuario where ur.idresidencia = ?1 and u.enabled = ?2 and u.tipousuario = ?3",resultClass=Usuario.class)
})
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	private Integer idusuario;
	@Basic(optional = false)
	private String email;
	@Basic(optional = true)
	private String nome;
	private String apelido;
	private String tipoUsuario;
	@Basic(optional = false)
	private Boolean enabled = Boolean.TRUE;
	@Basic(optional = true)
	private Boolean genero;
	@Temporal(TemporalType.DATE)
	private Date datanascimento;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idusuario", fetch = FetchType.LAZY)
	private List<UsuarioResidencia> usuarioResidenciaList;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idusuario", fetch = FetchType.EAGER)
	private List<UsuarioTelefone> usuarioTelefoneList;
	
	@Transient
	private String[] roles;

	public Usuario() {
	}

	public Usuario(Integer idusuario) {
		this.idusuario = idusuario;
	}

	public Usuario(Integer idusuario, String email, String nome, Boolean enabled, Boolean genero) {
		this.idusuario = idusuario;
		this.email = email;
		this.nome = nome;
		this.enabled = enabled;
		this.genero = genero;
	}

	public Integer getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(Integer idusuario) {
		this.idusuario = idusuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Boolean getGenero() {
		return genero;
	}

	public void setGenero(Boolean genero) {
		this.genero = genero;
	}

	public Date getDatanascimento() {
		return datanascimento;
	}

	public void setDatanascimento(Date datanascimento) {
		this.datanascimento = datanascimento;
	}
	
	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	
	
	@XmlTransient
	public String[] getRoles() {
		if(tipoUsuario != null){
			roles = tipoUsuario.split(",");
		}
		return roles;
	}

	public void setRoles(String[] roles) {
		if(roles != null && roles.length >0){
			tipoUsuario = roles[0];
			for(int i=1;i<roles.length;i++){
				tipoUsuario=tipoUsuario.concat(",").concat(roles[i]);
			}	
		}
		this.roles = roles;
	}

	@XmlTransient
	public List<UsuarioResidencia> getUsuarioResidenciaList() {
		if (usuarioResidenciaList == null) {
			usuarioResidenciaList = new ArrayList<UsuarioResidencia>();
		}
		return usuarioResidenciaList;
	}

	public void setUsuarioResidenciaList(List<UsuarioResidencia> usuarioResidenciaList) {
		this.usuarioResidenciaList = usuarioResidenciaList;
	}

	@XmlTransient
	public List<UsuarioTelefone> getUsuarioTelefoneList() {
		if (usuarioTelefoneList == null) {
			usuarioTelefoneList = new ArrayList<UsuarioTelefone>();
		}
		return usuarioTelefoneList;
	}

	public void setUsuarioTelefoneList(List<UsuarioTelefone> usuarioTelefoneList) {
		this.usuarioTelefoneList = usuarioTelefoneList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idusuario != null ? idusuario.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Usuario)) {
			return false;
		}
		Usuario other = (Usuario) object;
		if ((this.idusuario == null && other.idusuario != null)
				|| (this.idusuario != null && !this.idusuario.equals(other.idusuario))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.com.cuidebem.model.Usuario[ idusuario=" + idusuario + " ]";
	}

}
