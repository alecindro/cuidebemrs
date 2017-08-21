/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cuidebem.controller;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.cuidebem.controller.def.TipoUsuario;
import br.com.cuidebem.controller.exception.ControllerException;
import br.com.cuidebem.model.Residencia;
import br.com.cuidebem.model.Usuario;
import br.com.cuidebem.model.UsuarioResidencia;
import br.com.cuidebem.translate.Bundle;

/**
 *
 * @author aleci
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

	@EJB
	private UsersFacade usersFacade;
	@EJB
	private ResidenciaFacade residenciaFacade;
	@EJB
	private UsuarioResidenciaFacade usuarioResidenciaFacade;

	public UsuarioFacade() {
		super(Usuario.class);
	}

	public Usuario find(String email) throws ControllerException {
		QueryParameter parameters = QueryParameter.init("email", email);
		List<Usuario> usuarios = findWithNamedQuery("Usuario.findByEmail", parameters, 1);
		if (usuarios.isEmpty()) {
			return null;
		}
		return usuarios.get(0);
	}

	public Usuario create(Residencia residencia, Usuario usuario) throws ControllerException {
		if (usuario.getNome() != null) {
			usuario.setNome(usuario.getNome().trim());
			if (usuario.getApelido() == null || usuario.getApelido().trim().equals("")) {
				String[] nomes = usuario.getNome().split("\\s");
				usuario.setApelido(nomes[0]);
			}
		}
		usuario = edit(usuario);
		UsuarioResidencia usuarioResidencia = new UsuarioResidencia();
		usuarioResidencia.setIdresidencia(residencia);
		usuarioResidencia.setIdusuario(usuario);
		usuarioResidenciaFacade.create(usuarioResidencia);
		usuario.getUsuarioResidenciaList().add(usuarioResidencia);
		return usuario;
	}
	
	public Usuario editUsuario(Residencia residencia, Usuario usuario) throws ControllerException{
		if (usuario.getNome() != null) {
			usuario.setNome(usuario.getNome().trim());
			if (usuario.getApelido() == null || usuario.getApelido().trim().equals("")) {
				String[] nomes = usuario.getNome().split("\\s");
				usuario.setApelido(nomes[0]);
			}
		}
		return edit(usuario);
	}

	public Usuario create(Integer idresidencia, Usuario usuario) throws ControllerException {
		if (idresidencia == null) {
			throw new ControllerException(Bundle.getValue("error.save"));
		}
		Residencia residencia = residenciaFacade.find(idresidencia);
		return create(residencia, usuario);
	}

	public List<Usuario> findAllDisabled(Integer idresidencia) throws ControllerException {
		return findByNativeQuery("Usuario.findByResidenciaEnabled", idresidencia, false);
	}

	public List<Usuario> findAllEnabled(Integer idresidencia) throws ControllerException {
		return findByNativeQuery("Usuario.findByResidenciaEnabled", idresidencia, true);
	}

	public List<Usuario> findCuidadoresEnabled(Integer idresidencia) throws ControllerException {
		return findByNativeQuery("Usuario.findByTipoUsuarioEnabled", idresidencia, true,
				TipoUsuario.CUIDADOR.getValue());
	}

	public void delete(Usuario usuario) throws ControllerException {
		usuario.setEnabled(false);
		edit(usuario);
	}

	public void reativar(Usuario usuario) throws ControllerException {
		usuario.setEnabled(true);
		edit(usuario);
	}

}
