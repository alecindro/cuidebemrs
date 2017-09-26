package br.com.cuidebem.view;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import br.com.cuidebem.controller.UsuarioFacade;
import br.com.cuidebem.controller.UsuarioResidenciaFacade;
import br.com.cuidebem.controller.exception.ControllerException;
import br.com.cuidebem.model.Residencia;
import br.com.cuidebem.model.Usuario;
import br.com.cuidebem.model.def.RolesEnum;
import br.com.cuidebem.view.util.JsfUtil;
import br.com.cuidebem.view.util.UtilSecurity;

@Named("index")
@RequestScoped
public class IndexView {
	@EJB
	private UsuarioFacade usuarioFacade;
	@EJB
	private UsuarioResidenciaFacade usuarioResidenciaFacade;
	private Integer idresidencia;
	private Residencia residencia;
	private static final String NO_PERMISSION_URL="/resources/nopermission.xhtml";
	private Usuario usuario;
	
	@PostConstruct
	public void authorize(){
		String user = UtilSecurity.getUser();
		try {
			usuario = usuarioFacade.find(user);
			if(usuario == null){
				try{
					UtilSecurity.logout();
					JsfUtil.redirect(NO_PERMISSION_URL);
					return;
				}catch(IOException e){
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return;
			}
			if(!UtilSecurity.hasRole(RolesEnum.CUIDADOR.getValue())){
				try {
					UtilSecurity.logout();
					JsfUtil.redirect(NO_PERMISSION_URL);
					return;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return;
			}
			//Todo refazer para mais de uma empresa
			residencia = usuarioResidenciaFacade.find(usuario).get(0).getIdresidencia();
			Integer _idresidencia = residencia.getIdresidencia();
			
			if(UtilSecurity.containsPermission(String.valueOf(_idresidencia))){
				idresidencia = _idresidencia;
			}else{
				try {
					UtilSecurity.logout();
					JsfUtil.redirect(NO_PERMISSION_URL);
					return;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		} catch (ControllerException e) {
		JsfUtil.addErrorMessage(e.getMessage());
		}
	}

	public void logout() {
		UtilSecurity.logout();
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		try {
			ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
		} catch (IOException e) {
			JsfUtil.addErrorMessage(e.getMessage());
		}
	}

	public Integer getIdresidencia() {
		return idresidencia;
	}

	public void setIdresidencia(Integer idresidencia) {
		this.idresidencia = idresidencia;
	}

	public Residencia getResidencia() {
		return residencia;
	}

	public void setResidencia(Residencia residencia) {
		this.residencia = residencia;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	
}
