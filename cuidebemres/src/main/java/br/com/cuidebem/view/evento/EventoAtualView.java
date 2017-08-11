package br.com.cuidebem.view.evento;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.ListDataModel;
import javax.inject.Named;

import br.com.cuidebem.controller.AniversarioFacade;
import br.com.cuidebem.controller.CuidadorEventoAtualFacade;
import br.com.cuidebem.controller.PacienteEventoAtualFacade;
import br.com.cuidebem.model.view.Aniversario;
import br.com.cuidebem.model.view.CuidadorEventoAtual;
import br.com.cuidebem.model.view.PacienteEventoAtual;
import br.com.cuidebem.view.IndexView;
import br.com.cuidebem.view.util.JsfUtil;

@Named("eventoatualmb")
@RequestScoped
public class EventoAtualView extends IndexView {

	@EJB
	private PacienteEventoAtualFacade pacienteEventoAtualFacade;
	private ListDataModel<PacienteEventoAtual> pacientesEvento;
	@EJB 
	private CuidadorEventoAtualFacade cuidadorEventoAtualFacade;
	private ListDataModel<CuidadorEventoAtual> cuidadoresEvento;
	@EJB
	private AniversarioFacade aniversarioFacade;
	private ListDataModel<Aniversario> aniversarios;
	

	@PostConstruct
	private void init() {
		try {
			pacientesEvento = new ListDataModel<>(pacienteEventoAtualFacade.findByResidencia(getIdresidencia()));
		} catch (Exception e) {
			JsfUtil.addErrorMessage(e.getMessage());
		}
		try {
			cuidadoresEvento = new ListDataModel<>(cuidadorEventoAtualFacade.findByResidencia(getIdresidencia()));
		} catch (Exception e) {
			JsfUtil.addErrorMessage(e.getMessage());
		}
		try {
			aniversarios = new ListDataModel<>(aniversarioFacade.load(getIdresidencia()));
		} catch (Exception e) {
			JsfUtil.addErrorMessage(e.getMessage());
		}
	}

	public ListDataModel<PacienteEventoAtual> getPacientesEvento() {
		return pacientesEvento;
	}

	public void setPacientesEvento(ListDataModel<PacienteEventoAtual> pacientesEvento) {
		this.pacientesEvento = pacientesEvento;
	}

	public ListDataModel<CuidadorEventoAtual> getCuidadoresEvento() {
		return cuidadoresEvento;
	}

	public void setCuidadoresEvento(ListDataModel<CuidadorEventoAtual> cuidadoresEvento) {
		this.cuidadoresEvento = cuidadoresEvento;
	}

	public ListDataModel<Aniversario> getAniversarios() {
		return aniversarios;
	}

	public void setAniversarios(ListDataModel<Aniversario> aniversarios) {
		this.aniversarios = aniversarios;
	}
	
	


}
