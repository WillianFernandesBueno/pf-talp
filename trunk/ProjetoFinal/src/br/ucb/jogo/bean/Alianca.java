package br.ucb.jogo.bean;

import java.util.Date;
import java.util.List;

import javax.persistence.OneToMany;

public class Alianca {
	
	private Integer idAlianca;
	private Date dataCriacao;
	private String mensagem;
	
	private List<Personagem> personagens; 
	
	public Integer getIdAlianca() {
		return idAlianca;
	}
	public void setIdAlianca(Integer idAlianca) {
		this.idAlianca = idAlianca;
	}
	public Date getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public List<Personagem> getPersonagens() {
		return personagens;
	}
	
	@OneToMany(targetEntity = Personagem.class)
	public void setPersonagens(List<Personagem> personagens) {
		this.personagens = personagens;
	}
	
	
	
	
}
