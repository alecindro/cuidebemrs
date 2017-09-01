package br.com.cuidebem.view.agenda;

import java.util.Calendar;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.ListDataModel;
import javax.inject.Named;

import br.com.cuidebem.controller.AgendaFacade;
import br.com.cuidebem.controller.exception.ControllerException;
import br.com.cuidebem.model.Agenda;
import br.com.cuidebem.model.util.DateUtil;
import br.com.cuidebem.view.IndexView;
import br.com.cuidebem.view.util.JsfUtil;

@Named("agendadiaria")
@RequestScoped
public class AgendaDiaria extends IndexView  {

	
	private ListDataModel<Agenda> agendas;
	
	@EJB
	private AgendaFacade agendaFacade;

	
	@PostConstruct
	private void init() {
		String _idpaciente = JsfUtil.getRequestParameter("idpaciente");
		if (_idpaciente == null) {
			return;
		}
		Integer idpaciente = Integer.valueOf(_idpaciente);
		Date date = Calendar.getInstance().getTime();
		Date dataInicio = DateUtil.minHour(date, 4);
		Date dataFim = DateUtil.sumDateDays(date, 1);
		try {
			agendas = new ListDataModel<>(agendaFacade.findByIdPaciente(idpaciente, dataInicio, dataFim));
		} catch (ControllerException e) {
			JsfUtil.addErrorMessage(e.getMessage());
		}

	}


	public ListDataModel<Agenda> getAgendas() {
		return agendas;
	}


	public void setAgendas(ListDataModel<Agenda> agendas) {
		this.agendas = agendas;
	}
	
}
