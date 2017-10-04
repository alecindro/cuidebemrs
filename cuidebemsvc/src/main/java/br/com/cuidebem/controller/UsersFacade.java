package br.com.cuidebem.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.cuidebem.controller.exception.ControllerException;
import br.com.cuidebem.model.Roles;
import br.com.cuidebem.model.Users;
import br.com.cuidebem.view.util.UtilSecurity;


@Stateless
public class UsersFacade extends AbstractFacade<Users> {
	
	@EJB
	private RolesFacade rolesFacade;

	public UsersFacade() {
		super(Users.class);
	}

	public void create(List<String> roles, String email, String password) throws ControllerException{
		List<Roles> _roles = new ArrayList<Roles>();
		for(String role : roles){
			_roles.add(rolesFacade.find(role));
		}
    	Users user = new Users();
    	Date date = Calendar.getInstance().getTime();
    	user.setDatacadastro(date);
    	user.setBlocked(Boolean.FALSE);
    	user.setEmail(email);
    	user.setEnabled(Boolean.TRUE);
    	user.setPassword(UtilSecurity.gerarSenha(password));
    	user.setActivation(date);
    	user.getRolesList().addAll(_roles);
    	create(user);
 	}
	
	public void edit(List<String> roles,String email,String password) throws ControllerException{
		List<String> _rolesuser = new ArrayList<String>(roles);
		List<Roles> _rolesremove = new ArrayList<Roles>();
		
		Users user = find(email);
		if(password != null && password.trim().length() >0){
			user.setPassword(UtilSecurity.gerarSenha(password));
		}
		for(Roles _role : user.getRolesList()){
			if(!roles.contains(_role.getName())){
				_rolesremove.add(_role);
				_rolesuser.remove(_role.getName());
			}
		}
		user.getRolesList().removeAll(_rolesremove);
		for(String _roleuser : _rolesuser){
			Roles role = rolesFacade.find(_roleuser);
			user.getRolesList().add(role);
		}
		
		edit(user);
	}
	
	public void enabled(String email,boolean enable) throws ControllerException{
		Users user = find(email);
		user.setEnabled(enable);
		edit(user);
	}
	
	
	
	public void desbloquear(String email) throws ControllerException{
		Users user = find(email);
		user.setBlocked(false);
		edit(user);
	}
	
	public void create(Users user,Roles role) throws ControllerException{
		
		Users olduser = find(user.getEmail());
		if (olduser != null) {
			if (!olduser.getRolesList().contains(role)) {
				olduser.getRolesList().add(role);
			}
			user = edit(olduser);
		} else {
		Date date = Calendar.getInstance().getTime();
    	user.setDatacadastro(date);
    	user.setBlocked(Boolean.FALSE);
    	user.setEnabled(Boolean.TRUE);
    	user.setPassword(UtilSecurity.gerarSenha(user.getPassword()));
    	user.setActivation(date);
    	user.setAlterLogin(date);
    	user.getRolesList().add(role);
    	create(user);
		}
 	}
	
	public void updatePassword(Users user,String newPassword,String repeatPassword) throws ControllerException{
		if(newPassword == null || repeatPassword == null){
			throw new ControllerException("password_empty");
		}
		if(!newPassword.equals(repeatPassword)){
			throw new ControllerException("password_match");
		}
		user.setPassword(UtilSecurity.gerarSenha(newPassword));
		edit(user);
	}
	
	 public void confirmaAceite(String email) throws ControllerException{
	    	Users user = find(email);
	    	if(user == null){
	    		throw new ControllerException("Usuário não encontrado: "+email);
	    	}
	    	if (!user.getBlocked()){
	    		throw new ControllerException("usuário já foi desbloqueado");
	    	}
	    	user.setBlocked(false);
	    	user.setActivation(new Date());
	    	edit(user);
	    }
	
}
