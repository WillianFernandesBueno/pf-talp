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
@Table(name="item")
public class Item {
	
	private Integer idItem;
	private String nome;
	private Float preco;
	private Integer levelNecessario;
	private Integer forca, agilidade, defesa, mana;
	private List<Personagem> personagens;
	
	@Id
	@GeneratedValue
	@Column(name="idItem")
	public Integer getIdItem() {
		return idItem;
	}
	
	public void setIdItem(Integer idItem) {
		this.idItem = idItem;
	}
	@Column(name="item")
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
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
	@JoinTable(name="Personagens_has_Item", 
	joinColumns = {@JoinColumn(name = "Item_idItem", referencedColumnName="idItem")}, 
	inverseJoinColumns = {@JoinColumn(name = "Personagens_idPersonagens", referencedColumnName="idPersonagens")} )
	public List<Personagem> getPersonagens() {
		return personagens;
	}
	public void setPersonagens(List<Personagem> personagens) {
		this.personagens = personagens;
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
