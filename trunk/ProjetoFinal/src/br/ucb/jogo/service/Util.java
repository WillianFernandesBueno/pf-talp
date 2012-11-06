package br.ucb.jogo.service;

import javax.faces.context.FacesContext;

public class Util {
	
	public static boolean isUser(String role){
		return FacesContext.getCurrentInstance().getExternalContext().isUserInRole(role);
	}
	
	
	
}
