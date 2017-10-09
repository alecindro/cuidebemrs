package br.com.cuidebem.controller.email;

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
import br.com.cuidebem.model.PacientePhoto;
import br.com.cuidebem.model.Residencia;
import br.com.cuidebem.model.Responsavel;
import br.com.cuidebem.model.util.PhotoUtil;

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
	
	public void sendEmail(List<Responsavel> responsaveis,Residencia residencia,Integer idpaciente,Date dataEvento) throws ControllerException{
		List<Evento> eventos = eventoFacade.findByPacienteDataregistro(idpaciente, dataEvento);
		Paciente paciente = pacienteFacade.find(idpaciente);
		Memorando memorando = memorandoFacade.loadbyDateAtual(idpaciente, dataEvento);
		PacientePhoto pacientePhoto = pacientePhotoFacade.find(idpaciente);
		EventoEmailModel model = new EventoEmailModel();
		model.setData(dataEvento);
		model.setEventos(eventos);
		model.setMemorando(memorando.getDescricao());
		model.setPaciente(paciente.getApelido());
		model.setIdpaciente(idpaciente);
		model.setResidencia(residencia.getRazao());
		model.setResponsaveis(responsaveis);
		byte[] photo = pacientePhoto.getPhoto();
		if(photo != null){
		photo = PhotoUtil.base64(photo);
		}
		model.setFotoPaciente(photo);
		eventoEmail.sendMessage(model);
	}

}
