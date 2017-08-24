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
import br.com.cuidebem.model.Residencia;
import br.com.cuidebem.model.ResidenciaTelefone;
import br.com.cuidebem.model.Telefone;

/**
 *
 * @author aleci
 */
@Stateless
public class ResidenciaTelefoneFacade extends AbstractFacade<ResidenciaTelefone> {

    public ResidenciaTelefoneFacade() {
        super(ResidenciaTelefone.class);
    }
    
    public List<Telefone> getTelefones(Residencia residencia) throws ControllerException{
    	List<Telefone> telefones = new ArrayList<Telefone>();
    	QueryParameter parameters = QueryParameter.init("idresidencia", residencia.getIdresidencia());
    	List<ResidenciaTelefone> list = findWithNamedQuery("ResidenciaTelefone.findByIdResidencia", parameters, 0);
    	for(ResidenciaTelefone ut : list){
    		telefones.add(ut.getIdtelefone());
    	}
    	return telefones;
    }
}
