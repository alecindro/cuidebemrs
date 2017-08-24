package br.com.cuidebem.controller;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.cuidebem.controller.exception.ControllerException;
import br.com.cuidebem.model.Agendadef;

@Stateless
public class AgendaDefFacade extends AbstractFacade<Agendadef>{

	@EJB
	private AgendaFacade agendaFacade;
	
	public AgendaDefFacade() {
		super(Agendadef.class);
	}
	
	public Agendadef save(Agendadef agendadef) throws ControllerException{
		boolean edit = agendadef.getIdagendadef() != null;
		agendadef.setDataRegistro(Calendar.getInstance(Locale.getDefault()).getTime());
		agendadef = edit(agendadef);
		if(!edit){
		agendaFacade.save(agendadef);
		}else{
			agendaFacade.edit(agendadef);
		}
		return agendadef;
	}
	
	public void delete(Agendadef agendadef) throws ControllerException{
		agendaFacade.delete(agendadef);
		if(agendaFacade.count(agendadef)>0){
			agendadef.setEnabled(false);
			edit(agendadef);
		}else{
		remove(agendadef);
		}
	}
	
	public List<Agendadef> findByPaciente(Integer idpaciente) throws ControllerException{
		QueryParameter parameters = QueryParameter.init("idpaciente", idpaciente);
		return findWithNamedQuery("Agendadef.findEnabledByPaciente", parameters, 0);
	}

}
