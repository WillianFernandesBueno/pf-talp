package br.ucb.jogo.bean;

import java.util.List;

public class Classe {
	private String nome;

	
	
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
		List<Classe> classes = (List<Classe>) getWrappedData();  

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
