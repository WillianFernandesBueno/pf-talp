package br.ucb.jogo.service;

import javax.faces.context.FacesContext;



import br.ucb.jogo.bean.Item;
import br.ucb.jogo.bean.Personagem;

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
	
	public static boolean verificaItemDispo(Personagem personagem, Item item){
		
		return personagem.getLevel() >= item.getLevelNecessario() && personagem.getCash() >= item.getPreco() && personagem.getItens().equals(item);
		
	}
	
}
