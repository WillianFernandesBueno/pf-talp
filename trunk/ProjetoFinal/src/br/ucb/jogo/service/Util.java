package br.ucb.jogo.service;

import javax.faces.context.FacesContext;

public class Util {
	
	public static boolean isUser(String role){
		return FacesContext.getCurrentInstance().getExternalContext().isUserInRole(role);
	}
	
	public static String getUserSession(){
		return FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
	}
	
	public static String getMsgLogger(String msg){
		
		return getUserSession() == null ? "Mensagem: "+msg : "Mensagem: "+msg+" | Usuario: "+ getUserSession();
	}
	
}
