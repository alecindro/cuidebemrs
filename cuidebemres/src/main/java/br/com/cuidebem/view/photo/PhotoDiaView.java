package br.com.cuidebem.view.photo;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.com.cuidebem.controller.PacientePhotoFacade;
import br.com.cuidebem.controller.exception.ControllerException;
import br.com.cuidebem.model.PacientePhoto;
import br.com.cuidebem.model.util.DateUtil;
import br.com.cuidebem.translate.Bundle;
import br.com.cuidebem.view.IndexView;
import br.com.cuidebem.view.util.JsfUtil;

@Named("photodiaview")
@RequestScoped
public class PhotoDiaView extends IndexView{
	@EJB
	private PacientePhotoFacade pacientePhotoFacade;
	private List<PacientePhoto> photos;
	private Integer idpaciente;
	private Date dataevento;
	
	@PostConstruct
	private void init(){
		String _idpaciente = JsfUtil.getRequestParameter("idpaciente");
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
		
		if(_idpaciente != null){
			
			try {
				idpaciente = Integer.valueOf(_idpaciente);
				loadPhotos();
			} catch (NumberFormatException | ControllerException e) {
				JsfUtil.addErrorMessage(Bundle.getValue("error.loadphoto"));
			}
		}
	
	}
	
	private void loadPhotos() throws ControllerException{
		photos = pacientePhotoFacade.photoDay(idpaciente, dataevento);
	}

	public List<PacientePhoto> getPhotos() {
		return photos;
	}

	public void setPhotos(List<PacientePhoto> photos) {
		this.photos = photos;
	}
	
	public void delete(Integer idpacientephoto){
		try {
			pacientePhotoFacade.delete(idpacientephoto);
			loadPhotos();
		} catch (ControllerException e) {
			JsfUtil.addErrorMessage(Bundle.getValue("error.delphoto"));
		}
	}
	
	
}
