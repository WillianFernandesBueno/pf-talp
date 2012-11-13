package br.ucb.jogo.bean;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="resultado")
public class Resultado {
	
	
	private Integer idResultado;
	private Integer idGanhador;
	private Integer idPerdedor;
	private Date dataDuelo;
	private Integer pontosGanhos;
	private Desafio desafio;
	
	@Id
	@GeneratedValue
	public Integer getIdResultado() {
		return idResultado;
	}
	
	public void setIdResultado(Integer idResultado) {
		this.idResultado = idResultado;
	}
	@Column
	public Integer getIdGanhador() {
		return idGanhador;
	}

	public void setIdGanhador(Integer idGanhador) {
		this.idGanhador = idGanhador;
	}
	@Column
	public Integer getIdPerdedor() {
		return idPerdedor;
	}

	public void setIdPerdedor(Integer idPerdedor) {
		this.idPerdedor = idPerdedor;
	}
	@Column
	public Integer getPontosGanhos() {
		return pontosGanhos;
	}

	public void setPontosGanhos(Integer pontosGanhos) {
		this.pontosGanhos = pontosGanhos;
	}
	
	@Column
	@Temporal(TemporalType.DATE)
	public Date getDataDuelo() {
		return dataDuelo;
	}

	public void setDataDuelo(Date dataDuelo) {
		this.dataDuelo = dataDuelo;
	}

	@OneToOne
	@JoinColumn(name="Desafios_idDesafios", referencedColumnName="idDesafios")
	public Desafio getDesafio() {
		return desafio;
	}

	public void setDesafio(Desafio desafio) {
		this.desafio = desafio;
	}

}
