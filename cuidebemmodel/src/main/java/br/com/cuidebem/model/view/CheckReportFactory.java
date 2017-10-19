package br.com.cuidebem.model.view;

import java.util.ArrayList;
import java.util.List;

import br.com.cuidebem.model.Evento;
import br.com.cuidebem.model.def.Check;
import br.com.cuidebem.model.util.DateUtil;

public class CheckReportFactory {
	
	public static List<CheckReport> mountcheckin(List<Evento> eventos){
		List<CheckReport> list = new ArrayList<>();
		
		if(eventos != null && !eventos.isEmpty()){
			List<Evento> _eventos = new ArrayList<>(eventos);
			if(_eventos.get(0).getGrupoevento().compareTo(Check.CHECKOUT.getDescricao())==0){
				_eventos.remove(0);
			}
			int tam = _eventos.size();
			if(tam <2){
				return list;
			}
			 if(tam%2!=0){
				 tam = tam -1;
			 }
			for(int i =0; i<tam;i++){
				Evento evento1 = _eventos.get(i);
				i = i+1;
				Evento evento2 = _eventos.get(i);
				CheckReport checkReport = new CheckReport();
				checkReport.setEntrada(evento1.getDataregistro());
				checkReport.setSaida(evento2.getDataregistro());
				String tempo = DateUtil.getDifHourMinute(evento1.getDataregistro(), evento2.getDataregistro());
				checkReport.setTempo(tempo);
				checkReport.setObsEntrada(evento1.getObsevento());
				checkReport.setObsSaida(evento2.getObsevento());
				list.add(checkReport);
			}
		}
		
		return list;
	}
	
	public static List<CheckReport> mountcheckout(List<Evento> eventos){
		List<CheckReport> list = new ArrayList<>();
		
		if(eventos != null && !eventos.isEmpty()){
			List<Evento> _eventos = new ArrayList<>(eventos);
			if(_eventos.get(0).getGrupoevento().compareTo(Check.CHECKIN.getDescricao())==0){
				_eventos.remove(0);
			}
			int tam = _eventos.size();
			if(tam <2){
				return list;
			}
			 if(tam%2!=0){
				 tam = tam -1;
			 }
			
			for(int i =0; i<tam;i++){
				Evento evento1 = _eventos.get(i);
				i = i+1;
				Evento evento2 = _eventos.get(i);
				CheckReport checkReport = new CheckReport();
				checkReport.setSaida(evento1.getDataregistro());
				checkReport.setEntrada(evento2.getDataregistro());
				String tempo = DateUtil.getDifHourMinute(evento1.getDataregistro(),evento2.getDataregistro());
				checkReport.setTempo(tempo);
				checkReport.setObsEntrada(evento1.getObsevento());
				checkReport.setObsSaida(evento2.getObsevento());
				list.add(checkReport);
			}
		}
		
		return list;
	}
	
	public static List<CheckReport> mountcheck(List<Evento> eventos,boolean checkin){
		if(checkin){
			return mountcheckin(eventos);
		}
		return mountcheckout(eventos);
	}

}
