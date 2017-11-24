/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cuidebem.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;

import br.com.cuidebem.controller.exception.ControllerException;
import br.com.cuidebem.model.PacientePhoto;
import br.com.cuidebem.model.util.PhotoUtil;
import br.com.cuidebem.model.view.PhotoDescricao;
import br.com.cuidebem.translate.Bundle;


/**
 *
 * @author aleci
 */
@Stateless
public class PacientePhotoFacade extends AbstractFacade<PacientePhoto>  {

    

    public PacientePhotoFacade() {
        super(PacientePhoto.class);
    }
    
    
    public PacientePhoto create(InputStream photo, Integer idpaciente, String typePhoto) throws ControllerException{
    
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			br.com.cuidebem.model.util.PhotoUtil.resize(photo, baos);
			baos.flush();
			byte[] imageInByte = baos.toByteArray();
			PacientePhoto pacientePhoto = findByPaciente(idpaciente);
			if(pacientePhoto == null){
				pacientePhoto = new PacientePhoto();
				pacientePhoto.setIdpaciente(idpaciente);
			}
			pacientePhoto.setDataregistro(Calendar.getInstance().getTime());
			pacientePhoto.setPrincipal(true);
			pacientePhoto.setPhoto(imageInByte);
			pacientePhoto.setType(typePhoto);
			pacientePhoto = edit(pacientePhoto);
			baos.close();
			return  pacientePhoto;
		} catch (IOException e) {
			throw new ControllerException(Bundle.getValue("errorprocessfoto"),e);
		}
	}
    
    public PacientePhoto findByPaciente(Object idpaciente) {
    	PacientePhoto pacientePhoto = null;
    	List<PacientePhoto> photos;
		try {
			photos = findWithNamedQuery("PacientePhoto.findByIdpacientePrincipal", QueryParameter.init("idpaciente", idpaciente),1);
			if(photos != null && !photos.isEmpty()){
	    		pacientePhoto = photos.get(0);
	    	}
		} catch (ControllerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return pacientePhoto;
    }
    
    public List<PacientePhoto> photoDay(Integer idpaciente, Date date) throws ControllerException{
    
    	return findByNativeQuery("PacientePhoto.findByPhotoDiaria", idpaciente,date);
    }
    
    public PacientePhoto photoDay(PhotoDescricao photo) throws ControllerException{
    	try {
    	PacientePhoto pacientePhoto = new PacientePhoto();
    	pacientePhoto.setDataregistro(Calendar.getInstance().getTime());
    	pacientePhoto.setDescricao(photo.getDescricao());
    	pacientePhoto.setIdpaciente(photo.getIdpaciente());
    	pacientePhoto.setPrincipal(false);
    	pacientePhoto.setType(photo.getType());
    	pacientePhoto.setIdusuario(photo.getIdusuario());
    	byte[] img = photo.getImgbase64().getBytes("UTF-8");
	      img = PhotoUtil.decoderBase64(img);
	   pacientePhoto.setPhoto(img);
	   pacientePhoto = edit(pacientePhoto);
	   return pacientePhoto;
		} catch (UnsupportedEncodingException e) {
			throw new ControllerException(e.getMessage(),e);
		}
    	
    }
    
    public void delete(Integer idpacientephoto) throws ControllerException{
    	PacientePhoto pacientePhoto  = find(idpacientephoto);
    	remove(pacientePhoto);
    }
}
