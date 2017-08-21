package br.com.cuidebem.view.agenda;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.ListDataModel;
import javax.inject.Named;

import br.com.cuidebem.controller.AgendaDefFacade;
import br.com.cuidebem.controller.AgendaFacade;
import br.com.cuidebem.controller.PacienteFacade;
import br.com.cuidebem.controller.exception.ControllerException;
import br.com.cuidebem.model.Agendadef;
import br.com.cuidebem.model.Paciente;
import br.com.cuidebem.translate.Bundle;
import br.com.cuidebem.view.IndexView;
import br.com.cuidebem.view.util.JsfUtil;

@Named("agendasmb")
@RequestScoped
public class AgendasView extends IndexView{

	
	private ListDataModel<Agendadef> listAgendadef;
	private Paciente paciente;
	@EJB
	private AgendaDefFacade agendadefFacade;
	@EJB
	private PacienteFacade pacienteFacade;
	@EJB 
	private AgendaFacade agendaFacade; 
	@PostConstruct
	private void init() {
		String _idpaciente = JsfUtil.getRequestParameter("idpaciente");
		if(_idpaciente == null){
			return;
		}
		Integer idpaciente = Integer.valueOf(_idpaciente);
		try {
			paciente = pacienteFacade.find(idpaciente);
			listAgendadef = new ListDataModel<>(agendadefFacade.findByPaciente(idpaciente));
		} catch (ControllerException e) {
			JsfUtil.addErrorMessage(Bundle.getValue("error.loadagenda"));

		}
		
	}
	
	public ListDataModel<Agendadef> getListAgendadef() {
		return listAgendadef;
	}

	public void setListAgendadef(ListDataModel<Agendadef> listAgendadef) {
		this.listAgendadef = listAgendadef;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	
	

}
