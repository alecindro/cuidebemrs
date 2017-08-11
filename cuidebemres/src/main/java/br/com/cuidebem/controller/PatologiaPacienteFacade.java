/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cuidebem.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ejb.Stateless;

import br.com.cuidebem.controller.exception.ControllerException;
import br.com.cuidebem.model.PatologiaPaciente;

/**
 *
 * @author aleci
 */
@Stateless
public class PatologiaPacienteFacade extends AbstractFacade<PatologiaPaciente> {

	public PatologiaPacienteFacade() {
		super(PatologiaPaciente.class);
	}

	public List<PatologiaPaciente> findById(Integer idpaciente) throws ControllerException {
		QueryParameter parameters = QueryParameter.init("idpaciente", idpaciente);
		return findWithNamedQuery("PatologiaPaciente.findByIdpaciente", parameters, 0);
	}

	public void update(String[] selected, Integer idpaciente) throws ControllerException {
		ArrayList<String> patologias = new ArrayList<String>(Arrays.asList(selected));
		ArrayList<String> patologiasOld = new ArrayList<String>();
		List<PatologiaPaciente> list = findById(idpaciente);
		for (PatologiaPaciente pp : list) {
			if (!patologias.contains(pp.getPatologia())) {
				remove(pp);
			} else {
				patologiasOld.add(pp.getPatologia());
			}
		}
		patologias.removeAll(patologiasOld); 
			for (String pt : patologias) {
				PatologiaPaciente patolociaPaciente = new PatologiaPaciente(idpaciente, pt);
				create(patolociaPaciente);
			}
		
	}
}
