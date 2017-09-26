package br.com.cuidebem.view.evento;

import java.util.Calendar;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.ListDataModel;
import javax.inject.Named;

import br.com.cuidebem.controller.AniversarioFacade;
import br.com.cuidebem.controller.PacienteEventoAtualFacade;
import br.com.cuidebem.model.view.Aniversario;
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
	private AniversarioFacade aniversarioFacade;
	private ListDataModel<Aniversario> aniversarios;
	
	private Date dataAtualizacao;
	

	@PostConstruct
	private void init() {
		try {
			dataAtualizacao = Calendar.getInstance().getTime();
			pacientesEvento = new ListDataModel<>(pacienteEventoAtualFacade.findByResidencia(getIdresidencia()));
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


	public ListDataModel<Aniversario> getAniversarios() {
		return aniversarios;
	}

	public void setAniversarios(ListDataModel<Aniversario> aniversarios) {
		this.aniversarios = aniversarios;
	}

	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}
	
	
	


}
