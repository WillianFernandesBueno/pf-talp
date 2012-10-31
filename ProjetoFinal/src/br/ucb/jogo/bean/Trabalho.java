package br.ucb.jogo.bean;

public class Trabalho {
	private String titulo;  
	private String salarioHora;
	private String horas;
	private String exp;

	
	public Trabalho(String titulo, String salarioHora, String horas, String exp) {
		super();
		this.titulo = titulo;
		this.salarioHora = salarioHora;
		this.horas = horas;
		this.exp = exp;
	}


	public Trabalho() {
		super();
	}


	public String getExp() {
		return exp;
	}


	public void setExp(String exp) {
		this.exp = exp;
	}


	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getSalarioHora() {
		return salarioHora;
	}
	public void setSalarioHora(String salarioHora) {
		this.salarioHora = salarioHora;
	}
	public String getHoras() {
		return horas;
	}
	public void setHoras(String horas) {
		this.horas = horas;
	}


}
