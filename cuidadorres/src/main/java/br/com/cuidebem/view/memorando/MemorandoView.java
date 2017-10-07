package br.com.cuidebem.view.memorando;

import java.util.Calendar;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.com.cuidebem.controller.MemorandoFacade;
import br.com.cuidebem.controller.exception.ControllerException;
import br.com.cuidebem.model.Memorando;
import br.com.cuidebem.model.util.DateUtil;
import br.com.cuidebem.translate.Bundle;
import br.com.cuidebem.view.IndexView;
import br.com.cuidebem.view.util.JsfUtil;


@Named("memomb")
@RequestScoped
public class MemorandoView extends IndexView{
	
	@EJB
	private MemorandoFacade memorandoFacade;
	private Memorando memorando;
	private String idpaciente;
	private Date dataevento;
	
	
	@PostConstruct
	private void init(){
		String idpaciente = JsfUtil.getRequestParameter("idpaciente");
		dataevento = Calendar.getInstance().getTime();
		String _dataevento = JsfUtil.getRequestParameter("dataevento");
		if(_dataevento != null){
			try {
				dataevento = DateUtil.convertDate(_dataevento);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(idpaciente != null){
			
			try {
				Integer _idpaciente = Integer.valueOf(idpaciente);
				memorando = memorandoFacade.loadbyDateAtual(_idpaciente, dataevento);
				if(memorando.getIdmemorando() == null){
					memorando.setIdpaciente(_idpaciente);
				}
			} catch (NumberFormatException | ControllerException e) {
				JsfUtil.addErrorMessage(Bundle.getValue("error.loadmemorando"));
				memorando = new Memorando();
			}
		}
	}
	
	public String save(){
		try {
			if(memorando.getDataregistro() == null){
				memorando.setDataregistro(new Date());
			} else{
				memorando.setDataalteracao(new Date());
			}
			memorandoFacade.edit(memorando);
			return "/app/paciente/pacienteevento.xhtml?idpaciente=".concat(String.valueOf(memorando.getIdpaciente()));
		} catch (ControllerException e) {
			JsfUtil.addErrorMessage(Bundle.getValue("error.save"));
			e.printStackTrace();
		}
		return null;
	}

	public Memorando getMemorando() {
		return memorando;
	}

	public void setMemorando(Memorando memorando) {
		this.memorando = memorando;
	}
	
	

}
