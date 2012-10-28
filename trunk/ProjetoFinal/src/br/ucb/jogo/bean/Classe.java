package br.ucb.jogo.bean;

import java.io.Serializable;
import java.util.List;
import javax.faces.model.ListDataModel;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "classe")
public class Classe implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer idClasse;
	private String nome;

	
	@SuppressWarnings("rawtypes")
	ListDataModel model = null;
	
	public Classe(String nome) {
		setNome(nome);
	}

	public Classe() {
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue
	public Integer getIdClasse() {
		return idClasse;
	}

	public void setIdClasse(Integer idClasse) {
		this.idClasse = idClasse;
	}
	
	@Column
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
