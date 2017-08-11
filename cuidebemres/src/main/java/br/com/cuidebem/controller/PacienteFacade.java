/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cuidebem.controller;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.cuidebem.controller.exception.ControllerException;
import br.com.cuidebem.model.Paciente;
import br.com.cuidebem.model.Residencia;

/**
 *
 * @author aleci
 */
@Stateless
public class PacienteFacade extends AbstractFacade<Paciente> {

	@PersistenceContext(unitName = "cuidebemresPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public PacienteFacade() {
		super(Paciente.class);
	}

	public Paciente save(Paciente paciente, Residencia residencia) throws ControllerException {
		paciente.setEnabled(true);
		paciente.setIdresidencia(residencia);
		return edit(paciente);
	}

	public void delete(Paciente paciente) throws ControllerException {
		paciente.setEnabled(false);
		edit(paciente);
	}

	public void enabled(Paciente paciente) throws ControllerException {
		paciente.setEnabled(true);
		edit(paciente);
	}

	public List<Paciente> findEnabled(int idresidencia) throws ControllerException {
		return find(idresidencia, true);
	}

	public List<Paciente> findDisabled(int idresidencia) throws ControllerException {
		return find(idresidencia, false);
	}

	private List<Paciente> find(int idresidencia, boolean enabled) throws ControllerException {
		QueryParameter parameters = QueryParameter.init("idresidencia", idresidencia);
		parameters.add("enabled", enabled);
		return findWithNamedQuery("Paciente.findByEnabled", parameters, 0);

	}
}
