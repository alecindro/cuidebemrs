package br.com.cuidebem.model.view;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.cuidebem.model.Agenda;

@Entity
@SqlResultSetMapping(name="eventoatual", entities=@EntityResult(entityClass=PacienteEventoAtual.class)) 
@NamedNativeQuery(name="PacienteEventoAtual.findAllByResidencia", query="  select p.idpaciente,p.apelido,  count(s.idpaciente) as qtdade, (count(s.idpaciente) != 0) as enabled  "
		+ "from paciente p left join "
		+ "(select e.grupoevento, e.idpaciente, e.idusuario, e.dataevento "
		+ "from evento e where date(e.dataevento) = date(now()) "
		+ ") s on p.idpaciente = s.idpaciente "
		+ "where p.idresidencia = ?1 and p.enabled =1 "
		+ "group by p.idpaciente;", resultSetMapping="eventoatual")
@XmlRootElement
public class PacienteEventoAtual implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private Integer idpaciente;
	private Integer qtdade;
	private String apelido;
	private Boolean enabled;
	@Transient
	private Agenda agenda;
	
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
	
	public Agenda getAgenda() {
		return agenda;
	}
	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
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
		if (qtdade == null) {
			if (other.qtdade != null)
				return false;
		} else if (!qtdade.equals(other.qtdade))
			return false;
		return true;
	}
	
	
	
	
}
