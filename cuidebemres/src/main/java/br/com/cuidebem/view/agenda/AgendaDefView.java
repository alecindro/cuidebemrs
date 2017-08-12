package br.com.cuidebem.view.agenda;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;

import br.com.cuidebem.controller.util.DateUtil;
import br.com.cuidebem.rotinas.Rotinas;
import br.com.cuidebem.view.IndexView;
import br.com.cuidebem.view.util.JsfUtil;

@Named("agendadefmb")
@RequestScoped
public class AgendaDefView extends IndexView {

	private Date dataInicio;
	private Date dataFim;
	private Date dataRegistro;
	private String horario;
	private boolean repetir;
	private String[] daysOfWeekSelected;
	private Integer repetirHorario;
	private String grupEventoSelected;
	private String subgrupEventoSelected;
	private List<String> grupoEvento;
	private List<String> subGrupoEvento;
	private String observacao;
	private ScheduleModel eventModel;

	@PostConstruct
	private void init() {
		String geSel = JsfUtil.getRequestParameter("grupEventoSelected");
		Date atual = Calendar.getInstance().getTime();
		dataInicio = atual;
		dataFim = atual;
		dataRegistro = atual;
		repetir = false;
		grupoEvento = Rotinas.getGrupoEventos();
		subGrupoEvento = new ArrayList<String>();
		eventModel = new DefaultScheduleModel();
		Date data = Calendar.getInstance().getTime();
		Date dataInicial = DateUtil.convertHour(data, "19:00");
		Date last = DateUtil.convertHour(data, "19:00");
		eventModel.addEvent(
				new DefaultScheduleEvent("grupo" + " " + "subgrupo", dataInicial, last));
		try {
			horario = DateUtil.convertHour(atual);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void save() {

		Date dataInicial = DateUtil.convertHour(dataInicio, horario);
		Date last = DateUtil.convertHour(dataFim, "21:00");
		eventModel.addEvent(
				new DefaultScheduleEvent(grupEventoSelected + " " + subgrupEventoSelected, dataInicial, last));

	}

	public String getGrupEventoSelected() {
		return grupEventoSelected;
	}

	public void setGrupEventoSelected(String grupEventoSelected) {
		this.grupEventoSelected = grupEventoSelected;
		subGrupoEvento = Rotinas.getSubGrupoEventos(grupEventoSelected);
	}

	public List<String> getGrupoEvento() {
		return grupoEvento;
	}

	public void setGrupoEvento(List<String> grupoEvento) {
		this.grupoEvento = grupoEvento;
	}

	public List<String> getSubGrupoEvento() {
		return subGrupoEvento;
	}

	public void setSubGrupoEvento(List<String> subGrupoEvento) {
		this.subGrupoEvento = subGrupoEvento;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public boolean isRepetir() {
		return repetir;
	}

	public void setRepetir(boolean repetir) {
		this.repetir = repetir;
	}

	public String[] getDaysOfWeekSelected() {
		return daysOfWeekSelected;
	}

	public void setDaysOfWeekSelected(String[] daysOfWeekSelected) {
		this.daysOfWeekSelected = daysOfWeekSelected;
	}

	public Integer getRepetirHorario() {
		return repetirHorario;
	}

	public void setRepetirHorario(Integer repetirHorario) {
		this.repetirHorario = repetirHorario;
	}

	public String getSubgrupEventoSelected() {
		return subgrupEventoSelected;
	}

	public void setSubgrupEventoSelected(String subgrupEventoSelected) {
		this.subgrupEventoSelected = subgrupEventoSelected;
	}

	public String getObservacao() {
		return observacao;
	}

	public ScheduleModel getEventModel() {
		return eventModel;
	}

	public void setEventModel(ScheduleModel eventModel) {
		this.eventModel = eventModel;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

}
