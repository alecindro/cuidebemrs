package br.com.cuidebem.controller;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.cuidebem.controller.exception.ControllerException;
import br.com.cuidebem.model.Roles;
import br.com.cuidebem.model.RolesPermissions;
import br.com.cuidebem.model.RolesPermissionsPK;

@Stateless
public class RolesPermissionFacade extends AbstractFacade<RolesPermissions>{
	
	@EJB
	private RolesFacade rolesFacade;

	public RolesPermissionFacade(){
		super(RolesPermissions.class);
	}
	public RolesPermissions permissionToResidence(String rolename, String permission) throws ControllerException {
		try {
			RolesPermissionsPK pk = new RolesPermissionsPK(rolename, permission);
			RolesPermissions rolesPermissions = new RolesPermissions(pk);
			create(rolesPermissions);
			return rolesPermissions;
		} catch (Exception e) {
			throw new ControllerException(e.getMessage(), e);
		}
	}
	
	public void saveAllPermissions(Integer idresidencia) throws ControllerException{
		List<Roles> roles = rolesFacade.findAll();
		for(Roles role : roles){
			permissionToResidence(role.getName(), String.valueOf(idresidencia));
		}
		
	}
	
	public void removeAllPermissions(Integer idresidencia, String email){
		
	}
	
	
}
