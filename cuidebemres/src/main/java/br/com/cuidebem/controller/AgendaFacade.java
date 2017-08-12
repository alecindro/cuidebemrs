package br.com.cuidebem.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;

import br.com.cuidebem.controller.util.DateUtil;
import br.com.cuidebem.model.Agenda;
import br.com.cuidebem.model.AgendaDef;

@Stateless
public class AgendaFacade extends AbstractFacade<Agenda> {

	public AgendaFacade() {
		super(Agenda.class);
	}

	public void save(AgendaDef agendaDef) {
		if (agendaDef.isDiaspersonalizado()) {
			saveDiasPersonalizado(agendaDef);
			return;
		}
		if (agendaDef.getRepetirHoras() != null) {
			saveRepetirHoras(agendaDef);
			return;
		}
		List<Date> dates = DateUtil.listDates(agendaDef.getDataInicio(), agendaDef.getDataFim(),
				agendaDef.getHorario());
		List<Agenda> agendas = new ArrayList<Agenda>();
		for (Date date : dates) {
			Agenda agenda = new Agenda(agendaDef);
			agenda.setData(date);
			agendas.add(agenda);
		}
		save(agendas);

	}

	private void saveDiasPersonalizado(AgendaDef agendaDef) {
		List<Date> dates = DateUtil.listDates(agendaDef.getDataInicio(), agendaDef.getDataFim(), agendaDef.getHorario(),
				agendaDef.getDiasSemana());
		List<Agenda> agendas = new ArrayList<Agenda>();
		for (Date date : dates) {
			Agenda agenda = new Agenda(agendaDef);
			agenda.setData(date);
			agendas.add(agenda);
		}
		save(agendas);
	}

	private void saveRepetirHoras(AgendaDef agendaDef) {
		List<Date> dates = DateUtil.listDates(agendaDef.getDataInicio(), agendaDef.getDataFim(), agendaDef.getHorario(),
				agendaDef.getRepetirHoras());
		List<Agenda> agendas = new ArrayList<Agenda>();
		for (Date date : dates) {
			Agenda agenda = new Agenda(agendaDef);
			agenda.setData(date);
			agendas.add(agenda);
		}
		save(agendas);

	}

	private void save(List<Agenda> agendas) {

	}

	private void save(Agenda agenda) {

	}
}
