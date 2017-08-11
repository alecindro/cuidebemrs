/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cuidebem.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import br.com.cuidebem.controller.util.DateUtil;

/**
 *
 * @author aleci
 */
@Entity
@Table(catalog = "cuidebemres", schema = "")
@XmlRootElement

@NamedQueries({ @NamedQuery(name = "Evento.findAll", query = "SELECT e FROM Evento e"),
		@NamedQuery(name = "Evento.findByDescevento", query = "SELECT e FROM Evento e WHERE e.descevento = :descevento"),
		@NamedQuery(name = "Evento.findByDataevento", query = "SELECT e FROM Evento e WHERE e.dataevento = :dataevento"),
		@NamedQuery(name = "Evento.findByDataregistro", query = "SELECT e FROM Evento e WHERE e.dataregistro = :dataregistro"),
		@NamedQuery(name = "Evento.findByEnabled", query = "SELECT e FROM Evento e WHERE e.enabled = :enabled"),
		@NamedQuery(name = "Evento.findByObsevento", query = "SELECT e FROM Evento e WHERE e.obsevento = :obsevento"),
		@NamedQuery(name = "Evento.findByGrupoevento", query = "SELECT e FROM Evento e WHERE e.grupoevento = :grupoevento"),
		@NamedQuery(name = "Evento.findBySubgrupoevento", query = "SELECT e FROM Evento e WHERE e.subgrupoevento = :subgrupoevento"),
		@NamedQuery(name = "Evento.findByRespeventos", query = "SELECT e FROM Evento e WHERE e.respeventos = :respeventos") })
@NamedNativeQueries({
		@NamedNativeQuery(name = "Evento.findByPaciente", query = "select * from evento where idpaciente=?1 and dataevento between ?2 and ?3", resultClass = Evento.class),
		@NamedNativeQuery(name = "Evento.findByDataPaciente", query = "select * from evento where dataevento between ?1 and ?2 and idpaciente = ?3 order by dataevento asc", resultClass = Evento.class)
		})

public class Evento implements Serializable {

	private static final long serialVersionUID = 1L;
	public final static String EVENTO_PACIENTE = "select e.idevento,e.descevento,e.dataevento,e.dataregistro,e.enabled,e.obsevento,e.grupoevento,e.subgrupoevento,e.respeventos, p.idpaciente as id_paciente,p.nome as nome_paciente, p.apelido as apelido_paciente, u.idusuario as id_usuario, u.nome as nome_usuario, u.apelido as apelido_usuario from evento e inner join paciente p on e.idpaciente = p.idpaciente inner join usuario u on e.idusuario = u.idusuario where e.enabled = 1 and e.idpaciente=?1 and e.dataevento between DATE_SUB(?2, INTERVAL 1 DAY) and ?3 order by e.dataevento desc";
	public final static String EVENTO_CUIDADOR = "select e.idevento,e.descevento,e.dataevento,e.dataregistro,e.enabled,e.obsevento,e.grupoevento,e.subgrupoevento,e.respeventos,p.idpaciente as id_paciente,p.nome as nome_paciente, p.apelido as apelido_paciente, u.idusuario as id_usuario, u.nome as nome_usuario, u.apelido as apelido_usuario from evento e inner join paciente p on e.idpaciente = p.idpaciente inner join usuario u on e.idusuario = u.idusuario where e.enabled = 1 and e.idusuario=?1  and e.dataevento between DATE_SUB(?2, INTERVAL 1 DAY) and ?3 order by e.dataevento desc";
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	private Integer idevento;
	private String descevento;
	@Basic(optional = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataevento;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataregistro;
	@Basic(optional = false)
	private boolean enabled = true;
	private String obsevento;
	private String grupoevento;
	private String subgrupoevento;
	private String respeventos;
	private Usuario idusuario;
	private Paciente idpaciente;
	@Transient
	private String hour;
	@Transient
    private String data;

	public Evento() {
	}

	public Evento(Integer idevento) {
		this.idevento = idevento;
	}

	public Evento(Integer idevento, Date dataevento, boolean enabled) {
		this.idevento = idevento;
		this.dataevento = dataevento;
		this.enabled = enabled;
	}
	
	

	public Evento(Integer idevento, String descevento, Date dataevento, Date dataregistro, boolean enabled,
			String obsevento, String grupoevento, String subgrupoevento, String respeventos) {
		super();
		this.idevento = idevento;
		this.descevento = descevento;
		this.dataevento = dataevento;
		this.dataregistro = dataregistro;
		this.enabled = enabled;
		this.obsevento = obsevento;
		this.grupoevento = grupoevento;
		this.subgrupoevento = subgrupoevento;
		this.respeventos = respeventos;
	}

	public Integer getIdevento() {
		return idevento;
	}

	public void setIdevento(Integer idevento) {
		this.idevento = idevento;
	}

	public String getDescevento() {
		return descevento;
	}

	public void setDescevento(String descevento) {
		this.descevento = descevento;
	}

	public Date getDataevento() {
		return dataevento;
	}

	public void setDataevento(Date dataevento) {
		this.dataevento = dataevento;
	}

	public Date getDataregistro() {
		return dataregistro;
	}

	public void setDataregistro(Date dataregistro) {
		this.dataregistro = dataregistro;
	}

	public boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getObsevento() {
		return obsevento;
	}

	public void setObsevento(String obsevento) {
		this.obsevento = obsevento;
	}

	public String getGrupoevento() {
		return grupoevento;
	}

	public void setGrupoevento(String grupoevento) {
		this.grupoevento = grupoevento;
	}

	public String getSubgrupoevento() {
		return subgrupoevento;
	}

	public void setSubgrupoevento(String subgrupoevento) {
		this.subgrupoevento = subgrupoevento;
	}

	public String getRespeventos() {
		return respeventos;
	}

	public void setRespeventos(String respeventos) {
		this.respeventos = respeventos;
	}

	@XmlTransient
	public Usuario getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(Usuario idusuario) {
		this.idusuario = idusuario;
	}

	@XmlTransient
	public Paciente getIdpaciente() {
		return idpaciente;
	}

	public void setIdpaciente(Paciente idpaciente) {
		this.idpaciente = idpaciente;
	}

	public String getHour() {
		if (dataevento != null) {
			try {
				hour = DateUtil.convertHour(dataevento);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}
	
	public String getData() {
		String result = null;
		try {
			result = DateUtil.convertDate(getDataevento());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
		
	}

	public void setData(String data) {
		this.data = data;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idevento != null ? idevento.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Evento)) {
			return false;
		}
		Evento other = (Evento) object;
		if ((this.idevento == null && other.idevento != null)
				|| (this.idevento != null && !this.idevento.equals(other.idevento))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.com.cuidebem.model.Evento[ idevento=" + idevento + " ]";
	}

	
}
