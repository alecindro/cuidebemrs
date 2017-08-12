package br.com.cuidebem.model;

import java.io.Serializable;
import java.util.Date;

public class Agenda implements Serializable{


	private static final long serialVersionUID = 1L;
	
	private Integer idAgenda;
	private Integer idAgendadef;
	private Integer idPaciente;
	private Date data;
	private String grupoEvento;
	private String subGrupoEvento;
	private String observacao;
	private boolean made;
	
	
	
	public Agenda() {
	
	}
	
	public Agenda(AgendaDef agendaDef) {
		this.idAgendadef = agendaDef.getIdAgendadef();
		this.idPaciente = agendaDef.getIdpaciente();
		this.grupoEvento = agendaDef.getGrupoEvento();
		this.subGrupoEvento = agendaDef.getSubGrupoEvento();
		this.observacao = agendaDef.getObservacao();
		this.made = false;
	}
	public Integer getIdAgenda() {
		return idAgenda;
	}
	public void setIdAgenda(Integer idAgenda) {
		this.idAgenda = idAgenda;
	}
	public Integer getIdPaciente() {
		return idPaciente;
	}
	public void setIdPaciente(Integer idPaciente) {
		this.idPaciente = idPaciente;
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
	public boolean isMade() {
		return made;
	}
	public void setMade(boolean made) {
		this.made = made;
	}
	public Integer getIdAgendadef() {
		return idAgendadef;
	}
	public void setIdAgendadef(Integer idAgendadef) {
		this.idAgendadef = idAgendadef;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idAgenda == null) ? 0 : idAgenda.hashCode());
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
		if (idAgenda == null) {
			if (other.idAgenda != null)
				return false;
		} else if (!idAgenda.equals(other.idAgenda))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Agenda [idAgenda=" + idAgenda + ", idAgendadef=" + idAgendadef + ", data=" + data + ", grupoEvento="
				+ grupoEvento + ", subGrupoEvento=" + subGrupoEvento + ", observacao=" + observacao + ", made=" + made
				+ "]";
	}
	
	
	
	

}
