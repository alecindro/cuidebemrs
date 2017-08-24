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
import br.com.cuidebem.model.Responsavel;
import br.com.cuidebem.model.ResponsavelTelefone;
import br.com.cuidebem.model.Telefone;

/**
 *
 * @author aleci
 */
@Stateless
public class ResponsavelTelefoneFacade extends AbstractFacade<ResponsavelTelefone> {

 

    public ResponsavelTelefoneFacade() {
        super(ResponsavelTelefone.class);
    }
    
    public List<Telefone> getTelefones(Responsavel responsavel) throws ControllerException{
    	List<Telefone> telefones = new ArrayList<Telefone>();
    	QueryParameter parameters = QueryParameter.init("idresponsavel", responsavel.getIdresponsavel());
    	List<ResponsavelTelefone> list = findWithNamedQuery("ResponsavelTelefone.findByIdResponsavel", parameters, 0);
    	for(ResponsavelTelefone ut : list){
    		telefones.add(ut.getIdtelefone());
    	}
    	return telefones;
    }
    
}
