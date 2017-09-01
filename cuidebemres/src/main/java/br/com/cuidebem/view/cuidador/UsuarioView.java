package br.com.cuidebem.view.cuidador;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.html.HtmlInputHidden;
import javax.faces.model.ListDataModel;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;

import br.com.cuidebem.controller.RolesFacade;
import br.com.cuidebem.controller.TelefoneFacade;
import br.com.cuidebem.controller.UsersFacade;
import br.com.cuidebem.controller.UsuarioFacade;
import br.com.cuidebem.controller.UsuarioPhotoFacade;
import br.com.cuidebem.controller.UsuarioTelefoneFacade;
import br.com.cuidebem.controller.exception.ControllerException;
import br.com.cuidebem.model.Roles;
import br.com.cuidebem.model.Telefone;
import br.com.cuidebem.model.Usuario;
import br.com.cuidebem.model.def.Operadoras;
import br.com.cuidebem.model.def.TipoTelefone;
import br.com.cuidebem.model.def.TipoUsuario;
import br.com.cuidebem.translate.Bundle;
import br.com.cuidebem.view.IndexView;
import br.com.cuidebem.view.util.JsfUtil;

@Named("usuariomb")
@RequestScoped
public class UsuarioView extends IndexView {

	@EJB
	private UsuarioTelefoneFacade usuarioTelefoneFacade;
	@EJB
	private UsuarioFacade usuarioFacade;
	@EJB
	private UsersFacade usersFacade;
	@EJB
	private TelefoneFacade telefoneFacade;
	@EJB
	private RolesFacade rolesFacade;
	@EJB
	private UsuarioPhotoFacade usuarioPhotoFacade;
	private Usuario usuario;

	private ListDataModel<Telefone> telefones;
	private Telefone telefone;

	private String password;

	public UsuarioView() {
	}

	@PostConstruct
	private void init() {
		usuario = new Usuario();
		telefone = new Telefone();
		String _idusuario = JsfUtil.getRequestParameter("idusuario");
		telefones = new ListDataModel<>();
		if (_idusuario != null) {
			usuario = usuarioFacade.find(Integer.valueOf(_idusuario));
			loadTelefones();
		}
	}

	public String delete() {
		if (usuario.getIdusuario() != null) {
			try {
				usuarioFacade.delete(usuario);
				JsfUtil.addSuccessMessage(Bundle.getValue("del.sucess"));
				return "/app/usuario/usuarios.xhtml";
			} catch (ControllerException e) {
				JsfUtil.addErrorMessage(e.getMessage());
			}
		}
		JsfUtil.addErrorMessage(Bundle.getValue("error.delete"));
		return null;
	}

	public void create() {
		try {
			Roles role = rolesFacade.find(usuario.getTipoUsuario());
			usersFacade.create(role, usuario.getEmail(), password);
			usuario = usuarioFacade.create(getIdresidencia(), usuario);
			JsfUtil.addSuccessMessage(Bundle.getValue("cadsucesso"));
		} catch (ControllerException e) {
			JsfUtil.addErrorMessage(e.getMessage());
		}
	}

	public void edit() {
		try {
			usuario = usuarioFacade.editUsuario(getResidencia(), usuario);
			JsfUtil.addSuccessMessage(Bundle.getValue("cadsucesso"));
		} catch (ControllerException e) {
			JsfUtil.addErrorMessage(e.getMessage());
		}
	}

	public void handleFileUpload(FileUploadEvent event) {
		try {
			Integer idusuario = Integer.valueOf(
					(String) ((HtmlInputHidden) event.getComponent().getChildren().get(0)).getSubmittedValue());
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
			telefoneFacade.save(telefone, usuario);
			loadTelefones();
			telefone = new Telefone();
			JsfUtil.addSuccessMessage(Bundle.getValue("cadsucesso"));
		} catch (ControllerException e) {
			JsfUtil.addErrorMessage(e.getMessage());
		}
	}

	private void loadTelefones() {
		try {
			telefones.setWrappedData(usuarioTelefoneFacade.getTelefones(usuario));
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

	public List<TipoUsuario> getTiposUsuarios() {
		return Arrays.asList(TipoUsuario.values());
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ListDataModel<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(ListDataModel<Telefone> telefones) {
		this.telefones = telefones;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
