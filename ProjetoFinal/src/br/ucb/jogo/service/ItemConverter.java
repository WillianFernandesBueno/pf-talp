package br.ucb.jogo.service;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.ucb.jogo.HIB.ItemHIB;
import br.ucb.jogo.bean.Item;


public class ItemConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
		ItemHIB u = new ItemHIB();
		if (value != null){
			return u.findTById(Integer.parseInt(value));
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object obj) {
		if(obj != null && obj instanceof Item){
			Item item = (Item) obj;
			return item.getIdItem().toString();
		}
		return null;
	}

}