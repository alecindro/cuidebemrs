package br.com.cuidebem.view.evento;

import java.util.Calendar;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.ListDataModel;
import javax.inject.Named;

import br.com.cuidebem.controller.EventoFacade;
import br.com.cuidebem.controller.PacienteFacade;
import br.com.cuidebem.controller.exception.ControllerException;
import br.com.cuidebem.model.Evento;
import br.com.cuidebem.model.Paciente;
import br.com.cuidebem.model.util.DateUtil;
import br.com.cuidebem.translate.Bundle;
import br.com.cuidebem.view.IndexView;
import br.com.cuidebem.view.util.JsfUtil;

@Named("eventopacientemb")
@RequestScoped
public class EventoPacienteView extends IndexView {

	private ListDataModel<Evento> eventos;
	private Date dataEvento;
	@EJB
	private EventoFacade eventoFacade;
	@EJB
	private PacienteFacade pacienteFacade;
	private Integer idpaciente;
	private Paciente paciente;
	private boolean today;

	@PostConstruct
	private void init() {
		dataEvento = Calendar.getInstance().getTime();
		today = true;
		String _dataevento = JsfUtil.getRequestParameter("dataevento");
		if(_dataevento != null){
			try {
				Date _data = DateUtil.convertDate(_dataevento);
				if(!(DateUtil.getZeroHour(dataEvento).equals(DateUtil.getZeroHour(_data)))){
					today = false;
				}
				dataEvento = _data;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		String _id = JsfUtil.getRequestParameter("idpaciente");
		if (_id != null) {
			idpaciente = Integer.valueOf(_id);
			loadPaciente();
			loadEventos();
		} else {
			JsfUtil.addErrorMessage("error.loadeventos");
		}
	}
	
	private void loadPaciente(){
		paciente = pacienteFacade.find(idpaciente);
	}

	private void loadEventos() {
		if (idpaciente != null) {
			try {
				eventos = new ListDataModel<>(eventoFacade.findByPacienteDataregistro(idpaciente, dataEvento));
			} catch (ControllerException e) {
				JsfUtil.addErrorMessage(e, "error.loadeventos");
			}
		}
	}
	
	public void excluirEvento(){
		try {
			eventoFacade.delete(eventos.getRowData().getIdevento());
			loadEventos();
			JsfUtil.addSuccessMessage(Bundle.getValue("deleventosucess"));
		} catch (ControllerException e) {
			JsfUtil.addErrorMessage(e, Bundle.getValue("error.delete"));
		}
	}

	public ListDataModel<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(ListDataModel<Evento> eventos) {
		this.eventos = eventos;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Date getDataEvento() {
		return dataEvento;
	}

	public void setDataEvento(Date dataEvento) {
		this.dataEvento = dataEvento;
	}

	public boolean isToday() {
		return today;
	}

	public void setToday(boolean today) {
		this.today = today;
	}
	
	

}
