package br.com.cuidebem.view.paciente;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.ListDataModel;
import javax.inject.Named;

import br.com.cuidebem.controller.PacienteFacade;
import br.com.cuidebem.controller.exception.ControllerException;
import br.com.cuidebem.model.Paciente;
import br.com.cuidebem.translate.Bundle;
import br.com.cuidebem.view.IndexView;
import br.com.cuidebem.view.util.JsfUtil;

@Named("pacientesmb")
@RequestScoped
public class PacientesView extends IndexView {

	private ListDataModel<Paciente> pacientes;
	@EJB
	private PacienteFacade pacienteFacade;

	@PostConstruct
	private void init() {
		loadPacientes(getIdresidencia());
	}

	public ListDataModel<Paciente> getPacientes() {
		return pacientes;
	}

	public void setPacientes(ListDataModel<Paciente> pacientes) {
		this.pacientes = pacientes;
	}

	private void loadPacientes(int idresidencia) {
		try {
			pacientes = new ListDataModel<>(pacienteFacade.findEnabled(idresidencia));
		} catch (ControllerException e) {
			JsfUtil.addErrorMessage(e, Bundle.getValue("error.loadpacientes"));
		}

	}

}
