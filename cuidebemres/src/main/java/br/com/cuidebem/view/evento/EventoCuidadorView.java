package br.com.cuidebem.view.evento;

import java.util.Calendar;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.ListDataModel;
import javax.inject.Named;

import br.com.cuidebem.controller.EventoFacade;
import br.com.cuidebem.controller.UsuarioFacade;
import br.com.cuidebem.controller.exception.ControllerException;
import br.com.cuidebem.model.Evento;
import br.com.cuidebem.model.Usuario;
import br.com.cuidebem.view.util.JsfUtil;

@Named("eventocuidadormb")
@RequestScoped
public class EventoCuidadorView {

	private Usuario cuidador;
	private ListDataModel<Evento> eventos;
	private Date dataEvento;
	@EJB
	private EventoFacade eventoFacade;
	@EJB
	private UsuarioFacade usuarioFacade;
	
	@PostConstruct
	private void init(){
		dataEvento = Calendar.getInstance().getTime();
		String _id = JsfUtil.getRequestParameter("idcuidador");
		if (_id != null) {
			Integer idcuidador = Integer.valueOf(_id);
			cuidador = usuarioFacade.find(idcuidador);
			loadEventos();
		} else{
			JsfUtil.addErrorMessage("error.loadeventos");
		}
	}
	
	private void loadEventos(){
		if(cuidador != null){
			try {
				eventos = new ListDataModel<>(eventoFacade.findEnabledByCuidador(cuidador, dataEvento));
			} catch (ControllerException e) {
				JsfUtil.addErrorMessage(e,"error.loadeventos");
			}
		}
	}

	public Usuario getCuidador() {
		return cuidador;
	}

	public void setCuidador(Usuario cuidador) {
		this.cuidador = cuidador;
	}

	public ListDataModel<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(ListDataModel<Evento> eventos) {
		this.eventos = eventos;
	}
	
	
	
}
