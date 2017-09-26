package br.com.cuidebem.controller;

import java.util.List;

import javax.ejb.Stateless;

import br.com.cuidebem.controller.exception.ControllerException;
import br.com.cuidebem.model.Memorando;

@Stateless
public class MemorandoFacade extends AbstractFacade<Memorando>{

	public MemorandoFacade() {
		super(Memorando.class);
	}
	
	public Memorando loadAtual(Integer idpaciente) throws ControllerException{
		Memorando memorando = new Memorando();
		List<Memorando> memorandos = findByNativeQuery("Memorando.findAtual", idpaciente);
		if(memorandos == null || memorandos.isEmpty()){
			return memorando;	
		}
		return memorandos.get(0);
	}

}
