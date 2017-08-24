/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cuidebem.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.ejb.Stateless;

import br.com.cuidebem.controller.exception.ControllerException;
import br.com.cuidebem.model.PacientePhoto;
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
    
    
    public PacientePhoto create(InputStream photo, Integer idpaciente) throws ControllerException{
    
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			br.com.cuidebem.model.util.PhotoUtil.resize(photo, baos);
			baos.flush();
			byte[] imageInByte = baos.toByteArray();
			PacientePhoto pacientePhoto = find(idpaciente);
			if(pacientePhoto == null){
				pacientePhoto = new PacientePhoto();
				pacientePhoto.setIdpaciente(idpaciente);
			}
			pacientePhoto.setPhoto(imageInByte);
			pacientePhoto = edit(pacientePhoto);
			baos.close();
			return  pacientePhoto;
		} catch (IOException e) {
			throw new ControllerException(Bundle.getValue("errorprocessfoto"),e);
		}
	
    }
}
