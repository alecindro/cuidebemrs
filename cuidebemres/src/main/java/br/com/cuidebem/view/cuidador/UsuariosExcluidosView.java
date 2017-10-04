package br.com.cuidebem.view.cuidador;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.ListDataModel;
import javax.inject.Named;

import br.com.cuidebem.controller.UsersFacade;
import br.com.cuidebem.controller.UsuarioFacade;
import br.com.cuidebem.controller.exception.ControllerException;
import br.com.cuidebem.model.Usuario;
import br.com.cuidebem.view.IndexView;
import br.com.cuidebem.view.util.JsfUtil;

@Named("usuariosexcludemb")
@RequestScoped
public class UsuariosExcluidosView extends IndexView {

	@EJB
	private UsuarioFacade usuarioFacade;
	@EJB
	private UsersFacade usersFacade;
	private ListDataModel<Usuario> usuarios = null;
	private Usuario usuario;

	@PostConstruct
	private void init() {
		String _idusuario = JsfUtil.getRequestParameter("idusuario");
		loadExcluidos();
		if (_idusuario != null) {
			usuario = usuarioFacade.find(Integer.valueOf(_idusuario));
		}
	}

	public String loadExcluidos() {
		try {
			usuarios = new ListDataModel<>();
			usuarios.setWrappedData(usuarioFacade.findAllDisabled(getIdresidencia()));
			return "/app/usuario/usuariosExcluidos.xhtml";
		} catch (ControllerException e) {
			JsfUtil.addErrorMessage(e, e.getMessage());
		}
		return null;
	}

	public String reactive() {
		try {
			usersFacade.enabled(usuario.getEmail(), true);
			usuarioFacade.reativar(usuario);
			JsfUtil.addSuccessMessage("reactive.sucess");
			return "/app/usuario/usuarios.xhtml";
		} catch (ControllerException e) {
			JsfUtil.addErrorMessage(e.getMessage());
		}
		return null;
	}

	public ListDataModel<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(ListDataModel<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
