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
import br.com.cuidebem.model.view.CheckReport;
import br.com.cuidebem.model.view.CheckReportFactory;
import br.com.cuidebem.model.view.PacienteEventoAtual;
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
				Evento evento = toEvento(o);
				Paciente paciente = new Paciente();
				paciente.setIdpaciente((Integer) o[18]);
				paciente.setNome((String) o[19]);
				paciente.setApelido((String) o[20]);
				Usuario usuario = new Usuario();
				usuario.setIdusuario((Integer) o[21]);
				usuario.setNome((String) o[22]);
				usuario.setApelido((String) o[23]);
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
				Evento evento = toEvento(o);
				Paciente paciente = new Paciente();
				paciente.setIdpaciente((Integer) o[18]);
				paciente.setNome((String) o[19]);
				paciente.setApelido((String) o[20]);
				Usuario usuario = new Usuario();
				usuario.setIdusuario((Integer) o[21]);
				usuario.setNome((String) o[22]);
				usuario.setApelido((String) o[23]);
				evento.setUsuario(usuario);
				evento.setPaciente(paciente);
				eventos.add(evento);
			}
			return eventos;
		} catch (Exception e) {
			throw new ControllerException(e.getMessage(), e);
		}
	}
	


	public List<Evento> findByPacienteDataregistro(Integer idpaciente, Date date) throws ControllerException {
		try {
			javax.persistence.Query query = getEntityManager().createNativeQuery(Evento.EVENTO_BYDATAPACIENTE);
			query.setParameter(1, idpaciente);
			query.setParameter(2, date);
			@SuppressWarnings("unchecked")
			List<Object[]> tuples = query.getResultList();
			List<Evento> eventos = new ArrayList<Evento>();
			for (Object[] o : tuples) {
				Evento evento = toEvento(o);
				Paciente paciente = new Paciente();
				paciente.setIdpaciente((Integer) o[18]);
				paciente.setNome((String) o[19]);
				paciente.setApelido((String) o[20]);
				Usuario usuario = new Usuario();
				usuario.setIdusuario((Integer) o[21]);
				usuario.setNome((String) o[22]);
				usuario.setApelido((String) o[23]);
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
				Evento evento = toEvento(o);
				Paciente paciente = new Paciente();
				paciente.setIdpaciente((Integer) o[18]);
				paciente.setNome((String) o[19]);
				paciente.setApelido((String) o[20]);
				Usuario usuario = new Usuario();
				usuario.setIdusuario((Integer) o[21]);
				usuario.setNome((String) o[22]);
				usuario.setApelido((String) o[23]);
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
				Evento evento = toEvento(o);
				Paciente paciente = new Paciente();
				paciente.setIdpaciente((Integer) o[18]);
				paciente.setNome((String) o[19]);
				paciente.setApelido((String) o[20]);
				Usuario usuario = new Usuario();
				usuario.setIdusuario((Integer) o[21]);
				usuario.setNome((String) o[22]);
				usuario.setApelido((String) o[23]);
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
			agenda.setDataregistro(evento.getDataregistro());
			agendaFacade.edit(agenda);
		}
		return evento;
	}
	
	public void delete(Integer idevento) throws ControllerException{
		Evento evento = find(idevento);
		evento.setEnabled(false);
		edit(evento);
		getEntityManager().getEntityManagerFactory().getCache().evict(PacienteEventoAtual.class);
	}
	
	public List<CheckReport> reportCheck(Integer idpaciente, Date inicio, Date fim,boolean checkin) throws ControllerException{
		List<Evento> eventos = findByNativeQuery("Evento.check", idpaciente,inicio,fim);
		return CheckReportFactory.mountcheck(eventos, checkin);
	}
	
	private Evento toEvento(Object[] tuple){
		Evento evento = new Evento();
		evento.setIdevento((Integer) tuple[0]);
		evento.setIdpaciente((Integer) tuple[1]);
		evento.setIdusuario((Integer) tuple[2]);
		evento.setDescevento((String) tuple[3]);
		evento.setDataevento((Date) tuple[4]);
		evento.setDataregistro((Date) tuple[5]);
		evento.setEnabled((String.valueOf(tuple[6])).equals("1"));
		evento.setObsevento((String) tuple[7]);
		evento.setGrupoevento((String) tuple[8]);
		evento.setSubgrupoevento((String) tuple[9]);
		evento.setRespeventos((String) tuple[10]);
		evento.setPeso((Integer) tuple[11]);
		evento.setPressaoinicial((Integer) tuple[12]);
		evento.setPressaofinal((Integer) tuple[13]);
		evento.setValue((Integer) tuple[14]);
		evento.setDescricao((String) tuple[15]);
		evento.setAspecto((String) tuple[16]);
		evento.setQuantidade((String) tuple[17]);
		return evento;
	}
	

}
