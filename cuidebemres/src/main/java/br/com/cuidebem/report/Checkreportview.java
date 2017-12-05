package br.com.cuidebem.report;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;

import br.com.cuidebem.controller.EventoFacade;
import br.com.cuidebem.controller.PacienteFacade;
import br.com.cuidebem.controller.exception.ControllerException;
import br.com.cuidebem.model.Paciente;
import br.com.cuidebem.model.util.DateUtil;
import br.com.cuidebem.model.view.CheckReport;
import br.com.cuidebem.view.IndexView;
import br.com.cuidebem.view.util.JsfUtil;


@Named("checkReport")
@RequestScoped
public class Checkreportview extends IndexView {
	
	private Date datainicio;
	private Date datafim;
	private Integer idpaciente;
	private SelectItem[] pacientes;
	@EJB 
	private PacienteFacade pacienteFacade;
	@EJB
	private EventoFacade eventoFacade;
	private List<CheckReport> list;
	
	private String totalTime;
	
	@PostConstruct
	private void init(){
		list = new ArrayList<CheckReport>();
		datainicio = Calendar.getInstance().getTime();
		datafim = datainicio;
		List<Paciente> _pacientes;
		try {
			_pacientes = pacienteFacade.findEnabled(getIdresidencia());
			pacientes = getItems(_pacientes);
		} catch (ControllerException e) {
			JsfUtil.addErrorMessage(e.getMessage());
		}
		
	}
	
	private  SelectItem[] getItems(List<Paciente> entities) {
        SelectItem[] items = new SelectItem[entities.size()];
        int i = 0;
        for (Paciente x : entities) {
            items[i++] = new SelectItem(x.getIdpaciente(), x.getApelido());
        }
        return items;
    }
	
	public void checkin(){
		try {
		   list = eventoFacade.reportCheck(idpaciente, datainicio, datafim, true);
		   calculateTime();
		} catch (ControllerException e) {
			JsfUtil.addErrorMessage(e.getMessage());
		}
	}
	
	public void checkout(){
		try {
			list = eventoFacade.reportCheck(idpaciente, datainicio, datafim, false);
			calculateTime();
		} catch (ControllerException e) {
			JsfUtil.addErrorMessage(e.getMessage());
		}
	}
	
	private void calculateTime(){
		int _totalTime = 0;
		for(CheckReport check : list){
			_totalTime = _totalTime + DateUtil.toMinutes(check.getTempo());
		}
		totalTime = DateUtil.totalHours(_totalTime);
	}

	public Date getDatainicio() {
		return datainicio;
	}

	public void setDatainicio(Date datainicio) {
		this.datainicio = datainicio;
	}

	public Date getDatafim() {
		return datafim;
	}

	public void setDatafim(Date datafim) {
		this.datafim = datafim;
	}

	public Integer getIdpaciente() {
		return idpaciente;
	}

	public void setIdpaciente(Integer idpaciente) {
		this.idpaciente = idpaciente;
	}

	public SelectItem[] getPacientes() {
		return pacientes;
	}

	public void setPacientes(SelectItem[] pacientes) {
		this.pacientes = pacientes;
	}

	public List<CheckReport> getList() {
		return list;
	}

	public void setList(List<CheckReport> list) {
		this.list = list;
	}

	public String getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(String totalTime) {
		this.totalTime = totalTime;
	}

	
	

}
