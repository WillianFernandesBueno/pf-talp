package br.ucb.jogo.bean;

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
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Integer getMana() {
		return mana;
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
	public Alianca getAlianca() {
		return alianca;
	}
	public void setAlianca(Alianca alianca) {
		this.alianca = alianca;
	}
	
}
