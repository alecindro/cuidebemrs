package br.com.cuidebem.controller;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TemporalType;

import br.com.cuidebem.controller.exception.ControllerException;
import br.com.cuidebem.controller.util.DateUtil;
import br.com.cuidebem.model.Agenda;
import br.com.cuidebem.model.Agendadef;

@Stateless
public class AgendaFacade extends AbstractFacade<Agenda> {

	public AgendaFacade() {
		super(Agenda.class);
	}

	public void save(Agendadef agendadef) throws ControllerException {
		if (agendadef.isDiaspersonalizado()) {
			saveDiasPersonalizado(agendadef);
			return;
		}
		if (agendadef.getRepetirHoras() != null) {
			saveRepetirHoras(agendadef);
			return;
		}
		List<Date> dates = DateUtil.listDates(agendadef.getDatainicio(), agendadef.getDatafim(),
				agendadef.getHorario());
		save(getAgendas(agendadef, dates));

	}
	
	public void edit(Agendadef agendadef) throws ControllerException{
		delete(agendadef);
		List<Date> dates = DateUtil.listDates(agendadef.getDatainicio(), agendadef.getDatafim(),
				agendadef.getHorario());
		save(getAgendas(agendadef, dates));
	}
	
	public int count(Agendadef agendadef){
		javax.persistence.Query query = getEntityManager().createNamedQuery("Agenda.countByAgendaDef");
		query.setParameter("idagendadef", agendadef.getIdagendadef());
		return ((Long) query.getSingleResult()).intValue();
	}
	
	private List<Agenda> getAgendas(Agendadef agendadef, List<Date> dates){
		List<Agenda> agendas = new ArrayList<Agenda>();
		for (Date date : dates) {
			Agenda agenda = new Agenda(agendadef);
			agenda.setData(date);
			agendas.add(agenda);
		}
		return agendas;
	}

	private void saveDiasPersonalizado(Agendadef agendadef) throws ControllerException {
		List<Date> dates = DateUtil.listDates(agendadef.getDatainicio(), agendadef.getDatafim(), agendadef.getHorario(),
				agendadef.getDias());
		save(getAgendas(agendadef, dates));
	}

	private void saveRepetirHoras(Agendadef agendadef) throws ControllerException {
		List<Date> dates = DateUtil.listDates(agendadef.getDatainicio(), agendadef.getDatafim(), agendadef.getHorario(),
				agendadef.getRepetirHoras());
		save(getAgendas(agendadef, dates));

	}

	private void save(List<Agenda> agendas) throws ControllerException {
		String params = "";
		int size = agendas.size();
		for(int i = 0; i<size;i++){
			if(i==0){
			params = Agenda.VALUES;
			}else{
				params = params.concat(",").concat(Agenda.VALUES);
			}
		}
		String sql = MessageFormat.format(Agenda.SAVE_LIST,params);
		
		try{
			javax.persistence.Query query = getEntityManager().createNativeQuery(sql);
			for(int i=0;i<size;i++){
				Agenda agenda = agendas.get(i);
				int j = 6*i;
				query.setParameter(j+1, agenda.getData(),TemporalType.TIMESTAMP);
				query.setParameter(j+2, agenda.getGrupoEvento());
				query.setParameter(j+3, agenda.getSubGrupoEvento());
				query.setParameter(j+4, agenda.getObservacao());
				query.setParameter(j+5, agenda.getIdagendadef());
				query.setParameter(j+6, agenda.getIdpaciente());
			}
		query.executeUpdate();
		}catch(Exception e){
			throw new ControllerException(e.getMessage(), e);
		}

	}
	
	public void delete(Agendadef agendadef) throws ControllerException{
		String sql = MessageFormat.format(Agenda.DELETE_BYAGENDADEF,agendadef.getIdagendadef());
		try{
			getEntityManager().createNativeQuery(sql).executeUpdate();
			}catch(Exception e){
				throw new ControllerException(e.getMessage(), e);
			}
	}

	public List<Agenda> findByIdPaciente(Integer idpaciente, Date dataInicio, Date dataFim) throws ControllerException{
		QueryParameter parameters = QueryParameter.init("idpaciente", idpaciente);
		parameters.add("data1", dataInicio);
		parameters.add("data2", dataFim);
		return findWithNamedQuery("Agenda.findByIdPaciente", parameters, 0);
	}
	
	public Agenda findNext(Integer idpaciente) throws ControllerException{
		List<Agenda> agendas = findByNativeQuery("Agenda.next", idpaciente);
		if(agendas != null && !agendas.isEmpty()){
			return agendas.get(0);
		}
		return new Agenda();
	}
	
}
