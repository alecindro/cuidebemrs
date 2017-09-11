package br.com.cuidebem.controller;

import java.util.Calendar;
import java.util.Date;

import javax.ejb.Stateless;

import br.com.cuidebem.controller.exception.ControllerException;
import br.com.cuidebem.model.Roles;
import br.com.cuidebem.model.Users;
import br.com.cuidebem.view.util.UtilSecurity;


@Stateless
public class UsersFacade extends AbstractFacade<Users> {

	public UsersFacade() {
		super(Users.class);
	}

	public void create(Roles role, String email, String password) throws ControllerException{
    	Users user = new Users();
    	Date date = Calendar.getInstance().getTime();
    	user.setDatacadastro(date);
    	user.setBlocked(Boolean.FALSE);
    	user.setEmail(email);
    	user.setEnabled(Boolean.TRUE);
    	user.setPassword(UtilSecurity.gerarSenha(password));
    	user.setActivation(date);
    	user.getRolesList().add(role);
    	create(user);
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
	
	
}
