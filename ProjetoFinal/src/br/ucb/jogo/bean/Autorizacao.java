package br.ucb.jogo.bean;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Autorizacao")
public class Autorizacao implements Serializable{

	private static final long serialVersionUID = 1L;
	private Usuario usuario;
	private String papel;

	@Id
	@ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)    

	@JoinColumns({  
		@JoinColumn(name="idUsuarios", referencedColumnName = "idUsuarios"),  
		@JoinColumn(name="login", referencedColumnName = "login")  
	})  

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

	@Override
	public String toString() {
		return "Autorizacao [usuario=" + usuario.getIdUsuarios() + ", papel=" + papel + "]";
	}
	
	


}


