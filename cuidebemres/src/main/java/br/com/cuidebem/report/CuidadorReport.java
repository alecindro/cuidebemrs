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
import br.com.cuidebem.controller.UsuarioFacade;
import br.com.cuidebem.controller.exception.ControllerException;
import br.com.cuidebem.model.Evento;
import br.com.cuidebem.model.Usuario;
import br.com.cuidebem.translate.Bundle;
import br.com.cuidebem.view.IndexView;
import br.com.cuidebem.view.util.JsfUtil;

@Named("cuidadorReport")
@RequestScoped
public class CuidadorReport extends IndexView {

	private List<Usuario> cuidadores;
	private Integer idusuario;
	private Date dataInicial;
	private Date dataFinal;
	private List<Evento> eventos;
	@EJB
	private UsuarioFacade usuarioFacade;
	@EJB
	private EventoFacade eventoFacade;

	@PostConstruct
	private void init() {
		Date atual = Calendar.getInstance().getTime();
		dataInicial = atual;
		dataFinal = atual;
		eventos = new ArrayList<Evento>();
		try {
			cuidadores = usuarioFacade.findCuidadoresEnabled(getIdresidencia());
		} catch (ControllerException e) {
			JsfUtil.addErrorMessage(Bundle.getValue("error.loadcuidadores"));
		}
	}
	
	public void search(){
		try {
			eventos = eventoFacade.findByCuidadorDataregistro(idusuario, dataInicial,dataFinal);
		} catch (ControllerException e) {
			JsfUtil.addErrorMessage(Bundle.getValue("error.loadeventos"));
		}
	}

		public List<Usuario> getCuidadores() {
		return cuidadores;
	}

	public void setCuidadores(List<Usuario> cuidadores) {
		this.cuidadores = cuidadores;
	}

	public Integer getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(Integer idusuario) {
		this.idusuario = idusuario;
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
	
	
}
