package br.com.cuidebem.view.residencia;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.ListDataModel;
import javax.inject.Named;

import br.com.cuidebem.controller.ResidenciaFacade;
import br.com.cuidebem.controller.ResidenciaTelefoneFacade;
import br.com.cuidebem.controller.TelefoneFacade;
import br.com.cuidebem.controller.exception.ControllerException;
import br.com.cuidebem.model.Telefone;
import br.com.cuidebem.translate.Bundle;
import br.com.cuidebem.view.IndexView;
import br.com.cuidebem.view.util.JsfUtil;

@Named("residenciamb")
@RequestScoped
public class ResidenciaView extends IndexView {

	@EJB
	private ResidenciaFacade residenciaFacade;
	@EJB
	private ResidenciaTelefoneFacade residenciaTelefoneFacade;
	@EJB
	private TelefoneFacade telefoneFacade;
	private ListDataModel<Telefone> telefones;
	private Telefone telefone;

	@PostConstruct
	private void init() {
		telefone = new Telefone();
		loadTelefones();
	}

	private void loadTelefones() {
		telefones = new ListDataModel<>();
		try {
			telefones.setWrappedData(residenciaTelefoneFacade.getTelefones(getResidencia()));
		} catch (ControllerException e) {
			JsfUtil.addErrorMessage(e.getMessage());
		}
	}

	public void save() {
		try {
			residenciaFacade.edit(getResidencia());
			JsfUtil.addSuccessMessage(Bundle.getValue("cadsucesso"));
		} catch (ControllerException e) {
			JsfUtil.addErrorMessage(e.getMessage());
		}
	}

	public void saveTelefone() {
		try {
			telefoneFacade.save(telefone, getResidencia());
			loadTelefones();
			telefone = new Telefone();
			JsfUtil.addSuccessMessage(Bundle.getValue("cadsucesso"));
		} catch (ControllerException e) {
			JsfUtil.addErrorMessage(e.getMessage());
		}
	}

	public void delPhone() {
		Telefone tel = telefones.getRowData();
		try {
			telefoneFacade.remove(tel);
			loadTelefones();
			JsfUtil.addSuccessMessage(Bundle.getValue("del.sucess"));
		} catch (ControllerException e) {
			JsfUtil.addErrorMessage(e.getMessage());
		}
	}

	public ListDataModel<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(ListDataModel<Telefone> telefones) {
		this.telefones = telefones;
	}

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

}
