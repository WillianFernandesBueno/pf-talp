package br.ucb.jogo.bean;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Item {
	
	private Integer idItem;
	private Float preco;
	private Integer levelNecessario;
	
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
	
}
