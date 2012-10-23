package br.ucb.jogo.bean;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="Personagens")
public class Personagem {
	
	private Integer idPersonagens;
	private String nick;
	private Integer level;
	private Integer mana;
	private Integer forca;
	private Integer agilidade;
	private Integer defesa;
	private Double cash;
	private Usuario usuario;
	//private Classe classe;
	private Item item;
	private Alianca alianca;
	private List<Treino> trienos;
	private List<Desafio> desafios;
	
	@OneToMany(targetEntity = Desafio.class)
	public List<Desafio> getDesafios() {
		return desafios;
	}
	public void setDesafios(List<Desafio> desafios) {
		this.desafios = desafios;
	}
	public Integer getIdPersonagens() {
		return idPersonagens;
	}
	public void setIdPersonagens(Integer idPersonagens) {
		this.idPersonagens = idPersonagens;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Integer getMana() {
		return mana;
	}
	
	@JoinTable(name="Personagens_has_treino", joinColumns = {@JoinColumn(name="idPersonagens")}, inverseJoinColumns = {@JoinColumn (name="idTreino")})
	public List<Treino> getTrienos() {
		return trienos;
	}
	
	public void setTrienos(List<Treino> trienos) {
		this.trienos = trienos;
	}
	public void setMana(Integer mana) {
		this.mana = mana;
	}
	public Integer getForca() {
		return forca;
	}
	public void setForca(Integer forca) {
		this.forca = forca;
	}
	public Integer getAgilidade() {
		return agilidade;
	}
	public void setAgilidade(Integer agilidade) {
		this.agilidade = agilidade;
	}
	public Integer getDefesa() {
		return defesa;
	}
	public void setDefesa(Integer defesa) {
		this.defesa = defesa;
	}
	public Double getCash() {
		return cash;
	}
	public void setCash(Double cash) {
		this.cash = cash;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Item getItem() {
		return item;
	}
	public void setLoja(Item item) {
		this.item = item;
	}
	
	@ManyToOne
	@JoinColumn(name="Alianca_idAlianca")
	public Alianca getAlianca() {
		return alianca;
	}
	public void setAlianca(Alianca alianca) {
		this.alianca = alianca;
	}
	
}
