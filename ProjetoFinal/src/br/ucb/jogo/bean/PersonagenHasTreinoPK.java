package br.ucb.jogo.bean;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Embeddable
public class PersonagenHasTreinoPK implements Serializable {

	private static final long serialVersionUID = 1L;

	private Personagem personagem;
	private Treino treino;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	public Personagem getPersonagem() {
		return personagem;
	}

	public void setPersonagem(Personagem personagem) {
		this.personagem = personagem;
	}
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	public Treino getTreino() {
		return treino;
	}

	public void setTreino(Treino treino) {
		this.treino = treino;
	}


}
