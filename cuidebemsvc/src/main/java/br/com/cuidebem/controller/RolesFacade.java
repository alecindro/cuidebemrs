package br.com.cuidebem.controller;

import javax.ejb.Stateless;

import br.com.cuidebem.model.Roles;

@Stateless
public class RolesFacade extends AbstractFacade<Roles>{

	public RolesFacade(){
		super(Roles.class);
	}
	
}
