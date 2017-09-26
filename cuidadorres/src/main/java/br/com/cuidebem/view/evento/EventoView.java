package br.com.cuidebem.view.evento;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;

import br.com.cuidebem.controller.AgendaFacade;
import br.com.cuidebem.controller.EventoFacade;
import br.com.cuidebem.controller.PacienteFacade;
import br.com.cuidebem.controller.exception.ControllerException;
import br.com.cuidebem.model.Agenda;
import br.com.cuidebem.model.Evento;
import br.com.cuidebem.model.Paciente;
import br.com.cuidebem.rotinas.RotinaAgenda;
import br.com.cuidebem.rotinas.Rotinas;
import br.com.cuidebem.translate.Bundle;
import br.com.cuidebem.view.IndexView;
import br.com.cuidebem.view.util.JsfUtil;

@Named("eventomb")
@RequestScoped
public class EventoView extends IndexView{
	
	@EJB
	private AgendaFacade agendaFacade;
	@EJB
	private PacienteFacade pacienteFacade;
	@EJB
	private EventoFacade eventoFacade;
	
	private Paciente paciente;
	private Evento evento;
	private List<String> grupoEvento;
	private List<String> subGrupoEvento;
	private String page;
	private Integer idagenda;
	private boolean rendered = false;
	
	@PostConstruct
	private void init() {
		page = "/app/evento/blank.xhtml";
		evento = new Evento();
		paciente = new Paciente();
		grupoEvento = new ArrayList<>();
		subGrupoEvento = new ArrayList<>();
		String _idagenda = JsfUtil.getRequestParameter("idagenda");
		if(_idagenda != null){
			idagenda = Integer.valueOf(_idagenda);
			loadfromAgenda();
		}else{
			grupoEvento = Rotinas.getGrupoEventos();
			String idpaciente = JsfUtil.getRequestParameter("idpaciente");
			if(idpaciente != null){
			paciente = pacienteFacade.find(Integer.valueOf(idpaciente));
			}
			
		}
	
		
		
	}
	
	private void loadfromAgenda(){
	  	Agenda agenda = agendaFacade.find(idagenda);
	  	evento.setGrupoevento(agenda.getGrupoEvento());
	  	evento.setSubgrupoevento(agenda.getSubGrupoEvento());
	  	evento.setObsevento(agenda.getObservacao());
	  	paciente = pacienteFacade.find(agenda.getIdpaciente());
	  	this.page = RotinaAgenda.getPage(evento.getGrupoevento(), evento.getSubgrupoevento());
	  	if(!page.contentEquals("/app/evento/blank.xhtml")){
	  		rendered = true;
	  	}
	  	grupoEvento.add(agenda.getGrupoEvento());
	  	subGrupoEvento.add(agenda.getSubGrupoEvento());
	}
	
	
	
	public String save(){
		try {
			Date dataevento = Calendar.getInstance().getTime();
			evento.setIdpaciente(paciente.getIdpaciente());
			evento.setDataregistro(dataevento);
			evento.setEnabled(true);
			evento.setIdusuario(getUsuario().getIdusuario());
			Rotinas.genResumo(evento);
			eventoFacade.save(evento, idagenda);
			evento = new Evento();
		} catch (ControllerException e) {
			JsfUtil.addErrorMessage(e.getMessage());
			return null;
		}
		JsfUtil.addSuccessMessage(Bundle.getValue("evento_sucess"));
		return "/app/agenda/tableAgendaDiaria.xhtml?idpaciente="+paciente.getIdpaciente();
	}
	
	public void grupEventoChanged(ValueChangeEvent  e) {
		subGrupoEvento = Rotinas.getSubGrupoEventos(e.getNewValue().toString());
		if(subGrupoEvento != null && subGrupoEvento.size() == 1){
			evento.setSubgrupoevento(subGrupoEvento.get(0));
		}
	}
	public void subGrupEventoChanged(ValueChangeEvent  e) {
		String _grupoevento = JsfUtil.getRequestParameter("grupoevento");
		String _subgrupoevento = e.getNewValue().toString();
		if(_grupoevento!= null && _grupoevento!= "null"){
		this.page = Rotinas.getPage(_grupoevento, _subgrupoevento);
		if(!page.equals("/app/evento/blank.xhtml")){
	  		rendered = true;
	  	}
		}
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public List<String> getGrupoEvento() {
		return grupoEvento;
	}

	public void setGrupoEvento(List<String> grupoEvento) {
		this.grupoEvento = grupoEvento;
	}

	public List<String> getSubGrupoEvento() {
		return subGrupoEvento;
	}

	public void setSubGrupoEvento(List<String> subGrupoEvento) {
		this.subGrupoEvento = subGrupoEvento;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public Integer getIdagenda() {
		return idagenda;
	}

	public void setIdagenda(Integer idagenda) {
		this.idagenda = idagenda;
	}

	public boolean isRendered() {
		return rendered;
	}

	public void setRendered(boolean rendered) {
		this.rendered = rendered;
	}
	
	

	

}
