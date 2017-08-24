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
import br.com.cuidebem.model.ResponsavelPhoto;
import br.com.cuidebem.model.util.PhotoUtil;
import br.com.cuidebem.translate.Bundle;


/**
 *
 * @author aleci
 */
@Stateless
public class ResponsavelPhotoFacade extends AbstractFacade<ResponsavelPhoto> {
    

    public ResponsavelPhotoFacade() {
        super(ResponsavelPhoto.class);
    }
    
    public ResponsavelPhoto create(InputStream photo, Integer idresponsavel) throws ControllerException{
        
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			PhotoUtil.resize(photo, baos);
			baos.flush();
			byte[] imageInByte = baos.toByteArray();
			ResponsavelPhoto responsavelPhoto = find(idresponsavel);
			if(responsavelPhoto == null){
				responsavelPhoto = new ResponsavelPhoto();
				responsavelPhoto.setIdresponsavel(idresponsavel);
			}
			responsavelPhoto.setPhoto(imageInByte);
			responsavelPhoto = edit(responsavelPhoto);
			baos.close();
			return  responsavelPhoto;
		} catch (IOException e) {
			throw new ControllerException(Bundle.getValue("errorprocessfoto"),e);
		}
	
    }

    
}
