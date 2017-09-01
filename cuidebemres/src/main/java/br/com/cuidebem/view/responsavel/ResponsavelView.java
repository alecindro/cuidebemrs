package br.com.cuidebem.view.responsavel;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.html.HtmlInputHidden;
import javax.faces.model.ListDataModel;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;

import br.com.cuidebem.controller.PacienteFacade;
import br.com.cuidebem.controller.ResponsavelFacade;
import br.com.cuidebem.controller.ResponsavelPhotoFacade;
import br.com.cuidebem.controller.ResponsavelTelefoneFacade;
import br.com.cuidebem.controller.TelefoneFacade;
import br.com.cuidebem.controller.exception.ControllerException;
import br.com.cuidebem.model.Paciente;
import br.com.cuidebem.model.Responsavel;
import br.com.cuidebem.model.Telefone;
import br.com.cuidebem.translate.Bundle;
import br.com.cuidebem.view.IndexView;
import br.com.cuidebem.view.util.JsfUtil;

@Named("responsavelmb")
@RequestScoped

public class ResponsavelView extends IndexView {

	
	private Responsavel responsavel;
	private Telefone telefone;
	private Paciente paciente;
	
	private ListDataModel<Telefone> telefones;
	@EJB
	private PacienteFacade pacienteFacade;
	@EJB
	private ResponsavelFacade responsavelFacade;
	@EJB
	private ResponsavelPhotoFacade responsavelPhotoFacade;
	@EJB
	private TelefoneFacade telefoneFacade;
	@EJB
	private ResponsavelTelefoneFacade responsavelTelefoneFacade;

	@PostConstruct
	private void init(){
		responsavel = new Responsavel();
		telefone = new Telefone();
		paciente = new Paciente();
		telefones = new ListDataModel<>();
		String _idpaciente = JsfUtil.getRequestParameter("idpaciente");
		if(_idpaciente == null){
			return;
		}
		paciente = pacienteFacade.find(Integer.valueOf(_idpaciente));
		String _idresponsavel = JsfUtil.getRequestParameter("idresponsavel");
		if(_idresponsavel!= null){
			responsavel = responsavelFacade.find(Integer.valueOf(_idresponsavel));
			loadTelefones();
		}
	}
	
	public String deleteResponsavel(){
		try {
			responsavelFacade.delResponsavel(responsavel);
			JsfUtil.addSuccessMessage(Bundle.getValue("delsucesso"));
			return "/app/paciente/paciente.xhtml?idpaciente="+paciente.getIdpaciente();
		} catch (ControllerException e) {
			JsfUtil.addErrorMessage(e.getMessage());
		}
		return null;
		
	}
	
	public void saveResponsavel() {
		try {
			if (responsavel.getNome() == null) {
				JsfUtil.addErrorMessage(Bundle.getValue("requiredmessage_nome"));
				return;
			}
			responsavel = responsavelFacade.save(responsavel, paciente);
			loadTelefones();
			JsfUtil.addSuccessMessage(Bundle.getValue("cadsucesso"));
			
		} catch (ControllerException e) {
			JsfUtil.addErrorMessage(e, "error.saveresponsavel");
		}
	
	}

	public void savePhoneResponsavel() {
		if (responsavel.getIdresponsavel() != null) {
			responsavel = responsavelFacade.find(responsavel.getIdresponsavel());
			try {
				telefoneFacade.save(telefone, responsavel);
				loadTelefones();
				telefone = new Telefone();
				JsfUtil.addSuccessMessage(Bundle.getValue("cadsucesso"));
			} catch (ControllerException e) {
				JsfUtil.addErrorMessage(e.getMessage());
			}
		} else {
			JsfUtil.addErrorMessage(Bundle.getValue("error.save"));
		}

	}

	
	
	public void handlePhotoResponsavel(FileUploadEvent event) {
		try {
			Integer idresponsavel = Integer.valueOf(
					(String) ((HtmlInputHidden) event.getComponent().getChildren().get(0)).getSubmittedValue());
			responsavelPhotoFacade.create(event.getFile().getInputstream(), idresponsavel);
			JsfUtil.addSuccessMessage(Bundle.getValue("cadimagesucesso"));
		} catch (ControllerException e) {
			JsfUtil.addErrorMessage(e.getMessage());
		} catch (IOException e) {
			JsfUtil.addErrorMessage(e, Bundle.getValue("errorprocessfoto"));
		} catch (Exception e) {
			JsfUtil.addErrorMessage(e, Bundle.getValue("errorprocessfoto"));
		}

	}
	
	public void delPhone() {
		try {
			telefone = telefones.getRowData();
			telefoneFacade.remove(telefone);
			loadTelefones();
			JsfUtil.addSuccessMessage(Bundle.getValue("delsucesso"));
		} catch (ControllerException e) {
			JsfUtil.addErrorMessage(e.getMessage());
		}

	}
	
	private void loadTelefones(){
		try {
			telefones = new ListDataModel<>(responsavelTelefoneFacade.getTelefones(responsavel));
		} catch (ControllerException e) {
			JsfUtil.addErrorMessage(e, Bundle.getValue("error.loadtelefoneresponsavel"));
		}
	}
	
	public Responsavel getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Responsavel responsavel) {
		this.responsavel = responsavel;
	}

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}
	
	public ListDataModel<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(ListDataModel<Telefone> telefones) {
		this.telefones = telefones;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}





}
