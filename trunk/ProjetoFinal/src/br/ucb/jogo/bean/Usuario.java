package br.ucb.jogo.bean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="usuarios")
public class Usuario extends Pessoa implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private Integer idUsuarios;
	private String senha;
	private Integer tipo;
	private String login;		
	
	private List<Autorizacao> autorizacao;
	
	@Id
	@GeneratedValue
	public Integer getIdUsuarios() {
		return idUsuarios;
	}
	public void setIdUsuarios(Integer idUsuarios) {
		this.idUsuarios = idUsuarios;
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
	
	@OneToMany(mappedBy="usuario", fetch = FetchType.LAZY, targetEntity = Autorizacao.class)
	public List<Autorizacao> getAutorizacao() {
		return autorizacao;
	}
	public void setAutorizacao(List<Autorizacao> autorizacao) {
		this.autorizacao = autorizacao;
	}
	
}
