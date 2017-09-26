package br.com.cuidebem.view.util;

import java.io.IOException;

import org.apache.shiro.SecurityUtils;

import br.com.security.quali.password.UtilPassword;

public class UtilSecurity {

	
	public static String getUser(){
		if(SecurityUtils.getSubject().getPrincipal()== null){
			return null;
		}
		return SecurityUtils.getSubject().getPrincipal().toString();
	}
	
	public static void logout(){
		SecurityUtils.getSecurityManager().logout(SecurityUtils.getSubject());
	}
	
	@SuppressWarnings("restriction")
	public static String encrypt(String encrypt){
		return new sun.misc.BASE64Encoder().encode(encrypt.getBytes());
	}
	
	@SuppressWarnings("restriction")
	public static String decrypt(String encrypt) throws IOException{
		return new String(new sun.misc.BASE64Decoder().decodeBuffer(encrypt));
	}
	
	public static String gerarSenha(String senha){
		return UtilPassword.genPassword(senha);
	}
	
	public static void main(String[] args){
		System.out.println(encrypt("WXZIiOOALleLrb86THdMBSWj45E="));
	}
	
	public static boolean containsPermission(String permission){
		return SecurityUtils.getSubject().isPermitted(permission);
	}
	public static boolean hasRole(String role){
		return SecurityUtils.getSubject().hasRole(role);
	}
}
