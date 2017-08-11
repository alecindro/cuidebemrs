/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cuidebem.controller;

import java.util.List;

import javax.ejb.Stateless;

import br.com.cuidebem.controller.exception.ControllerException;
import br.com.cuidebem.model.ResponsavelPaciente;

/**
 *
 * @author aleci
 */
@Stateless
public class ResponsavelPacienteFacade extends AbstractFacade<ResponsavelPaciente>{

  

    public ResponsavelPacienteFacade() {
        super(ResponsavelPaciente.class);
    }
    
    public ResponsavelPaciente find(Integer idresponsvel, Integer idpaciente) throws ControllerException{
    	QueryParameter parameters = QueryParameter.init("idresponsavel", idresponsvel);
    	parameters.add("idpaciente", idpaciente);
    	List<ResponsavelPaciente> list = findWithNamedQuery("ResponsavelPaciente.findByUnique", parameters, 1);
    	if(list.isEmpty()){
    		return null;
    	}
    	return list.get(0);
    }
    
}
