package br.ucb.jogo.bean;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="treino")
public class Treino {
	
	private Integer idTreino;
	private String nome;
	private Float cash;
	private Integer pontos;
	private Integer tempoNecessario;
	private List<Personagem> personagens;
	
	@Id
	@GeneratedValue
	public Integer getIdTreino() {
		return idTreino;
	}
	
	public void setIdTreino(Integer idTreino) {
		this.idTreino = idTreino;
	}
	@Column
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	@Column
	public Float getCash() {
		return cash;
	}

	public void setCash(Float cash) {
		this.cash = cash;
	}
	
	@Column
	public Integer getPontos() {
		return pontos;
	}
	
	public void setPontos(Integer pontos) {
		this.pontos = pontos;
	}
	@Column
	public Integer getTempoNecessario() {
		return tempoNecessario;
	}

	public void setTempoNecessario(Integer tempoNecessario) {
		this.tempoNecessario = tempoNecessario;
	}

	@ManyToMany
	@JoinTable(name="Personagens_has_treino", 
	joinColumns = {@JoinColumn(name="Treino_idTreino",referencedColumnName="idTreino")}, 
	inverseJoinColumns = {@JoinColumn (name="Personagens_idPersonagens", referencedColumnName="idPersonagens")})
	public List<Personagem> getPersonagens() {
		return personagens;
	}
	public void setPersonagens(List<Personagem> personagens) {
		this.personagens = personagens;
	}
	
	
	
//	@Temporal(TemporalType.DATE)
//	public Date getDataInicial() {
//		return dataInicial;
//	}
//	
//	public void setDataInicial(Date dataInicial) {
//		this.dataInicial = dataInicial;
//	}
//	
//	@Temporal(TemporalType.DATE)
//	public Date getDataSaida() {
//		return dataSaida;
//	}
//
//	public void setDataSaida(Date dataSaida) {
//		this.dataSaida = dataSaida;
//	}
	
}
