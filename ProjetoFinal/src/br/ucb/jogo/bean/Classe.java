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
@Table(name="classe")
public class Classe  implements Serializable {
	private static final long serialVersionUID = 1L;

	private String nome;
	private Integer forca, agilidade, defesa, mana;
	private Integer idClasse;
	
	@SuppressWarnings("rawtypes")
	ListDataModel model = null;
	
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
	
	@Id
	@GeneratedValue
	public Integer getIdClasse() {
		return idClasse;
	}

	public void setIdClasse(Integer id) {
		this.idClasse = id;
	}
	
	@Column(name="descricao")
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Column(name="forca")
	public Integer getForca() {
		return forca;
	}

	public void setForca(Integer forca) {
		this.forca = forca;
	}
	@Column(name="agilidade")
	public Integer getAgilidade() {
		return agilidade;
	}

	public void setAgilidade(Integer agilidade) {
		this.agilidade = agilidade;
	}
	@Column(name="defesa")
	public Integer getDefesa() {
		return defesa;
	}

	public void setDefesa(Integer defesa) {
		this.defesa = defesa;
	}
	@Column(name="mana")
	public Integer getMana() {
		return mana;
	}

	public void setMana(Integer mana) {
		this.mana = mana;
	}  
	
	
	
}
