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

@Named("pacientesexcludemb")
@RequestScoped
public class PacientesExcluidosView extends IndexView {

	private ListDataModel<Paciente> pacientes;
	@EJB
	private PacienteFacade pacienteFacade;

	@PostConstruct
	private void init() {
		loadExcluidos(getIdresidencia());
	}

	public String enabledPaciente() {
		try {
			pacienteFacade.enabled(pacientes.getRowData());
			return "/app/paciente/pacientes.xhtml";
		} catch (ControllerException e) {
			JsfUtil.addErrorMessage(Bundle.getValue("errorenabledpaciente"));
		}
		return null;
	}

	public void loadExcluidos(int idresidencia) {
		try {
			pacientes = new ListDataModel<>(pacienteFacade.findDisabled(idresidencia));
		} catch (ControllerException e) {
			JsfUtil.addErrorMessage(e, Bundle.getValue("error.loadpacientes"));
		}
	}

	public ListDataModel<Paciente> getPacientes() {
		return pacientes;
	}

	public void setPacientes(ListDataModel<Paciente> pacientes) {
		this.pacientes = pacientes;
	}

}
