package br.com.cuidebem.view.evento;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;

import br.com.cuidebem.controller.ResponsavelFacade;
import br.com.cuidebem.controller.email.EventoEmailService;
import br.com.cuidebem.controller.exception.ControllerException;
import br.com.cuidebem.model.Responsavel;
import br.com.cuidebem.translate.Bundle;
import br.com.cuidebem.view.IndexView;
import br.com.cuidebem.view.util.JsfUtil;

@Named("eventoemailmb")
@RequestScoped
public class EventoEmailView extends IndexView{

	private List<SelectItem> selectResponsaveis;
	private List<Responsavel> responsaveis;
	private Integer[] idresponsaveis;
	private Integer idpaciente;
	private Date date;
	private String message;
	@EJB
	private ResponsavelFacade responsavelFacade;
	@EJB
	private EventoEmailService ems;
	
	@PostConstruct
	private void init(){
		date= Calendar.getInstance().getTime();
		String _id = JsfUtil.getRequestParameter("idpaciente");
		if (_id == null) {
			message = Bundle.getValue("error.loadreponsaveis");
		}else{
			idpaciente = Integer.valueOf(_id);
		try {
			responsaveis = responsavelFacade.loadByPaciente(idpaciente);
			if(responsaveis.isEmpty()){
				message= Bundle.getValue("responsavelnorecord");
			}
			loadItems(responsaveis);
		} catch (ControllerException e) {
			message = Bundle.getValue("error.loadreponsaveis");
			e.printStackTrace();
		}
		}
		
	}
	
	private void loadItems(List<Responsavel> responsaveis){
		selectResponsaveis = new ArrayList<SelectItem>();
		for(Responsavel responsavel : responsaveis){
			SelectItem si = new SelectItem(responsavel.getIdresponsavel(), responsavel.getNome());
			selectResponsaveis.add(si);
		}
	}
	
	public void sendEmail(){
		List<Integer> ids = Arrays.asList(idresponsaveis);
		List<Responsavel> selecteds = responsaveis.stream().filter(r -> ids.contains(r.getIdresponsavel())).collect(Collectors.toList());
		try {
			ems.sendEmail(selecteds, getResidencia(), idpaciente,date);
			JsfUtil.addSuccessMessage(Bundle.getValue("email_sucess"));
		} catch (ControllerException e) {
			JsfUtil.addErrorMessage(Bundle.getValue("error_sendemail"));
		}
	}

	
	public List<SelectItem> getSelectResponsaveis() {
		return selectResponsaveis;
	}

	public void setSelectResponsaveis(List<SelectItem> selectResponsaveis) {
		this.selectResponsaveis = selectResponsaveis;
	}

	
	public  Integer[] getIdresponsaveis() {
		return idresponsaveis;
	}

	public void setIdresponsaveis( Integer[] idresponsaveis) {
		this.idresponsaveis = idresponsaveis;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getIdpaciente() {
		return idpaciente;
	}

	public void setIdpaciente(Integer idpaciente) {
		this.idpaciente = idpaciente;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
}
