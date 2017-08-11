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

	@PostConstruct
	private void init() {
		dataEvento = Calendar.getInstance().getTime();
		String _id = JsfUtil.getRequestParameter("idpaciente");
		if (_id != null) {
			idpaciente = Integer.valueOf(_id);
			loadEventos();
		} else {
			JsfUtil.addErrorMessage("error.loadeventos");
		}
	}

	private void loadEventos() {
		if (idpaciente != null) {
			try {
				eventos = new ListDataModel<>(eventoFacade.findEnabledByPaciente(idpaciente, dataEvento));
			} catch (ControllerException e) {
				JsfUtil.addErrorMessage(e, "error.loadeventos");
			}
		}
	}

	public ListDataModel<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(ListDataModel<Evento> eventos) {
		this.eventos = eventos;
	}

}
