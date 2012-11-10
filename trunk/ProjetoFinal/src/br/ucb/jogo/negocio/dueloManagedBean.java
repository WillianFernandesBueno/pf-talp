package br.ucb.jogo.negocio;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.ucb.jogo.HIB.DesafioHIB;
import br.ucb.jogo.HIB.PersonagemHIB;
import br.ucb.jogo.HIB.ResultadoHIB;
import br.ucb.jogo.HIB.UsuarioHIB;
import br.ucb.jogo.bean.Desafio;
import br.ucb.jogo.bean.Personagem;
import br.ucb.jogo.bean.Resultado;
import br.ucb.jogo.bean.Usuario;
import br.ucb.jogo.service.Util;


@ManagedBean (name = "dueloManagedBean")
@SessionScoped
public class dueloManagedBean {
	
	private Personagem personagem;
	private Desafio desafio;

	public dueloManagedBean() {
		this.personagem = new Personagem();
	}
	
	public String saveDuelo() {
		UsuarioHIB u = new UsuarioHIB();
		PersonagemHIB p = new PersonagemHIB();
		
		Usuario userLogado = u.findTByLogin(Util.getUserSession());
		
		Personagem desafiante = p.findTByIdUser(userLogado.getIdUsuarios());
		Personagem desafiado  = this.personagem;
		
		desafio.setIdDesafiado(desafiado.getIdPersonagens());
		desafio.setIdDesafiante(desafiante.getIdPersonagens());
		desafio.setAposta(Float.parseFloat("100"));
		DesafioHIB d = new DesafioHIB();
		d.save(desafio);
		
		obtemResultado(desafiante, desafiado);
		
		return "IndexUsuario";
	}
	
	
	private void obtemResultado(Personagem desafiante, Personagem desafiado) {
		int totalDesafiante, totalDesafiado;
		totalDesafiante = desafiante.getForca() + desafiante.getMana();
		totalDesafiado = desafiado.getAgilidade() + desafiado.getDefesa();
		
		Resultado resultado = new Resultado();
		
		if(totalDesafiante > totalDesafiado){
			resultado.setIdGanhador(desafiante.getIdPersonagens());
			resultado.setIdPerdedor(desafiado.getIdPersonagens());
		}else{
			resultado.setIdGanhador(desafiado.getIdPersonagens());
			resultado.setIdPerdedor(desafiante.getIdPersonagens());
		}
		resultado.setDesafio(desafio);
		
		resultado.setPontosGanhos(100);
		
		ResultadoHIB r = new ResultadoHIB();
		r.save(resultado);
		
	}

	public Personagem getPersonagem() {
		return personagem;
	}

	public void setPersonagem(Personagem personagem) {
		this.personagem = personagem;
	}
	
	public Desafio getDesafio() {
		return desafio;
	}

	public void setDesafio(Desafio desafio) {
		this.desafio = desafio;
	}
	
}
