package br.com.cuidebem.view;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
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

	
	private Residencia residencia;
	
	private Users user;
	@EJB
	private ResidenciaFacade residenciaFacade;
	private String nomeUsuario;
	
	@PostConstruct
	private void init(){
		residencia = new Residencia();
		user = new Users();
	}
	
	public String save(){
		try {
			residenciaFacade.create(residencia,user,nomeUsuario);
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

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	
	
	
	
}
