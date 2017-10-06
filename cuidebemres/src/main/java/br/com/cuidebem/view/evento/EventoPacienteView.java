package br.com.cuidebem.view.evento;

import java.util.Calendar;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.ListDataModel;
import javax.inject.Named;

import br.com.cuidebem.controller.EventoFacade;
import br.com.cuidebem.controller.PacienteFacade;
import br.com.cuidebem.controller.exception.ControllerException;
import br.com.cuidebem.model.Evento;
import br.com.cuidebem.model.Paciente;
import br.com.cuidebem.model.util.DateUtil;
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

	@PostConstruct
	private void init() {
		String _data = JsfUtil.getRequestParameter("dataevento");
		dataEvento = Calendar.getInstance().getTime();
		if(_data!= null){
			try {
				dataEvento = DateUtil.convertDate(_data);
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
	
	 public void onDateSelect(ValueChangeEvent e) {
		 dataEvento = (Date) e.getNewValue();
		 loadEventos();
	 }

	public Date getDataEvento() {
		return dataEvento;
	}

	public void setDataEvento(Date dataEvento) {
		this.dataEvento = dataEvento;
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
	

}
