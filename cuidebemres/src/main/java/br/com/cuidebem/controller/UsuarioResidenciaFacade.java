/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cuidebem.controller;

import java.util.List;

import javax.ejb.Stateless;

import br.com.cuidebem.controller.exception.ControllerException;
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
}
