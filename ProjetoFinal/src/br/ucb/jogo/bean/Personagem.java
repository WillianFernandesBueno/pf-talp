package br.ucb.jogo.bean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;


@Entity
@Table(name="Personagens")
public class Personagem implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer idPersonagens;
	private String nick;
	private Integer level;
	private Integer mana;
	private Integer forca;
	private Integer agilidade;
	private Integer defesa;
	private Double cash;
	private Usuario usuario;
	private Classe classe;
	private List<Item> itens;
	private Alianca alianca;
	private List<Treino> trienos;
	//private List<Desafio> desafios;
	private Boolean ativo;
	private Integer experiencia;
	private List<PersonagenHasTreino> personagenHasTreinos;


	@Id
	@GeneratedValue
	public Integer getIdPersonagens() {
		return idPersonagens;
	}
	public void setIdPersonagens(Integer idPersonagens) {
		this.idPersonagens = idPersonagens;
	}
	@Column
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}

	@Column
	public Integer getExperiencia() {
		return experiencia;
	}
	public void setExperiencia(Integer experiencia) {
		this.experiencia = experiencia;
	}
	@Column
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="Classe_idClasse", referencedColumnName="idClasse")
	public Classe getClasse() {
		return classe;
	}
	public void setClasse(Classe classe) {
		this.classe = classe;
	}

	@Column
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}

	@Column
	public Integer getMana() {
		return mana;
	}

	public void setMana(Integer mana) {
		this.mana = mana;
	}

	@ManyToMany
	@JoinTable(name="Personagens_has_treino", joinColumns = {@JoinColumn(name="idPersonagens")}, inverseJoinColumns = {@JoinColumn (name="idTreino")})
	public List<Treino> getTrienos() {
		return trienos;
	}

	public void setTrienos(List<Treino> trienos) {
		this.trienos = trienos;
	}

	@Column
	public Integer getForca() {
		return forca;
	}
	public void setForca(Integer forca) {
		this.forca = forca;
	}
	@Column
	public Integer getAgilidade() {
		return agilidade;
	}
	public void setAgilidade(Integer agilidade) {
		this.agilidade = agilidade;
	}
	@Column
	public Integer getDefesa() {
		return defesa;
	}
	public void setDefesa(Integer defesa) {
		this.defesa = defesa;
	}
	@Column
	public Double getCash() {
		return cash;
	}
	public void setCash(Double cash) {
		this.cash = cash;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="Usuarios_idUsuarios", referencedColumnName="idUsuarios")
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name="Personagens_has_Item", 
	joinColumns = {@JoinColumn(name = "Personagens_idPersonagens", referencedColumnName="idPersonagens")}, 
	inverseJoinColumns = {@JoinColumn(name = "Item_idItem", referencedColumnName="idItem")} )
	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}

	//	@OneToMany(targetEntity = Desafio.class)
	//	public List<Desafio> getDesafios() {
	//		return desafios;
	//	}
	//	public void setDesafios(List<Desafio> desafios) {
	//		this.desafios = desafios;
	//	}

	@ManyToOne
	@JoinColumn(name="Alianca_idAlianca")
	public Alianca getAlianca() {
		return alianca;
	}
	public void setAlianca(Alianca alianca) {
		this.alianca = alianca;
	}
	@SuppressWarnings("deprecation")
	@OneToMany(fetch = FetchType.LAZY,	 mappedBy = "pk.personagem", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@Cascade( { org.hibernate.annotations.CascadeType.SAVE_UPDATE,	 org.hibernate.annotations.CascadeType.DELETE_ORPHAN })
	public List<PersonagenHasTreino> getPersonagenHasTreinos() {
		return personagenHasTreinos;
	}
	public void setPersonagenHasTreinos(
			List<PersonagenHasTreino> personagenHasTreinos) {
		this.personagenHasTreinos = personagenHasTreinos;
	}



}
