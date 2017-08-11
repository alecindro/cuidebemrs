/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cuidebem.controller;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.cuidebem.controller.def.RolesEnum;
import br.com.cuidebem.controller.exception.ControllerException;
import br.com.cuidebem.model.Residencia;
import br.com.cuidebem.model.Roles;
import br.com.cuidebem.model.Users;
import br.com.cuidebem.model.Usuario;

/**
 *
 * @author aleci
 */
@Stateless
public class ResidenciaFacade extends AbstractFacade<Residencia> {

	@EJB
	private UsersFacade usersFacade;
	@EJB
	private UsuarioFacade usuarioFacade;
	@EJB
	private RolesFacade rolesFacade;

	@EJB
	private RolesPermissionFacade rolesPermissionFacade;

	public ResidenciaFacade() {
		super(Residencia.class);
	}

	public Residencia create(Residencia residencia, Users user) throws ControllerException {
		try {
			Usuario usuario = usuarioFacade.find(user.getEmail());
			residencia = edit(residencia);
			rolesPermissionFacade.saveAllPermissions(residencia.getIdresidencia());
			Roles roles = rolesFacade.find(RolesEnum.ADMINRESDIDENCIA.getValue());
			usersFacade.create(user, roles);
			if (usuario == null) {
				usuario = new Usuario();
				usuario.setEmail(user.getEmail());
				usuario.setEnabled(true);
			}
			usuario = usuarioFacade.create(residencia, usuario);
			return residencia;
		} catch (Exception e) {
			throw new ControllerException(e.getMessage(), e);
		}
	}

}
