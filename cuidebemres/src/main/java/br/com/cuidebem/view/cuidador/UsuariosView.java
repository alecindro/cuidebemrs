package br.com.cuidebem.view.cuidador;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.ListDataModel;
import javax.inject.Named;

import br.com.cuidebem.controller.UsuarioFacade;
import br.com.cuidebem.controller.exception.ControllerException;
import br.com.cuidebem.model.Usuario;
import br.com.cuidebem.view.IndexView;
import br.com.cuidebem.view.util.JsfUtil;

@Named("usuariosmb")
@RequestScoped
public class UsuariosView extends IndexView {	

	@EJB
	private UsuarioFacade usuarioFacade;
	private ListDataModel<Usuario> usuarios = null;

	@PostConstruct
	private void init() {
		loadUsuarios();
	}

	private void loadUsuarios() {
		try {
			usuarios = new ListDataModel<>();
			usuarios.setWrappedData(usuarioFacade.findAllEnabled(getIdresidencia()));
		} catch (ControllerException e) {
			JsfUtil.addErrorMessage(e, e.getMessage());
		}
	}

	public ListDataModel<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(ListDataModel<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

}
