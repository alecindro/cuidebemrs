package br.com.cuidebem.controller.email;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.cuidebem.controller.EventoFacade;
import br.com.cuidebem.controller.MemorandoFacade;
import br.com.cuidebem.controller.PacienteFacade;
import br.com.cuidebem.controller.PacientePhotoFacade;
import br.com.cuidebem.controller.exception.ControllerException;
import br.com.cuidebem.model.Evento;
import br.com.cuidebem.model.Memorando;
import br.com.cuidebem.model.Paciente;
import br.com.cuidebem.model.Residencia;
import br.com.cuidebem.model.Responsavel;
import br.com.cuidebem.model.util.DateUtil;
import br.com.cuidebem.translate.Bundle;

@Stateless
public class EventoEmailService {
	
	@EJB
	private EventoFacade eventoFacade;
	@EJB
	private PacienteFacade pacienteFacade;
	@EJB
	private MemorandoFacade memorandoFacade;
	@EJB 
	private EventoMail eventoEmail;
	@EJB
	private PacientePhotoFacade pacientePhotoFacade;
	
	public void sendEmail(List<Responsavel> responsaveis,Residencia residencia,Integer idpaciente,Date dataEvento,boolean automatic) throws ControllerException{
		sendEmail(responsaveis, residencia.getIdresidencia(),residencia.getRazao(), idpaciente, dataEvento,automatic);
	}
	
	public void sendEmail(List<Responsavel> responsaveis,Integer idresidencia, String razaoSocial,Integer idpaciente,Date dataEvento,boolean automatic) throws ControllerException{
		List<Evento> eventos = eventoFacade.findByPacienteDataregistro(idpaciente, dataEvento);
		if(eventos.isEmpty()){
			throw new ControllerException(Bundle.getValue("eventosempty",DateUtil.convertDate(dataEvento)));
		}
		
		Paciente paciente = pacienteFacade.find(idpaciente);
		Memorando memorando = memorandoFacade.loadbyDateAtual(idpaciente, dataEvento);
		EventoEmailModel model = new EventoEmailModel();
		model.setData(dataEvento);
		model.setEventos(eventos);
		model.setMemorando(memorando.getDescricao());
		model.setPaciente(paciente.getApelido());
		model.setIdpaciente(idpaciente);
		model.setResidencia(razaoSocial);
		model.setIdresidencia(idresidencia);
		model.setResponsaveis(responsaveis);
		eventoEmail.sendMessage(model,automatic);
	}
	
	public void sendEmail(Integer idresidencia,String email,String razaoSocial,Integer idpaciente,Date dataEvento,boolean automatic) throws ControllerException{
		List<Responsavel> responsaveis = new ArrayList<Responsavel>();
		Responsavel responsavel = new Responsavel();
		responsavel.setEmail(email);
		responsaveis.add(responsavel);
		sendEmail(responsaveis, idresidencia, razaoSocial, idpaciente, dataEvento,automatic);
	}

}
