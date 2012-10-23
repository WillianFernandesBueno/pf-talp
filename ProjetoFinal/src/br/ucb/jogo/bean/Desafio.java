package br.ucb.jogo.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="desafios")
public class Desafio {
	
	
	private Integer idDesafios;
	private Integer idDesafiante;
	private Integer idDesafiado;
	private Float aposta;
	private Personagem personagem;
	
	@Id
	@GeneratedValue
	public Integer getIdDesafios() {
		return idDesafios;
	}
	
	public void setIdDesafios(Integer idDesafios) {
		this.idDesafios = idDesafios;
	}
	@Column
	public Integer getIdDesafiante() {
		return idDesafiante;
	}
	
	public void setIdDesafiante(Integer idDesafiante) {
		this.idDesafiante = idDesafiante;
	}
	@Column
	public Integer getIdDesafiado() {
		return idDesafiado;
	}
	
	public void setIdDesafiado(Integer idDesafiado) {
		this.idDesafiado = idDesafiado;
	}
	@Column
	public Float getAposta() {
		return aposta;
	}
	
	public void setAposta(Float aposta) {
		this.aposta = aposta;
	}
	
	@ManyToOne
	@JoinColumn(name="id_Personagens")
	public Personagem getPersonagem() {
		return personagem;
	}
	public void setPersonagem(Personagem personagem) {
		this.personagem = personagem;
	}
	
	

}
