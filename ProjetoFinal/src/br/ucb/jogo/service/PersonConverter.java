package br.ucb.jogo.service;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.ucb.jogo.HIB.UsuarioHIB;
import br.ucb.jogo.bean.Personagem;

public class PersonConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
		UsuarioHIB u = new UsuarioHIB();
		if (value != null){
			return u.findTById(Integer.parseInt(value));
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object obj) {
		if(obj != null && obj instanceof Personagem){
			Personagem person = (Personagem) obj;
			return person.getIdPersonagens().toString();
		}
		return null;
	}

}