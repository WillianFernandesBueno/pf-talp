package br.ucb.jogo.bean;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="Item")
public class Item {
	
	private Integer idItem;
	private Float preco;
	private Integer levelNecessario;
	private List<Personagem> personagens;
	
	@Id
	@GeneratedValue
	public Integer getIdItem() {
		return idItem;
	}
	public void setIdItem(Integer idItem) {
		this.idItem = idItem;
	}
	@Column
	public Float getPreco() {
		return preco;
	}
	public void setPreco(Float preco) {
		this.preco = preco;
	}
	@Column
	public Integer getLevelNecessario() {
		return levelNecessario;
	}
	public void setLevelNecessario(Integer levelNecessario) {
		this.levelNecessario = levelNecessario;
	}
	
	@ManyToMany
	@JoinTable(name="Personagens_has_Item", joinColumns = {@JoinColumn(name = "idItem")}, inverseJoinColumns = {@JoinColumn(name = "idPersonagens" )} )
	public List<Personagem> getPersonagens() {
		return personagens;
	}
	public void setPersonagens(List<Personagem> personagens) {
		this.personagens = personagens;
	}
	
	
	
	
}
