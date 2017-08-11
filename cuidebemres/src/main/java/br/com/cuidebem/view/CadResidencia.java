package br.com.cuidebem.view;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.cuidebem.controller.ResidenciaFacade;
import br.com.cuidebem.controller.exception.ControllerException;
import br.com.cuidebem.model.Residencia;
import br.com.cuidebem.model.Users;
import br.com.cuidebem.translate.Bundle;
import br.com.cuidebem.view.util.JsfUtil;

@Named("cadResidencia")
@RequestScoped
public class CadResidencia {

	@Inject
	private Residencia residencia;
	@Inject
	private Users user;
	@EJB
	private ResidenciaFacade residenciaFacade; 
	
	public String save(){
		try {
			residenciaFacade.create(residencia,user);
			return "cadressucess.xhtml";
		} catch (ControllerException e) {
			JsfUtil.addErrorMessage(Bundle.getValue(e.getMessage()));
		}
		return null;
	}
	
	public String edit(){
		try{
		residenciaFacade.edit(residencia);
		return "cadressucess.xhtml";
		}catch(ControllerException e){
			JsfUtil.addErrorMessage(e,Bundle.getValue("error.save"));
		}
		return null;
	}
	
	public Residencia getResidencia() {
		return residencia;
	}
	public void setResidencia(Residencia residencia) {
		this.residencia = residencia;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	
	
	
	
}
