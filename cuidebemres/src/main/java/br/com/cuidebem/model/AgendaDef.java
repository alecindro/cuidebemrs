package br.com.cuidebem.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

public class AgendaDef implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer idAgendadef;
	private Integer idpaciente;
	private Date dataInicio;
	private Date dataFim;
	private String horario;
	private Integer repetirHoras;
	private String[] diasSemana;
	private String grupoEvento;
	private String subGrupoEvento;
	private String observacao;
	private boolean diaspersonalizado;
	
	public Integer getIdAgendadef() {
		return idAgendadef;
	}
	public void setIdAgendadef(Integer idAgendadef) {
		this.idAgendadef = idAgendadef;
	}
	public Date getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	public Date getDataFim() {
		return dataFim;
	}
	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
	public String getHorario() {
		return horario;
	}
	public void setHorario(String horario) {
		this.horario = horario;
	}
	public Integer getRepetirHoras() {
		return repetirHoras;
	}
	public void setRepetirHoras(Integer repetirHoras) {
		this.repetirHoras = repetirHoras;
	}
	public String[] getDiasSemana() {
		return diasSemana;
	}
	public void setDiasSemana(String[] diasSemana) {
		this.diasSemana = diasSemana;
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
	
	public Integer getIdpaciente() {
		return idpaciente;
	}
	public void setIdpaciente(Integer idpaciente) {
		this.idpaciente = idpaciente;
	}
	
	public boolean isDiaspersonalizado() {
		return diaspersonalizado;
	}
	public void setDiaspersonalizado(boolean diaspersonalizado) {
		this.diaspersonalizado = diaspersonalizado;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idAgendadef == null) ? 0 : idAgendadef.hashCode());
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
		AgendaDef other = (AgendaDef) obj;
		if (idAgendadef == null) {
			if (other.idAgendadef != null)
				return false;
		} else if (!idAgendadef.equals(other.idAgendadef))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "AgendaDef [idAgendadef=" + idAgendadef + ", dataInicio=" + dataInicio + ", dataFim=" + dataFim
				+ ", horario=" + horario + ", repetirHoras=" + repetirHoras + ", diasSemana="
				+ Arrays.toString(diasSemana) + ", grupoEvento=" + grupoEvento + ", subGrupoEvento=" + subGrupoEvento
				+ ", observacao=" + observacao + "]";
	}
	
	
	
	
}
