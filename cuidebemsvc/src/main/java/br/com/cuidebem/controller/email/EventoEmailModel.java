package br.com.cuidebem.controller.email;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.cuidebem.model.Evento;
import br.com.cuidebem.model.Responsavel;

public class EventoEmailModel {

	private Date data;
	private String paciente;
	private Integer idpaciente;
	private String memorando;
	private List<Evento> eventos;
	private List<Responsavel> responsaveis;
	private String residencia;
	private Integer idresidencia;

	
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getPaciente() {
		return paciente;
	}
	public void setPaciente(String paciente) {
		this.paciente = paciente;
	}
	
	
	public Integer getIdpaciente() {
		return idpaciente;
	}
	public void setIdpaciente(Integer idpaciente) {
		this.idpaciente = idpaciente;
	}
	public String getMemorando() {
		return memorando;
	}
	public void setMemorando(String memorando) {
		this.memorando = memorando;
	}
	public List<Evento> getEventos() {
		if(eventos == null){
			eventos = new ArrayList<Evento>();
		}
		return eventos;
	}
	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}
	public String getResidencia() {
		return residencia;
	}
	public void setResidencia(String residencia) {
		this.residencia = residencia;
	}
	public List<Responsavel> getResponsaveis() {
		if(responsaveis == null){
			responsaveis = new ArrayList<Responsavel>();
		}
		return responsaveis;
	}
	public void setResponsaveis(List<Responsavel> responsaveis) {
		this.responsaveis = responsaveis;
	}
	
	public Integer getIdresidencia() {
		return idresidencia;
	}
	public void setIdresidencia(Integer idresidencia) {
		this.idresidencia = idresidencia;
	}
	
	

}
