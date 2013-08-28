package br.ucb.jogo.bean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="usuarios")
public class Usuario extends Pessoa implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private int idUsuarios;
	private String senha;
	private Integer tipo;
	private String login;		
	private List<Autorizacao> autorizacao;
	private List<Loggers> logs;
	private Personagem personagem;
	
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
	
	@OneToMany(mappedBy="usuario", fetch = FetchType.LAZY, targetEntity = Autorizacao.class, cascade = CascadeType.ALL)
	public List<Autorizacao> getAutorizacao() {
		return autorizacao;
	}
	public void setAutorizacao(List<Autorizacao> autorizacao) {
		this.autorizacao = autorizacao;
	}
	
	@OneToMany(mappedBy="usuario", fetch = FetchType.LAZY, targetEntity = Loggers.class, cascade = CascadeType.ALL)
	public List<Loggers> getLogs() {
		return logs;
	}
	public void setLogs(List<Loggers> logs) {
		this.logs = logs;
	}
	
	@OneToOne(mappedBy="usuario", fetch = FetchType.LAZY, targetEntity = Personagem.class, cascade = CascadeType.ALL)
	public Personagem getPersonagem() {
		return personagem;
	}
	public void setPersonagem(Personagem personagem) {
		this.personagem = personagem;
	}
}
