package br.com.cuidebem.view.agenda;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.cuidebem.controller.AgendaDefFacade;
import br.com.cuidebem.controller.PacienteFacade;
import br.com.cuidebem.controller.exception.ControllerException;
import br.com.cuidebem.model.Agendadef;
import br.com.cuidebem.model.Paciente;
import br.com.cuidebem.model.util.DateUtil;
import br.com.cuidebem.rotinas.Rotinas;
import br.com.cuidebem.view.IndexView;
import br.com.cuidebem.view.util.JsfUtil;

@Named("agendadefmb")
@RequestScoped
public class AgendaDefView extends IndexView {

	@EJB
	private AgendaDefFacade agendaDefFacade;
	@EJB
	private PacienteFacade pacienteFacade;
	@Inject
	private Agendadef agendadef;
	@Inject
	private Paciente paciente;
	

	private List<String> grupoEvento;
	private List<String> subGrupoEvento;
	

	@PostConstruct
	private void init() {
		grupoEvento = Rotinas.getGrupoEventos();
		subGrupoEvento = new ArrayList<String>();
		String _idagendadef = JsfUtil.getRequestParameter("idagendadef");
		if(_idagendadef != null){
			editAgendaDef(Integer.valueOf(_idagendadef));
		}else{
			String _idpaciente = JsfUtil.getRequestParameter("idpaciente");
			if(_idpaciente != null){
			newAgenda(Integer.valueOf(_idpaciente));
			}
		}
	}
	
	private void editAgendaDef(Integer idagendadef){
			agendadef = agendaDefFacade.find(idagendadef);
			subGrupoEvento = Rotinas.getSubGrupoEventos(agendadef.getGrupoevento());
			paciente = pacienteFacade.find(agendadef.getIdpaciente());
	}
	
	private void newAgenda(Integer idpaciente){
		paciente = pacienteFacade.find(idpaciente);
		Date atual = Calendar.getInstance().getTime();
		agendadef.setIdpaciente(idpaciente);
		agendadef.setDatainicio(atual);
		agendadef.setDatafim(atual);
		agendadef.setDiaspersonalizado(false);
		agendadef.setHorario(DateUtil.convertHour(atual));

	}

	public String save() {
		try {
			agendaDefFacade.save(agendadef);
		} catch (ControllerException e) {
			JsfUtil.addErrorMessage(e.getMessage());
			return null;
		}
		return "/app/paciente/listagenda.xhtml?idpaciente="+paciente.getIdpaciente();
	}
	
	
	
	public void grupEventoChanged(ValueChangeEvent  e) {
		subGrupoEvento = Rotinas.getSubGrupoEventos(e.getNewValue().toString());
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

	public Agendadef getAgendadef() {
		return agendadef;
	}

	public void setAgendadef(Agendadef agendadef) {
		this.agendadef = agendadef;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	
	

	
}
