package br.ucb.jogo.bean;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="treino")
public class Treino {
	
	private Integer idTreino;
	private Integer pontos;
	private Date dataInicial;
	private Date dataSaida;
	private List<Personagem> personagens;
	private Date tempoNecessario;

	@Id
	@GeneratedValue
	public Integer getIdTreino() {
		return idTreino;
	}
	
	public void setIdTreino(Integer idTreino) {
		this.idTreino = idTreino;
	}
	@Column
	public Integer getPontos() {
		return pontos;
	}
	
	public void setPontos(Integer pontos) {
		this.pontos = pontos;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getDataInicial() {
		return dataInicial;
	}
	
	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}
	
	@ManyToMany
	@JoinTable(name="Personagens_has_treino", joinColumns = {@JoinColumn(name="idTreino")}, inverseJoinColumns = {@JoinColumn (name="idPersonagens")})
	public List<Personagem> getPersonagem() {
		return personagens;
	}

	public void setPersonagem(List<Personagem> personagens) {
		this.personagens = personagens;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getTempoNecessario() {
		return tempoNecessario;
	}

	public void setTempoNecessario(Date tempoNecessario) {
		this.tempoNecessario = tempoNecessario;
	}

}
