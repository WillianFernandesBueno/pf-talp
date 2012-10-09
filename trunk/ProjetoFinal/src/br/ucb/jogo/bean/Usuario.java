package br.ucb.jogo.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="usuarios")
public class Usuario extends Pessoa{

	private Integer id;
	private String senha;
	private Integer tipo;
	private String login;		
	
	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	@Column
	public Integer getTipo() {
		return tipo;
	}
	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}
	@Column
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	@Column
	public String getBairro() {
		return super.getBairro();
	}
	@Column
	public String getRua() {
		return super.getRua();
	}
	@Column
	public String getCidade() {
		return super.getCidade();
	}
	@Column
	public String getEstado() {
		return super.getEstado();
	}
	@Column
	public String getNome() {
		return super.getNome();
	}
	@Column
	public String getEmail() {
		return super.getEmail();
	}
	@Override
	public String toString() {
		return "Usuario [getId()=" + getId() + ", getSenha()=" + getSenha()
				+ ", getTipo()=" + getTipo() + ", getLogin()=" + getLogin()
				+ ", getBairro()=" + getBairro() + ", getRua()=" + getRua()
				+ ", getCidade()=" + getCidade() + ", getEstado()="
				+ getEstado() + ", getNome()=" + getNome() + ", getEmail()="
				+ getEmail() + "]";
	}
	
	
	
}
