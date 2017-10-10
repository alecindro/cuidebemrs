package br.com.cuidebem.model.view;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.cuidebem.model.def.Check;

@Entity
@SqlResultSetMapping(name="eventoatual", entities=@EntityResult(entityClass=PacienteEventoAtual.class)) 
@NamedNativeQuery(name="PacienteEventoAtual.findAllByResidencia", query="select p.idpaciente,p.apelido,  count(s.idpaciente) as qtdade, "
		+ "(count(s.idpaciente) != 0) as enabled, ue.grupoevento as lastgrupoevento, "
		+ "ue.idevento as lastidevento, an.grupoevento as nextgrupoagenda, an.subgrupoevento as nextsubgrupoagenda, "
		+ "an.data as nextdataagenda, an.idagenda as nextidagenda from paciente p left join "
		+ "(select e.grupoevento, e.idpaciente, e.idusuario, e.dataevento "
		+ "from evento e where date(e.dataevento) = date(now())) s on p.idpaciente = s.idpaciente "
		+ "left join (SELECT e1.idpaciente, e1.idevento, e1.grupoevento, e1.dataregistro "
		+ "FROM evento e1 inner join paciente p1 on e1.idpaciente = p1.idpaciente "
		+ "WHERE p1.idresidencia = ?1 and e1.dataregistro = (SELECT MAX(e2.dataregistro) "
		+ "FROM evento e2 inner join paciente p2 on e2.idpaciente = p2.idpaciente "
		+ "WHERE e2.idpaciente = e1.idpaciente and e2.enabled = 1 and p2.idresidencia = ?2)) ue "
		+ "on p.idpaciente = ue.idpaciente "
		+ "left join (SELECT a1.idpaciente, a1.grupoevento, a1.data, a1.subgrupoevento, a1.idagenda "
		+ "FROM agenda a1 inner join paciente p1 on a1.idpaciente = p1.idpaciente "
		+ "WHERE p1.idresidencia = ?3 and a1.data = (SELECT min(a2.data) "
		+ "FROM agenda a2 inner join paciente p2 on a2.idpaciente = p2.idpaciente "
		+ "WHERE a2.idpaciente = a1.idpaciente and p2.idresidencia = ?4 and a2.data > date_sub(now(), interval 1 hour) "
		+ "and a1.dataregistro is null)) "
		+ "an on p.idpaciente = an.idpaciente "
		+ "where p.idresidencia = ?5 and p.enabled =1 "
		+ "group by p.idpaciente;", resultSetMapping="eventoatual")
@XmlRootElement
public class PacienteEventoAtual implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private Integer idpaciente;
	private Integer qtdade;
	private String apelido;
	private Boolean enabled;
	private String lastgrupoevento;
	private Integer lastidevento;
	private String nextgrupoagenda;
	private String nextsubgrupoagenda;
	private Date nextdataagenda;
	private Integer nextidagenda;
	@Transient
	private boolean checkin;
	
	
	public Integer getIdpaciente() {
		return idpaciente;
	}
	public void setIdpaciente(Integer idpaciente) {
		this.idpaciente = idpaciente;
	}
	
	public Integer getQtdade() {
		return qtdade;
	}
	public void setQtdade(Integer qtdade) {
		this.qtdade = qtdade;
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
	
	public String getLastgrupoevento() {
		return lastgrupoevento;
	}
	public void setLastgrupoevento(String lastgrupoevento) {
		this.lastgrupoevento = lastgrupoevento;
	}
	public Integer getLastidevento() {
		return lastidevento;
	}
	public void setLastidevento(Integer lastidevento) {
		this.lastidevento = lastidevento;
	}
	public String getNextgrupoagenda() {
		return nextgrupoagenda;
	}
	public void setNextgrupoagenda(String nextgrupoagenda) {
		this.nextgrupoagenda = nextgrupoagenda;
	}
	public String getNextsubgrupoagenda() {
		return nextsubgrupoagenda;
	}
	public void setNextsubgrupoagenda(String nextsubgrupoagenda) {
		this.nextsubgrupoagenda = nextsubgrupoagenda;
	}
	public Date getNextdataagenda() {
		return nextdataagenda;
	}
	public void setNextdataagenda(Date nextdataagenda) {
		this.nextdataagenda = nextdataagenda;
	}
	public Integer getNextidagenda() {
		return nextidagenda;
	}
	public void setNextidagenda(Integer nextidagenda) {
		this.nextidagenda = nextidagenda;
	}
	
	
	public boolean isCheckin() {
			checkin =  false;
		if(lastgrupoevento != null && lastgrupoevento.compareTo(Check.CHECKOUT.getDescricao())==0){
			checkin =  true;
		}
		return checkin;
	}
	public void setCheckin(boolean checkin) {
		this.checkin = checkin;
	}
	@Override
	public String toString() {
		return "PacienteEventoAtual [idpaciente=" + idpaciente + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apelido == null) ? 0 : apelido.hashCode());
		result = prime * result + ((enabled == null) ? 0 : enabled.hashCode());
		result = prime * result + ((idpaciente == null) ? 0 : idpaciente.hashCode());
		result = prime * result + ((lastgrupoevento == null) ? 0 : lastgrupoevento.hashCode());
		result = prime * result + ((lastidevento == null) ? 0 : lastidevento.hashCode());
		result = prime * result + ((nextdataagenda == null) ? 0 : nextdataagenda.hashCode());
		result = prime * result + ((nextgrupoagenda == null) ? 0 : nextgrupoagenda.hashCode());
		result = prime * result + ((nextidagenda == null) ? 0 : nextidagenda.hashCode());
		result = prime * result + ((nextsubgrupoagenda == null) ? 0 : nextsubgrupoagenda.hashCode());
		result = prime * result + ((qtdade == null) ? 0 : qtdade.hashCode());
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
		PacienteEventoAtual other = (PacienteEventoAtual) obj;
		if (apelido == null) {
			if (other.apelido != null)
				return false;
		} else if (!apelido.equals(other.apelido))
			return false;
		if (enabled == null) {
			if (other.enabled != null)
				return false;
		} else if (!enabled.equals(other.enabled))
			return false;
		if (idpaciente == null) {
			if (other.idpaciente != null)
				return false;
		} else if (!idpaciente.equals(other.idpaciente))
			return false;
		if (lastgrupoevento == null) {
			if (other.lastgrupoevento != null)
				return false;
		} else if (!lastgrupoevento.equals(other.lastgrupoevento))
			return false;
		if (lastidevento == null) {
			if (other.lastidevento != null)
				return false;
		} else if (!lastidevento.equals(other.lastidevento))
			return false;
		if (nextdataagenda == null) {
			if (other.nextdataagenda != null)
				return false;
		} else if (!nextdataagenda.equals(other.nextdataagenda))
			return false;
		if (nextgrupoagenda == null) {
			if (other.nextgrupoagenda != null)
				return false;
		} else if (!nextgrupoagenda.equals(other.nextgrupoagenda))
			return false;
		if (nextidagenda == null) {
			if (other.nextidagenda != null)
				return false;
		} else if (!nextidagenda.equals(other.nextidagenda))
			return false;
		if (nextsubgrupoagenda == null) {
			if (other.nextsubgrupoagenda != null)
				return false;
		} else if (!nextsubgrupoagenda.equals(other.nextsubgrupoagenda))
			return false;
		if (qtdade == null) {
			if (other.qtdade != null)
				return false;
		} else if (!qtdade.equals(other.qtdade))
			return false;
		return true;
	}

	
	
	
}
