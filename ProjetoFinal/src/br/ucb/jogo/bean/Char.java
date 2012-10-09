package br.ucb.jogo.bean;

public class Char {
	private String classe;
	private String detalhes;
	private int forca;
	private int mana;
	private int hp;
	private int agilidade;
	private int defesa;
	
	public Char(String classe, String detalhes, int forca, int mana, int hp,
			int agilidade, int defesa) {
		super();
		this.classe = classe;
		this.detalhes = detalhes;
		this.forca = forca;
		this.mana = mana;
		this.hp = hp;
		this.agilidade = agilidade;
		this.defesa = defesa;
	}

	public String getDetalhes() {
		return detalhes;
	}

	public void setDetalhes(String detalhes) {
		this.detalhes = detalhes;
	}

	public Char(){
	}
	public String getClasse() {
		
		return classe;
	}
	public void setClasse(String classe) {
		this.classe = classe;
	}
	public int getForca() {
		return forca;
	}
	public void setForca(int forca) {
		this.forca = forca;
	}
	public int getMana() {
		return mana;
	}
	public void setMana(int mana) {
		this.mana = mana;
	}
	
	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getAgilidade() {
		return agilidade;
	}
	public void setAgilidade(int agilidade) {
		this.agilidade = agilidade;
	}
	public int getDefesa() {
		return defesa;
	}
	public void setDefesa(int defesa) {
		this.defesa = defesa;
	}
	


}
