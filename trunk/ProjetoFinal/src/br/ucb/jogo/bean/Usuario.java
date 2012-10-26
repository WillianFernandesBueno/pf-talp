package br.ucb.jogo.bean;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="usuarios")
public class Usuario extends Pessoa{

	private Integer id;
	private String senha;
	private Integer tipo;
	private String login;		
	
	private List<Autorizacao> autorizacao;
	
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
	

	@OneToMany(targetEntity = Autorizacao.class)
	public List<Autorizacao> getAutorizacao() {
		return autorizacao;
	}
	public void setAutorizacao(List<Autorizacao> autorizacao) {
		this.autorizacao = autorizacao;
	}
	
	
	
}
