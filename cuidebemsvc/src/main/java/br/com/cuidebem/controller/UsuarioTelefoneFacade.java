/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cuidebem.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import br.com.cuidebem.controller.exception.ControllerException;
import br.com.cuidebem.model.Telefone;
import br.com.cuidebem.model.Usuario;
import br.com.cuidebem.model.UsuarioTelefone;

/**
 *
 * @author aleci
 */
@Stateless
public class UsuarioTelefoneFacade extends AbstractFacade<UsuarioTelefone>  {


    public UsuarioTelefoneFacade() {
        super(UsuarioTelefone.class);
    }
    
    public List<Telefone> getTelefones(Usuario usuario) throws ControllerException{
    	List<Telefone> telefones = new ArrayList<Telefone>();
    	QueryParameter parameters = QueryParameter.init("idusuario", usuario.getIdusuario());
    	List<UsuarioTelefone> list = findWithNamedQuery("UsuarioTelefone.findByIdusuario", parameters, 0);
    	for(UsuarioTelefone ut : list){
    		telefones.add(ut.getIdtelefone());
    	}
    	return telefones;
    }
    
    
    
}
