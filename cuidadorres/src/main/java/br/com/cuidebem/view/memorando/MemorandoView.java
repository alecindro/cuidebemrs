package br.com.cuidebem.view.memorando;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.com.cuidebem.controller.MemorandoFacade;
import br.com.cuidebem.controller.exception.ControllerException;
import br.com.cuidebem.model.Memorando;
import br.com.cuidebem.translate.Bundle;
import br.com.cuidebem.view.IndexView;
import br.com.cuidebem.view.util.JsfUtil;


@Named("memomb")
@RequestScoped
public class MemorandoView extends IndexView{
	
	@EJB
	private MemorandoFacade memorandoFacade;
	private Memorando memorando;
	
	
	@PostConstruct
	private void init(){
		String idpaciente = JsfUtil.getRequestParameter("idpaciente");
		if(idpaciente != null){
			
			try {
				Integer _idpaciente = Integer.valueOf(idpaciente);
				memorando = memorandoFacade.loadAtual(_idpaciente);
				if(memorando.getIdmemorando() == null){
					memorando.setIdpaciente(_idpaciente);
				}
			} catch (NumberFormatException | ControllerException e) {
				JsfUtil.addErrorMessage(Bundle.getValue("error.loadmemorando"));
				memorando = new Memorando();
			}
		}
	}
	
	public void save(){
		try {
			if(memorando.getDataregistro() == null){
				memorando.setDataregistro(new Date());
			} else{
				memorando.setDataalteracao(new Date());
			}
			memorandoFacade.edit(memorando);
		} catch (ControllerException e) {
			JsfUtil.addErrorMessage(Bundle.getValue("error.save"));
			e.printStackTrace();
		}
	}

	public Memorando getMemorando() {
		return memorando;
	}

	public void setMemorando(Memorando memorando) {
		this.memorando = memorando;
	}
	
	

}
