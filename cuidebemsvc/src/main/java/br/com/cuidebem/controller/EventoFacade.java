/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cuidebem.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.cuidebem.controller.exception.ControllerException;
import br.com.cuidebem.model.Agenda;
import br.com.cuidebem.model.Evento;
import br.com.cuidebem.model.Paciente;
import br.com.cuidebem.model.Usuario;
import br.com.cuidebem.translate.Bundle;

/**
 *
 * @author aleci
 */
@Stateless
public class EventoFacade extends AbstractFacade<Evento> {

	
	@EJB
	private AgendaFacade agendaFacade;
	
	public EventoFacade() {
		super(Evento.class);
	}

	public List<Evento> findEnabledByPaciente(Paciente paciente, Date date) throws ControllerException {
		return findEnabledByPaciente(paciente.getIdpaciente(), date);
	}

	public List<Evento> findEnabledByCuidador(Usuario cuidador, Date date) throws ControllerException {
		return findEnabledByCuidador(cuidador.getIdusuario(), date);
	}

	public List<Evento> findEnabledByPaciente(Integer idpaciente, Date date) throws ControllerException {
		try {
			javax.persistence.Query query = getEntityManager().createNativeQuery(Evento.EVENTO_PACIENTE);
			query.setParameter(1, idpaciente);
			query.setParameter(2, date);
			query.setParameter(3, date);
			@SuppressWarnings("unchecked")
			List<Object[]> tuples = query.getResultList();
			List<Evento> eventos = new ArrayList<Evento>();
			for (Object[] o : tuples) {
				Evento evento = new Evento((Integer) o[0], (String) o[1], (Date) o[2], (Date) o[3],  (String.valueOf(o[4])).equals("1"),
						(String) o[5], (String) o[6], (String) o[7], (String) o[8]);
				Paciente paciente = new Paciente();
				paciente.setIdpaciente((Integer) o[9]);
				paciente.setNome((String) o[10]);
				paciente.setApelido((String) o[11]);
				Usuario usuario = new Usuario();
				usuario.setIdusuario((Integer) o[12]);
				usuario.setNome((String) o[13]);
				usuario.setApelido((String) o[14]);
				evento.setUsuario(usuario);
				evento.setPaciente(paciente);
				eventos.add(evento);
			}
			return eventos;
		} catch (Exception e) {
			throw new ControllerException(e.getMessage(), e);
		}
	}
	
	public List<Evento> findByPacienteDataregistro(Integer idpaciente, Date dateBegin, Date dateEnd) throws ControllerException {
		try {
			javax.persistence.Query query = getEntityManager().createNativeQuery(Evento.EVENTO_PACIENTE_DATAREGISTRO);
			query.setParameter(1, idpaciente);
			query.setParameter(2, dateBegin);
			query.setParameter(3, dateEnd);
			@SuppressWarnings("unchecked")
			List<Object[]> tuples = query.getResultList();
			List<Evento> eventos = new ArrayList<Evento>();
			for (Object[] o : tuples) {
				Evento evento = new Evento((Integer) o[0], (String) o[1], (Date) o[2], (Date) o[3],  (String.valueOf(o[4])).equals("1"),
						(String) o[5], (String) o[6], (String) o[7], (String) o[8]);
				Paciente paciente = new Paciente();
				paciente.setIdpaciente((Integer) o[9]);
				paciente.setNome((String) o[10]);
				paciente.setApelido((String) o[11]);
				Usuario usuario = new Usuario();
				usuario.setIdusuario((Integer) o[12]);
				usuario.setNome((String) o[13]);
				usuario.setApelido((String) o[14]);
				evento.setUsuario(usuario);
				evento.setPaciente(paciente);
				eventos.add(evento);
			}
			return eventos;
		} catch (Exception e) {
			throw new ControllerException(e.getMessage(), e);
		}
	}

	public List<Evento> findEnabledByCuidador(Integer idcuidador, Date date) throws ControllerException {
		try {
			javax.persistence.Query query = getEntityManager().createNativeQuery(Evento.EVENTO_CUIDADOR);
			query.setParameter(1, idcuidador);
			query.setParameter(2, date);
			query.setParameter(3, date);
			@SuppressWarnings("unchecked")
			List<Object[]> tuples = query.getResultList();
			List<Evento> eventos = new ArrayList<Evento>();
			for (Object[] o : tuples) {
				Evento evento = new Evento((Integer) o[0], (String) o[1], (Date) o[2], (Date) o[3],  (String.valueOf(o[4])).equals("1"),
						(String) o[5], (String) o[6], (String) o[7], (String) o[8]);
				Paciente paciente = new Paciente();
				paciente.setIdpaciente((Integer) o[9]);
				paciente.setNome((String) o[10]);
				paciente.setApelido((String) o[11]);
				Usuario usuario = new Usuario();
				usuario.setIdusuario((Integer) o[12]);
				usuario.setNome((String) o[13]);
				usuario.setApelido((String) o[14]);
				evento.setUsuario(usuario);
				evento.setPaciente(paciente);
				eventos.add(evento);
			}
			return eventos;
		} catch (Exception e) {
			throw new ControllerException(e.getMessage(), e);
		}
	}
	
	public List<Evento> findByCuidadorDataregistro(Integer idcuidador, Date dateBegin,Date dateEnd) throws ControllerException {
		try {
			javax.persistence.Query query = getEntityManager().createNativeQuery(Evento.EVENTO_CUIDADOR_DATAREGISTRO);
			query.setParameter(1, idcuidador);
			query.setParameter(2, dateBegin);
			query.setParameter(3, dateEnd);
			@SuppressWarnings("unchecked")
			List<Object[]> tuples = query.getResultList();
			List<Evento> eventos = new ArrayList<Evento>();
			for (Object[] o : tuples) {
				Evento evento = new Evento((Integer) o[0], (String) o[1], (Date) o[2], (Date) o[3],  (String.valueOf(o[4])).equals("1"),
						(String) o[5], (String) o[6], (String) o[7], (String) o[8]);
				Paciente paciente = new Paciente();
				paciente.setIdpaciente((Integer) o[9]);
				paciente.setNome((String) o[10]);
				paciente.setApelido((String) o[11]);
				Usuario usuario = new Usuario();
				usuario.setIdusuario((Integer) o[12]);
				usuario.setNome((String) o[13]);
				usuario.setApelido((String) o[14]);
				evento.setUsuario(usuario);
				evento.setPaciente(paciente);
				eventos.add(evento);
			}
			return eventos;
		} catch (Exception e) {
			throw new ControllerException(e.getMessage(), e);
		}
	}
	
	public Evento save(Evento evento, Integer idagenda) throws ControllerException{
		evento = edit(evento);
		if(idagenda != null){
			Agenda agenda = agendaFacade.find(idagenda);
			if(agenda.getDataregistro()!= null){
				evento = new Evento();
				throw new ControllerException(Bundle.getValue("evento_saved_before"));
			}
			agenda.setIdevento(evento.getIdevento());
			agenda.setData(evento.getDataregistro());
			agendaFacade.edit(agenda);
		}
		return evento;
	}

}
