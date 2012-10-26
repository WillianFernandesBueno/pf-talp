package br.ucb.jogo.bean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="Autorizacao")
public class Autorizacao implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Usuario usuario;
	private String papel;
	

	@Id
	@ManyToOne
	@JoinColumn(name="idUsuarios")
	@Fetch(FetchMode.JOIN)
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	@Id
	@Column
	public String getPapel() {
		return papel;
	}
	public void setPapel(String papel) {
		this.papel = papel;
	}
	
	
}
