package br.com.cuidebem.report;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.com.cuidebem.controller.EventoFacade;
import br.com.cuidebem.controller.PacienteFacade;
import br.com.cuidebem.controller.exception.ControllerException;
import br.com.cuidebem.model.Evento;
import br.com.cuidebem.model.Paciente;
import br.com.cuidebem.translate.Bundle;
import br.com.cuidebem.view.IndexView;
import br.com.cuidebem.view.util.JsfUtil;


@Named("pacienteReport")
@RequestScoped
public class PacienteReport extends IndexView{
	
	private List<Paciente> pacientes;
	private Integer idpaciente;
	private Date dataInicial;
	private Date dataFinal;
	private List<Evento> eventos;
	@EJB
	private PacienteFacade pacienteFacade;
	@EJB
	private EventoFacade eventoFacade;
	
	@PostConstruct
	private void init(){
		Date atual = Calendar.getInstance().getTime();
		dataInicial = atual;
		dataFinal = atual;
		eventos = new ArrayList<Evento>();
		try {
			pacientes = pacienteFacade.findEnabled(getIdresidencia());
			
		} catch (ControllerException e) {
			JsfUtil.addErrorMessage(Bundle.getValue("error.loadpacientes"));
		}
	}
	
	public void search(){
		try {
			eventos = eventoFacade.findByPacienteDataregistro(idpaciente, dataInicial,dataFinal);
		} catch (ControllerException e) {
			JsfUtil.addErrorMessage(Bundle.getValue("error.loadeventos"));
		}
	}


	public Integer getIdpaciente() {
		return idpaciente;
	}

	public void setIdpaciente(Integer idpaciente) {
		this.idpaciente = idpaciente;
	}

	public Date getDataInicial() {
		return dataInicial;
	}
	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}
	public Date getDataFinal() {
		return dataFinal;
	}
	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}
	public List<Evento> getEventos() {
		return eventos;
	}
	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}
	public PacienteFacade getPacienteFacade() {
		return pacienteFacade;
	}
	public void setPacienteFacade(PacienteFacade pacienteFacade) {
		this.pacienteFacade = pacienteFacade;
	}
	public EventoFacade getEventoFacade() {
		return eventoFacade;
	}
	public void setEventoFacade(EventoFacade eventoFacade) {
		this.eventoFacade = eventoFacade;
	}

	public List<Paciente> getPacientes() {
		return pacientes;
	}

	public void setPacientes(List<Paciente> pacientes) {
		this.pacientes = pacientes;
	}


	
	

}
