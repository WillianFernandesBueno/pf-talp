package br.ucb.jogo.bean;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="Alianca")
public class Alianca {
	
	private Integer idAlianca;
	private String nome;
	private Date dataCriacao;
	private String mensagem;
	
	private List<Personagem> personagens; 
	
	@Id
	@GeneratedValue
	public Integer getIdAlianca() {
		return idAlianca;
	}
	
	public void setIdAlianca(Integer idAlianca) {
		this.idAlianca = idAlianca;
	}
	@Column
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	public Date getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	@Column
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	@OneToMany(mappedBy = "alianca", targetEntity = Personagem.class)
	public List<Personagem> getPersonagens() {
		return personagens;
	}
	
	public void setPersonagens(List<Personagem> personagens) {
		this.personagens = personagens;
	}
	
	
	
	
}
