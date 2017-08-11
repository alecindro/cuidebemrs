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
import br.com.cuidebem.model.ResidenciaTelefone;
import br.com.cuidebem.model.Responsavel;
import br.com.cuidebem.model.ResponsavelTelefone;
import br.com.cuidebem.model.Telefone;
import br.com.cuidebem.model.Usuario;
import br.com.cuidebem.model.UsuarioTelefone;

/**
 *
 * @author aleci
 */
@Stateless
public class TelefoneFacade extends AbstractFacade<Telefone>  {

  

    public TelefoneFacade() {
        super(Telefone.class);
    }
    
    public Telefone save(Telefone telefone, Usuario usuario) throws ControllerException{
    	UsuarioTelefone usuarioTelefone = new UsuarioTelefone();
		usuarioTelefone.setIdusuario(usuario);
		usuarioTelefone.setIdtelefone(telefone);
		telefone.getUsuarioTelefoneList().add(usuarioTelefone);
		return edit(telefone);
	}
    public void save(List<Telefone> telefones, Usuario usuario) throws ControllerException{
    	for(Telefone tel : telefones){
    		UsuarioTelefone usuarioTelefone = new UsuarioTelefone();
    		usuarioTelefone.setIdusuario(usuario);
    		usuarioTelefone.setIdtelefone(tel);
    		tel.getUsuarioTelefoneList().add(usuarioTelefone);
    		edit(tel);
    	}
    }
    
    public Telefone save(Telefone telefone, Responsavel responsavel) throws ControllerException{
    	ResponsavelTelefone responsavelTelefone = new ResponsavelTelefone();
    	responsavelTelefone.setIdresponsavel(responsavel);;
		responsavelTelefone.setIdtelefone(telefone);
		telefone.getResponsavelTelefoneList().add(responsavelTelefone);
		return edit(telefone);
	}
    
    public void save(List<Telefone> telefones, Responsavel responsavel) throws ControllerException{
    	for(Telefone tel : telefones){
    		ResponsavelTelefone respTelefone = new ResponsavelTelefone();
    		respTelefone.setIdresponsavel(responsavel);
    		respTelefone.setIdtelefone(tel);
    		tel.getResponsavelTelefoneList().add(respTelefone);
    		edit(tel);
    	}
    }
    
    public void save(List<Telefone> telefones, Residencia residencia) throws ControllerException{
    	for(Telefone tel : telefones){
    		save(tel, residencia);
    	}
    }
    
    public void save(Telefone telefone, Residencia residencia) throws ControllerException{
    		ResidenciaTelefone residTelefone = new ResidenciaTelefone();
    		residTelefone.setIdresidencia(residencia);
    		residTelefone.setIdtelefone(telefone);
    		telefone.getResidenciaTelefoneList().add(residTelefone);
    		edit(telefone);
    	
    }
    
}
