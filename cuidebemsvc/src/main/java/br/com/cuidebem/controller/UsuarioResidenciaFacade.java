/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cuidebem.controller;

import java.util.List;

import javax.ejb.Stateless;

import br.com.cuidebem.controller.exception.ControllerException;
import br.com.cuidebem.model.Residencia;
import br.com.cuidebem.model.Usuario;
import br.com.cuidebem.model.UsuarioResidencia;

/**
 *
 * @author aleci
 */
@Stateless
public class UsuarioResidenciaFacade extends AbstractFacade<UsuarioResidencia> {

   
    public UsuarioResidenciaFacade() {
        super(UsuarioResidencia.class);
    }
    
    public List<UsuarioResidencia> find(Usuario usuario) throws ControllerException{
    	QueryParameter parameters = QueryParameter.init("idusuario", usuario.getIdusuario());
    	return findWithNamedQuery("UsuarioResidencia.findByIdusuario", parameters, 0);
    }
    
    public boolean exits(Usuario usuario, Residencia residencia) throws ControllerException{
    	if(usuario.getIdusuario() == null){
    		return false;
    	}
    	if(residencia.getIdresidencia() == null){
    		return false;
    	}
    	QueryParameter parameters = QueryParameter.init("idusuario", usuario.getIdusuario());
    	parameters.add("idresidencia", residencia.getIdresidencia());
    	List<UsuarioResidencia> list = findWithNamedQuery("UsuarioResidencia.find", parameters,1);
    	if(list != null && list.size() > 0){
    		return true;
    	}
    	return false;
    }
}
