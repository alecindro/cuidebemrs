package br.com.cuidebem.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.cuidebem.controller.exception.ControllerException;
import br.com.cuidebem.model.Agenda;
import br.com.cuidebem.model.AgendaDef;

@Stateless
public class AgendaDefFacade extends AbstractFacade<AgendaDef>{

	@EJB
	private AgendaFacade agendaFacade;
	private static List<Agenda> agendas = new ArrayList<Agenda>();
	static{
		Agenda agenda = new Agenda();
		agenda.setData(Calendar.getInstance().getTime());
		agenda.setGrupoEvento("Nutrição");
		agenda.setSubGrupoEvento("Café da manhã");
		agenda.setMade(false);
		agenda.setObservacao("Com leite Integral.");
		agendas.add(agenda);
	}
	public AgendaDefFacade() {
		super(AgendaDef.class);
	}
	
	public AgendaDef save(AgendaDef agendaDef) throws ControllerException{
		//agendaDef = edit(agendaDef);
		
		return agendaDef;
	}

}
