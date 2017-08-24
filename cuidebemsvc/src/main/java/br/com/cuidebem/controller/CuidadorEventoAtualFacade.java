package br.com.cuidebem.controller;

import java.util.List;

import javax.ejb.Stateless;

import br.com.cuidebem.controller.exception.ControllerException;
import br.com.cuidebem.model.view.CuidadorEventoAtual;

@Stateless
public class CuidadorEventoAtualFacade extends AbstractFacade<CuidadorEventoAtual> {

	public CuidadorEventoAtualFacade() {
		super(CuidadorEventoAtual.class);
	}

	public List<CuidadorEventoAtual> findByResidencia(Integer idresidencia) throws ControllerException {
		return findByNativeQuery("CuidadorEventoAtual.findAllByResidencia", idresidencia);
	}

}
