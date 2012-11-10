package br.ucb.jogo.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="Loggers")
public class Loggers implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date data;
	private String logger;
	private String level;
	private String messagem;
	private Usuario usuario;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	@Column
	public String getLogger() {
		return logger;
	}
	public void setLogger(String logger) {
		this.logger = logger;
	}
	@Column
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	@Column
	public String getMessagem() {
		return messagem;
	}
	public void setMessagem(String messagem) {
		this.messagem = messagem;
	}
	
	@Id
	@ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)    			
	@JoinColumns({  
		@JoinColumn(name="Usuarios_idUsuarios", referencedColumnName = "idUsuarios"),  
		@JoinColumn(name="Usuarios_login", referencedColumnName = "login")  
	})  	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	

}
