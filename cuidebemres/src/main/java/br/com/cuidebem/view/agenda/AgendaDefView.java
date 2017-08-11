package br.com.cuidebem.view.agenda;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.com.cuidebem.controller.util.DateUtil;
import br.com.cuidebem.rotinas.Rotinas;
import br.com.cuidebem.view.IndexView;

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
	
	private Integer pressaoInitial;
	private Integer pressaoFinal;

	private String qtidade;
	private String aspecto;
	private String opcao;
	private String day;

	@PostConstruct
	private void init() {
		Date atual = Calendar.getInstance().getTime();
		dataInicio = atual;
		dataFim = atual;
		dataRegistro = atual;
		repetir = false;
		grupoEvento = Rotinas.getGrupoEventos();

		try {
			horario = DateUtil.convertHour(atual);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	public Integer getPressaoInitial() {
		return pressaoInitial;
	}

	public void setPressaoInitial(Integer pressaoInitial) {
		this.pressaoInitial = pressaoInitial;
	}

	public Integer getPressaoFinal() {
		return pressaoFinal;
	}

	public void setPressaoFinal(Integer pressaoFinal) {
		this.pressaoFinal = pressaoFinal;
	}

	public String getQtidade() {
		return qtidade;
	}

	public void setQtidade(String qtidade) {
		this.qtidade = qtidade;
	}

	public String getAspecto() {
		return aspecto;
	}

	public void setAspecto(String aspecto) {
		this.aspecto = aspecto;
	}

	public String getOpcao() {
		return opcao;
	}

	public void setOpcao(String opcao) {
		this.opcao = opcao;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}
	public String getSubgrupEventoSelected() {
		return subgrupEventoSelected;
	}
	public void setSubgrupEventoSelected(String subgrupEventoSelected) {
		this.subgrupEventoSelected = subgrupEventoSelected;
	}
	
	
	

}
