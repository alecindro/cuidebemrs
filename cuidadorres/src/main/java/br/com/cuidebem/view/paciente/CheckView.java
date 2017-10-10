package br.com.cuidebem.view.paciente;

import java.util.Calendar;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.com.cuidebem.controller.EventoFacade;
import br.com.cuidebem.controller.PacienteFacade;
import br.com.cuidebem.controller.exception.ControllerException;
import br.com.cuidebem.model.Evento;
import br.com.cuidebem.model.Paciente;
import br.com.cuidebem.translate.Bundle;
import br.com.cuidebem.view.IndexView;
import br.com.cuidebem.view.util.JsfUtil;

@Named("checkmb")
@RequestScoped
public class CheckView extends IndexView {

	private Evento evento;
	private boolean checkin;
	private Paciente paciente;
	@EJB
	private EventoFacade eventoFacade;
	@EJB
	private PacienteFacade pacienteFacade;

	@PostConstruct
	private void init() {
		evento = new Evento();
		String _idpaciente = JsfUtil.getRequestParameter("idpaciente");
		Integer idpaciente = Integer.valueOf(_idpaciente);
		paciente = pacienteFacade.find(idpaciente);
		evento.setIdpaciente(idpaciente);
		String _checkin = JsfUtil.getRequestParameter("checkin");
		checkin = Boolean.valueOf(_checkin);
	}

	public String save() {
		try {
			Date dataevento = Calendar.getInstance().getTime();
			if (checkin) {
				evento.setGrupoevento(br.com.cuidebem.model.def.Check.CHECKIN.getDescricao());
			} else {
				evento.setGrupoevento(br.com.cuidebem.model.def.Check.CHECKOUT.getDescricao());
			}
			evento.setDataregistro(dataevento);
			evento.setEnabled(true);
			evento.setIdusuario(getUsuario().getIdusuario());
			eventoFacade.save(evento, null);
		} catch (ControllerException e) {
			JsfUtil.addErrorMessage(e.getMessage());
			return null;
		}
		JsfUtil.addSuccessMessage(Bundle.getValue("evento_sucess"));
		return "/app/index.xhtml";
	}

	public boolean isCheckin() {
		return checkin;
	}

	public void setCheckin(boolean checkin) {
		this.checkin = checkin;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	
	

}
