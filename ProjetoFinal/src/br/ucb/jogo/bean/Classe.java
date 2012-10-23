package br.ucb.jogo.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.model.ListDataModel;

public class Classe implements Serializable{
	private static final long serialVersionUID = 1L;

	private String nome;

	@SuppressWarnings("rawtypes")
	ListDataModel model = null;
	
	public Classe(String nome) {
		setNome(nome);
	}

	public Classe() {
		// TODO Auto-generated constructor stub
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	public Classe getRowData(String rowKey) {  
		@SuppressWarnings("unchecked")
		List<Classe> classes = (List<Classe>) model.getWrappedData();  

		for(Classe classe : classes) {  
			if(classe.getNome().equals(rowKey))  
				return classe;
		}  

		return null;  
	}  

	public String getRowKey(Classe classe) {  
		return classe.getNome();  
	}  
	
}
