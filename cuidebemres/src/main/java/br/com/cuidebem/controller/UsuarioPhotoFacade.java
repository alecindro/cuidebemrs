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
import br.com.cuidebem.model.UsuarioPhoto;
import br.com.cuidebem.translate.Bundle;
import br.com.cuidebem.view.util.PhotoUtil;

/**
 *
 * @author aleci
 */
@Stateless
public class UsuarioPhotoFacade extends AbstractFacade<UsuarioPhoto>  {


    public UsuarioPhotoFacade() {
        super(UsuarioPhoto.class);
    }
    
    public UsuarioPhoto create(InputStream photo, Integer idusuario) throws ControllerException{
    
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			PhotoUtil.resize(photo, baos);
			baos.flush();
			byte[] imageInByte = baos.toByteArray();
			UsuarioPhoto usuarioPhoto = find(idusuario);
			if(usuarioPhoto == null){
				usuarioPhoto = new UsuarioPhoto();
				usuarioPhoto.setIdusuario(idusuario);
			}
			usuarioPhoto.setPhoto(imageInByte);
			usuarioPhoto = edit(usuarioPhoto);
			baos.close();
			return  usuarioPhoto;
		} catch (IOException e) {
			throw new ControllerException(Bundle.getValue("errorprocessfoto"),e);
		}
	
    }
}
