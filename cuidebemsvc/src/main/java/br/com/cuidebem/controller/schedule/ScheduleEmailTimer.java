package br.com.cuidebem.controller.schedule;

import java.util.Calendar;

import javax.annotation.Resource;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.ScheduleExpression;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.cuidebem.controller.EmailException;
import br.com.cuidebem.controller.EmailJpaController;
import br.com.cuidebem.controller.email.EventoEmailService;
import br.com.cuidebem.controller.exception.ControllerException;
import br.com.cuidebem.model.Emailenviado;
import br.com.cuidebem.model.email.Schedulemail;

@Startup
@Singleton
public class ScheduleEmailTimer {

	@Resource
	TimerService timerService;
	@EJB
	EventoEmailService eventoMailService;
	private static Logger logger = LoggerFactory.getLogger(ScheduleEmailTimer.class);
	@EJB
	private EmailJpaController emailJpaController;

	
	@Timeout
	public void execute(Timer timer){
		Schedulemail schedulemail = (Schedulemail) timer.getInfo();
		try {
			eventoMailService.sendEmail(schedulemail.getResidencia().getIdresidencia(), schedulemail.getResponsavel().getEmail(),schedulemail.getResidencia().getRazao(), schedulemail.getIdpaciente(), Calendar.getInstance().getTime(),true);
		} catch (ControllerException e) {
			Emailenviado emailenviado = new Emailenviado ();
			emailenviado.setDataenvio(Calendar.getInstance().getTime());
			emailenviado.setSubject("");
			emailenviado.setTo_email(schedulemail.getResponsavel().getEmail());
			emailenviado.setDatarelatorio(Calendar.getInstance().getTime());
			emailenviado.setIdresidencia(schedulemail.getResidencia().getIdresidencia());
			emailenviado.setError(true);
			emailenviado.setMessage(e.getMessage());
			emailenviado.setAutomatic(true);
			try {
				emailJpaController.create(emailenviado,"");
			} catch (EmailException e1) {
				logger.error(e.getMessage());
			}
			
		}
	}
	@Asynchronous
	public void add(Schedulemail schedulemail){
		 cancel(schedulemail);
		 addScheduler(schedulemail);
	}
	
	@Asynchronous
	public void cancel(Schedulemail schedulemail){
		 Timer timer =  timerService.getTimers().stream().filter(_timer -> schedulemail.equals(_timer.getInfo())).findAny().orElse(null);
		 if(timer != null){
			 timer.cancel();
		 }
	}
	
	private void addScheduler(Schedulemail schedulemail){
		ScheduleExpression se = new ScheduleExpression();
		se.dayOfWeek(schedulemail.getDiasemana());
		String[] hora = schedulemail.getHora().split(":"); 
		se.hour(hora[0]);
		se.minute(hora[1]);
		TimerConfig tc = new TimerConfig();
		tc.setInfo(schedulemail);
		timerService.createCalendarTimer(se,tc);
		
	}
	
	
}
