/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cuidebem.controller;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.cuidebem.controller.exception.ControllerException;
import br.com.cuidebem.model.Paciente;
import br.com.cuidebem.model.Responsavel;
import br.com.cuidebem.model.ResponsavelPaciente;

/**
 *
 * @author aleci
 */
@Stateless
public class ResponsavelFacade extends AbstractFacade<Responsavel>  {
	
	@EJB
	private ResponsavelPacienteFacade responsavelPacienteFacade;

    public ResponsavelFacade() {
        super(Responsavel.class);
    }
    
    public void delResponsavel(Responsavel responsavel) throws ControllerException{
    	responsavel.setEnabled(false);
    	edit(responsavel);
    }
    public List<Responsavel> loadEnabled() throws ControllerException{
    	QueryParameter parameters = QueryParameter.init("enabled", true);
    	return findWithNamedQuery("Responsavel.findByEnabled", parameters, 0);
    }
    public List<Responsavel> loadDisabled() throws ControllerException{
    	QueryParameter parameters = QueryParameter.init("enabled", false);
    	return findWithNamedQuery("Responsavel.findByEnabled", parameters, 0);
    }
    
    public Responsavel save(Responsavel responsavel,Paciente paciente) throws ControllerException{
    	responsavel.setEnabled(true);
    	responsavel = edit(responsavel);
    	if(responsavelPacienteFacade.find(responsavel.getIdresponsavel(), paciente.getIdpaciente()) == null){
    		ResponsavelPaciente rp = new ResponsavelPaciente();
    		rp.setIdpaciente(paciente);
    		rp.setIdresponsavel(responsavel);
    		responsavelPacienteFacade.edit(rp);
    	}
    	return responsavel;
    	
    }
    public List<Responsavel> loadByPaciente(Integer idpaciente) throws ControllerException{
    	return findByNativeQuery("Responsavel.findByPaciente", idpaciente);
    	
    }
    
    
}
