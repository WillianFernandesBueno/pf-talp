package br.ucb.jogo.service;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.ucb.jogo.HIB.TreinoHIB;
import br.ucb.jogo.bean.Treino;


public class TreinoConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
		TreinoHIB u = new TreinoHIB();
		if (value != null){
			return u.findTById(Integer.parseInt(value));
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object obj) {
		if(obj != null && obj instanceof Treino){
			Treino treino = (Treino) obj;
			return treino.getIdTreino().toString();
		}
		return null;
	}

}