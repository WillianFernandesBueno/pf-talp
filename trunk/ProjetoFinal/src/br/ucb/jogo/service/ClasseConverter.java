package br.ucb.jogo.service;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.ucb.jogo.HIB.ClasseHIB;
import br.ucb.jogo.bean.Classe;

public class ClasseConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
		ClasseHIB u = new ClasseHIB();
		if (value != null){
			return u.findTById(Integer.parseInt(value));
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object obj) {
		if(obj != null && obj instanceof Classe){
			Classe classe = (Classe) obj;
			return classe.getIdClasse().toString();
		}
		return null;
	}

}