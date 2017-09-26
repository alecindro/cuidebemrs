package br.com.cuidebem.view;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.ListDataModel;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;

import br.com.cuidebem.controller.TelefoneFacade;
import br.com.cuidebem.controller.UsersFacade;
import br.com.cuidebem.controller.UsuarioFacade;
import br.com.cuidebem.controller.UsuarioPhotoFacade;
import br.com.cuidebem.controller.UsuarioTelefoneFacade;
import br.com.cuidebem.controller.exception.ControllerException;
import br.com.cuidebem.model.Telefone;
import br.com.cuidebem.model.Users;
import br.com.cuidebem.model.def.Operadoras;
import br.com.cuidebem.model.def.TipoTelefone;
import br.com.cuidebem.translate.Bundle;
import br.com.cuidebem.view.util.JsfUtil;
import br.com.cuidebem.view.util.UtilSecurity;

@Named("usuariomb")
@RequestScoped
public class UsuarioView extends IndexView {

	private String newPassword;
	private String repeatPassword;
	@EJB
	private UsuarioTelefoneFacade usuarioTelefoneFacade;
	@EJB
	private UsuarioFacade usuarioFacade;
	@EJB
	private UsersFacade usersFacade;
	@EJB
	private TelefoneFacade telefoneFacade;
	@EJB
	private UsuarioPhotoFacade usuarioPhotoFacade;

	private ListDataModel<Telefone> telefones;
	private Telefone telefone;


	public UsuarioView() {
	}

	@PostConstruct
	private void init() {
		telefone = new Telefone();
		telefones = new ListDataModel<>();
		loadTelefones();

	}
	
	public void updatePassword(){
		Users user = usersFacade.find(UtilSecurity.getUser());
		try {
			usersFacade.updatePassword(user, newPassword, repeatPassword);
			JsfUtil.addSuccessMessage(Bundle.getValue("cadsucesso"));
		} catch (ControllerException e) {
			JsfUtil.addErrorMessage(e.getMessage());
			}
	}

	public void edit() {
		try {
			setUsuario(usuarioFacade.editUsuario(getResidencia(), getUsuario()));
			JsfUtil.addSuccessMessage(Bundle.getValue("cadsucesso"));
		} catch (ControllerException e) {
			JsfUtil.addErrorMessage(e.getMessage());
		}
	}

	public void handleFileUpload(FileUploadEvent event) {
		try {
			Integer idusuario = getUsuario().getIdusuario();
			usuarioPhotoFacade.create(event.getFile().getInputstream(), idusuario);
			JsfUtil.addSuccessMessage(Bundle.getValue("cadimagesucesso"));
		} catch (ControllerException e) {
			JsfUtil.addErrorMessage(e.getMessage());
		} catch (IOException e) {
			JsfUtil.addErrorMessage(e, Bundle.getValue("errorprocessfoto"));
		} catch (Exception e) {
			JsfUtil.addErrorMessage(e, Bundle.getValue("errorprocessfoto"));
		}
	}

	public void savePhone() {
		try {
			telefoneFacade.save(telefone, getUsuario());
			loadTelefones();
			telefone = new Telefone();
			JsfUtil.addSuccessMessage(Bundle.getValue("cadsucesso"));
		} catch (ControllerException e) {
			JsfUtil.addErrorMessage(e.getMessage());
		}
	}

	private void loadTelefones() {
		try {
			telefones.setWrappedData(usuarioTelefoneFacade.getTelefones(getUsuario()));
		} catch (ControllerException e) {
			JsfUtil.addErrorMessage(e, e.getMessage());
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

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

	public List<Operadoras> getOperadoras() {
		return Arrays.asList(Operadoras.values());
	}

	public List<TipoTelefone> getTipoTelefones() {
		return Arrays.asList(TipoTelefone.values());
	}

	public ListDataModel<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(ListDataModel<Telefone> telefones) {
		this.telefones = telefones;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getRepeatPassword() {
		return repeatPassword;
	}

	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}

}
