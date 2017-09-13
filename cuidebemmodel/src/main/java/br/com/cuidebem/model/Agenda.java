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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(catalog = "cuidebemres", schema = "", name="agenda")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Agenda.findAll", query = "SELECT a FROM Agenda a")
    , @NamedQuery(name = "Agenda.findByIdagenda", query = "SELECT a FROM Agenda a WHERE a.idagenda = :idagenda")
    , @NamedQuery(name = "Agenda.findByIdPaciente", query = "SELECT a FROM Agenda a WHERE a.idpaciente = :idpaciente and a.data between :data1 and :data2 order by a.data asc")
    , @NamedQuery(name = "Agenda.findByData", query = "SELECT a FROM Agenda a WHERE a.data = :data")
    , @NamedQuery(name = "Agenda.findByDataregistro", query = "SELECT a FROM Agenda a WHERE a.dataregistro = :dataregistro")
    , @NamedQuery(name = "Agenda.findByGrupoEvento", query = "SELECT a FROM Agenda a WHERE a.grupoEvento = :grupoEvento")
    , @NamedQuery(name = "Agenda.findBySubGrupoEvento", query = "SELECT a FROM Agenda a WHERE a.subGrupoEvento = :subGrupoEvento")
    , @NamedQuery(name = "Agenda.findByObservacao", query = "SELECT a FROM Agenda a WHERE a.observacao = :observacao")
    , @NamedQuery(name = "Agenda.countByAgendaDef", query = "SELECT COUNT(a) FROM Agenda a WHERE a.idagendadef = :idagendadef")})
@NamedNativeQueries({
	@NamedNativeQuery(name="Agenda.next",query="select * from agenda where idpaciente=?1 and data > date_sub(now(), interval 4 hour) and dataregistro is null order by data asc limit 1",resultClass=Agenda.class)
	
})
public class Agenda implements Serializable {

	public static final String SAVE_LIST = "insert into agenda(data,grupoEvento,subGrupoEvento,observacao,idagendadef,idpaciente) values {0}";
	public static final String VALUES = "(?,?,?,?,?,?)";
	public static final String DELETE_BYAGENDADEF = "delete from agenda where dataregistro is null and idagendadef = {0} ";
	
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer idagenda;
    @Basic(optional = false)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataregistro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    private String grupoEvento;
    @Size(max = 255)
    private String subGrupoEvento;
    @Size(max = 255)
    private String observacao;
    private Integer idagendadef;
    private Integer idpaciente;
	private Integer idevento;

	
	
	public Agenda() {
	
	}
	
	public Agenda(Agendadef agendadef) {
		this.idagendadef = agendadef.getIdagendadef();
		this.idpaciente = agendadef.getIdpaciente();
		this.grupoEvento = agendadef.getGrupoevento();
		this.subGrupoEvento = agendadef.getSubgrupoevento();
		this.observacao = agendadef.getObservacao();
	}
	public Integer getIdagenda() {
		return idagenda;
	}
	public void setIdagenda(Integer idagenda) {
		this.idagenda = idagenda;
	}
	public Integer getIdpaciente() {
		return idpaciente;
	}
	public void setIdpaciente(Integer idpaciente) {
		this.idpaciente = idpaciente;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getGrupoEvento() {
		return grupoEvento;
	}
	public void setGrupoEvento(String grupoEvento) {
		this.grupoEvento = grupoEvento;
	}
	public String getSubGrupoEvento() {
		return subGrupoEvento;
	}
	public void setSubGrupoEvento(String subGrupoEvento) {
		this.subGrupoEvento = subGrupoEvento;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
	
	public Integer getIdagendadef() {
		return idagendadef;
	}

	public void setIdagendadef(Integer idagendadef) {
		this.idagendadef = idagendadef;
	}

	public Date getDataregistro() {
		return dataregistro;
	}

	public void setDataregistro(Date dataregistro) {
		this.dataregistro = dataregistro;
	}
	
	public Integer getIdevento() {
		return idevento;
	}

	public void setIdevento(Integer idevento) {
		this.idevento = idevento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idagenda == null) ? 0 : idagenda.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Agenda other = (Agenda) obj;
		if (idagenda == null) {
			if (other.idagenda != null)
				return false;
		} else if (!idagenda.equals(other.idagenda))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Agenda [idagenda=" + idagenda + ", data=" + data + ", dataregistro=" + dataregistro + ", grupoEvento="
				+ grupoEvento + ", subGrupoEvento=" + subGrupoEvento + ", observacao=" + observacao + ", idpaciente=" + idpaciente + "]";
	}
	
	
	
	

}
