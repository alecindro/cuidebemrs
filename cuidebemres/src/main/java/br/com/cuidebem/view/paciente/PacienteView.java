package br.com.cuidebem.view.paciente;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.html.HtmlInputHidden;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;

import br.com.cuidebem.controller.PacienteFacade;
import br.com.cuidebem.controller.PacientePhotoFacade;
import br.com.cuidebem.controller.PatologiaPacienteFacade;
import br.com.cuidebem.controller.ResponsavelFacade;
import br.com.cuidebem.controller.exception.ControllerException;
import br.com.cuidebem.model.Paciente;
import br.com.cuidebem.model.PatologiaPaciente;
import br.com.cuidebem.model.Responsavel;
import br.com.cuidebem.model.def.Patologias;
import br.com.cuidebem.translate.Bundle;
import br.com.cuidebem.view.IndexView;
import br.com.cuidebem.view.util.JsfUtil;

@Named("pacientemb")
@RequestScoped
public class PacienteView extends IndexView {

	
	private Paciente paciente;
	private String[] selected_patologias;
	private List<SelectItem> patologias;
	private ListDataModel<Responsavel> responsaveis;
	@EJB
	private PacienteFacade pacienteFacade;
	@EJB
	private ResponsavelFacade responsavelFacade;
	@EJB
	private PacientePhotoFacade photoFacade;
	@EJB
	private PatologiaPacienteFacade patologiaFacade;

	@PostConstruct
	private void init() {
		paciente = new Paciente();
		loadPatologias();
		String _id = JsfUtil.getRequestParameter("idpaciente");
		if (_id != null) {
			Integer idpaciente = Integer.valueOf(_id);
			paciente = pacienteFacade.find(idpaciente);
			fillPatologias();
			loadResponsaveis();
		} else {
			responsaveis = new ListDataModel<>();
		}

	}

	public void savePaciente() {
		try {
			paciente = pacienteFacade.save(paciente, getResidencia());
			loadPatologias();
			fillPatologias();
			loadResponsaveis();
			JsfUtil.addSuccessMessage(Bundle.getValue("cadsucesso"));
		} catch (ControllerException e) {
			JsfUtil.addErrorMessage(Bundle.getValue("error.save"));
		}
	}

	public String delPaciente() {
		try {
			pacienteFacade.delete(paciente);
			JsfUtil.addSuccessMessage(Bundle.getValue("delsucess"));
			return "/app/paciente/pacientes.xhtml";
		} catch (ControllerException e) {
			JsfUtil.addErrorMessage(Bundle.getValue("error.delelete"));
		}
		return null;
	}

	private void fillPatologias() {
		try {
			if (paciente.getIdpaciente() != null) {
				List<PatologiaPaciente> patologias = patologiaFacade.findById(paciente.getIdpaciente());
				selected_patologias = new String[patologias.size()];
				int i = 0;
				for (PatologiaPaciente patologiaPaciente : patologias) {
					selected_patologias[i] = patologiaPaciente.getPatologia();
					i = i + 1;
				}
			}
		} catch (ControllerException e) {
			JsfUtil.addErrorMessage(Bundle.getValue("error.loadpatologias"));
		}
	}

	private void loadPatologias() {
		patologias = new ArrayList<SelectItem>();
		for (Patologias _patologia : Patologias.values()) {
			patologias.add(new SelectItem(_patologia.getDescricao(), _patologia.getDescricao()));
		}
	}

	

	private void loadResponsaveis() {
		try {
			responsaveis = new ListDataModel<Responsavel>(responsavelFacade.loadByPaciente(paciente.getIdpaciente()));
		} catch (ControllerException e) {
			JsfUtil.addErrorMessage(e, Bundle.getValue("error.loadreponsaveis"));
		}
	}

	public void handleFileUpload(FileUploadEvent event) {
		try {
			Integer idpaciente = Integer.valueOf(
					(String) ((HtmlInputHidden) event.getComponent().getChildren().get(0)).getSubmittedValue());
			photoFacade.create(event.getFile().getInputstream(), idpaciente, event.getFile().getContentType());
			JsfUtil.addSuccessMessage(Bundle.getValue("cadimagesucesso"));
		} catch (ControllerException e) {
			JsfUtil.addErrorMessage(e.getMessage());
		} catch (IOException e) {
			JsfUtil.addErrorMessage(e, Bundle.getValue("errorprocessfoto"));
		} catch (Exception e) {
			JsfUtil.addErrorMessage(e, Bundle.getValue("errorprocessfoto"));
		}

	}

	
	public void savePatologias() {
		try {
			patologiaFacade.update(selected_patologias, paciente.getIdpaciente());
			JsfUtil.addSuccessMessage(Bundle.getValue("cadsucesso"));
		} catch (ControllerException e) {
			JsfUtil.addErrorMessage(e, Bundle.getValue("error.savepatologias"));
		}
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	
	public String[] getSelected_patologias() {
		return selected_patologias;
	}

	public void setSelected_patologias(String[] selected_patologias) {
		this.selected_patologias = selected_patologias;
	}

	public List<SelectItem> getPatologias() {
		if (patologias == null) {
			loadPatologias();
		}
		return patologias;
	}

	public void setPatologias(List<SelectItem> patologias) {
		this.patologias = patologias;
	}

	public ListDataModel<Responsavel> getResponsaveis() {
		if (responsaveis == null) {
			loadResponsaveis();
		}
		return responsaveis;
	}

	public void setResponsaveis(ListDataModel<Responsavel> responsaveis) {
		this.responsaveis = responsaveis;
	}
	

}
