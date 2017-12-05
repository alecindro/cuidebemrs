package br.com.cuidebem.controller.schedule;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.cuidebem.controller.AbstractFacade;
import br.com.cuidebem.controller.QueryParameter;
import br.com.cuidebem.controller.exception.ControllerException;
import br.com.cuidebem.model.email.Schedulemail;

@Stateless
public class ScheduleEmailFacade extends AbstractFacade<Schedulemail> {
	
	@EJB
	private ScheduleEmailTimer scheduleEmailTimer;

	public ScheduleEmailFacade() {
		super(Schedulemail.class);
	}
	
	public Schedulemail find(Integer idpaciente,Integer idresponsavel) throws ControllerException{
		Schedulemail schedulemail = new Schedulemail();
		QueryParameter parameters = QueryParameter.init("idpaciente", idpaciente);
		parameters.add("idresponsavel", idresponsavel);
		List<Schedulemail> list = findWithNamedQuery("schedulemail.findByResponsavel", parameters,1);
		if(list!=null && !list.isEmpty()){
			schedulemail = list.get(0);
		}
		return schedulemail;
	}
	
	public Schedulemail findEager(Schedulemail schedulemail) throws ControllerException{
		QueryParameter parameters = QueryParameter.init("idschedulemail", schedulemail.getIdschedulemail()); 
		return findWithNamedQuery("schedulemail.findSchedule", parameters,1).get(0);
	}
	
	public Schedulemail save(Schedulemail schedulemail) throws ControllerException{
		schedulemail = edit(schedulemail);
		schedulemail = findEager(schedulemail);
		if(schedulemail.isEnabled()){
		scheduleEmailTimer.add(schedulemail);
		}else{
			scheduleEmailTimer.cancel(schedulemail);
		}
		return schedulemail;
	}

}
