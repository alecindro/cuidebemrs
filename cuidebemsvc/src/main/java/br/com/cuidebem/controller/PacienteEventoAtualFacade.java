package br.com.cuidebem.controller;

import java.util.List;

import javax.ejb.Stateless;

import br.com.cuidebem.controller.exception.ControllerException;
import br.com.cuidebem.model.view.PacienteEventoAtual;

@Stateless
public class PacienteEventoAtualFacade extends AbstractFacade<PacienteEventoAtual>{
	


	public PacienteEventoAtualFacade() {
		super(PacienteEventoAtual.class);
	}
	
	public List<PacienteEventoAtual> findByResidencia(Integer idresidencia) throws ControllerException{
		List<PacienteEventoAtual> list = findByNativeQuery("PacienteEventoAtual.findAllByResidencia", idresidencia,idresidencia,idresidencia,idresidencia);
		return list;
	}

}
