package br.com.cuidebem.view.agenda;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.com.cuidebem.controller.AgendaFacade;
import br.com.cuidebem.controller.exception.ControllerException;
import br.com.cuidebem.model.Agenda;
import br.com.cuidebem.model.util.DateUtil;
import br.com.cuidebem.view.IndexView;
import br.com.cuidebem.view.util.JsfUtil;

@Named("agendadiaria")
@RequestScoped
public class AgendaDiaria extends IndexView  {

	
	private TreeMap<String, TreeMap<String, List<Agenda>>> tableAgenda;

	private List<String> rowNames;
	private List<String> colNames;
	private static Integer numDays = 1;
	
	@EJB
	private AgendaFacade agendaFacade;

	
	@PostConstruct
	private void init() {
		initData();
		initTableAgenda();
		String _idpaciente = JsfUtil.getRequestParameter("idpaciente");
		if (_idpaciente == null) {
			return;
		}
		Integer idpaciente = Integer.valueOf(_idpaciente);
		Date dataInicio = DateUtil.getZeroHour(Calendar.getInstance().getTime());
		Date dataFim = DateUtil.sumDateDays(dataInicio, numDays);
		try {
			List<Agenda> agendas = agendaFacade.findByIdPaciente(idpaciente, dataInicio, dataFim);
			for (Agenda agenda : agendas) {
				addAgenda(agenda);
			}
	
		} catch (ControllerException e) {
			JsfUtil.addErrorMessage(e.getMessage());
		}

	}

	private void initData() {
		rowNames = new ArrayList<String>();
		Date init = DateUtil.getZeroHour(Calendar.getInstance().getTime());
		colNames = new ArrayList<String>();
		for (int i = 0; i < numDays; i++) {
			Date _date = DateUtil.sumDateDays(init, i);
			colNames.add(DateUtil.dayOfWeek(_date));
		}

	}

	private void initTableAgenda() {
		tableAgenda = new TreeMap<String, TreeMap<String, List<Agenda>>>();
		for (String rowName : rowNames) {
			addHour(rowName);
		}
	}
	
	private void addHour(String horario) {
		tableAgenda.put(horario, new TreeMap<String, List<Agenda>>());
		addDays(tableAgenda.get(horario));
	}

	private void addDays(TreeMap<String, List<Agenda>> days) {
		for (String cols : colNames) {
			days.put(cols, new ArrayList<Agenda>());
		}
	}

	private void addAgenda(String horario, String day, Agenda agenda) {
		tableAgenda.get(horario).get(day).add(agenda);
	}

	public void addAgenda(Agenda agenda) {
		String horario = DateUtil.convertHour(agenda.getData());
		String day = DateUtil.dayOfWeek(agenda.getData());
		if (!rowNames.contains(horario)) {
			rowNames.add(horario);
			Collections.sort(rowNames);
			addHour(horario);
		}
		addAgenda(horario, day, agenda);

	}

	public TreeMap<String, TreeMap<String, List<Agenda>>> getTableAgenda() {
		return tableAgenda;
	}

	public void setTableAgenda(TreeMap<String, TreeMap<String, List<Agenda>>> tableAgenda) {
		this.tableAgenda = tableAgenda;
	}

	public List<String> getRowNames() {
		return rowNames;
	}

	public void setRowNames(List<String> rowNames) {
		this.rowNames = rowNames;
	}

	public List<String> getColNames() {
		return colNames;
	}

	public void setColNames(List<String> colNames) {
		this.colNames = colNames;
	}
	
	
}
