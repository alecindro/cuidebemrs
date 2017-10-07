package br.com.cuidebem.agenda;

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
	private Date dataevento;
	private boolean today;
	
	@PostConstruct
	private void init() {
		dataevento = Calendar.getInstance().getTime();
		today = true;
		dataevento = DateUtil.getZeroHour(dataevento);
		String _dataevento = JsfUtil.getRequestParameter("dataevento");
		if(_dataevento != null){
			try {
				
				Date _data = DateUtil.convertDate(_dataevento);
				if(_data.compareTo(DateUtil.getZeroHour(dataevento))!=0){
					today = false;
				}
				dataevento = _data;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		String _idpaciente = JsfUtil.getRequestParameter("idpaciente");
		if (_idpaciente == null) {
			return;
		}
		Integer idpaciente = Integer.valueOf(_idpaciente);
		Date date = Calendar.getInstance().getTime();
		Date dataFim = DateUtil.sumDateDays(date, 1);
		try {
			agendas = new ListDataModel<>(agendaFacade.findByIdPaciente(idpaciente, dataevento, dataFim));
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


	public boolean isToday() {
		return today;
	}


	public void setToday(boolean today) {
		this.today = today;
	}

	
	


	
	
}
