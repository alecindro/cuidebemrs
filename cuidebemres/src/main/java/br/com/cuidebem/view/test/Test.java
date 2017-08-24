package br.com.cuidebem.view.test;

import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.com.cuidebem.controller.UsersFacade;
import br.com.cuidebem.controller.UsuarioFacade;
import br.com.cuidebem.controller.exception.ControllerException;
import br.com.cuidebem.model.Telefone;
import br.com.cuidebem.model.Users;
import br.com.cuidebem.model.Usuario;
import br.com.cuidebem.model.UsuarioPhoto;
import br.com.cuidebem.model.UsuarioTelefone;
import br.com.cuidebem.model.def.TipoTelefone;

@Named("test")
@RequestScoped
public class Test {
	
	@EJB
	private UsuarioFacade usuarioFacade;
	
	@EJB
	private UsersFacade usersFacade;
	
	private Users createUser(){
		return null;
	}

	private Usuario createUsuario(){
		Usuario usuario = new Usuario();
		usuario.setDatanascimento(Calendar.getInstance().getTime());
		usuario.setEmail("alecindrocastilho@gmail.com");
		//usuario.setEnabled(true);
		usuario.setGenero(Boolean.TRUE);
		usuario.setNome("alecindro");
		usuario.setApelido("lele");
		return usuario;
	}
	
	private Telefone createTelefone(){
		Telefone telefone = new Telefone();
		telefone.setDdd("48");
		telefone.setOperadora("TIM");
		telefone.setTipo(TipoTelefone.CELULAR.name());
		telefone.setTelefone("999619985");
		return telefone;
		
	}
	
	private UsuarioPhoto createUsuarioPhoto(Usuario usuario){
		UsuarioPhoto userphoto = new UsuarioPhoto();
		try {
			URL url = getClass().getResource("/"+"dor.png");
			
			URI uri = url.toURI();
			Path path = Paths.get(uri);
			byte[] photo = Files.readAllBytes(path);
			userphoto.setPhoto(photo);
			//userphoto.setUsuario(usuario);
			return userphoto;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void simpleUser(){
	     try {
			usuarioFacade.edit(createUsuario());
		} catch (ControllerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void compositionUser(){
		Usuario usuario = createUsuario();
		UsuarioPhoto usuarioPhoto = createUsuarioPhoto(usuario);
		//usuario.setUsuarioPhoto(usuarioPhoto);
		UsuarioTelefone ut = new UsuarioTelefone();
		ut.setIdusuario(usuario);
		ut.setIdtelefone(createTelefone());
		usuario.getUsuarioTelefoneList().add(ut);
		//usuarioFacade.create(entity);
	}
}
