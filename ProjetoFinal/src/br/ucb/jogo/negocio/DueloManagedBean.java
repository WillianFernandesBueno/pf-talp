package br.ucb.jogo.negocio;

import java.util.Date;

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
public class DueloManagedBean {
	
	private Personagem personagem;
	private Desafio desafio;
	private Resultado resultado;

	public DueloManagedBean() {
		this.personagem = new Personagem();
		this.resultado = new Resultado();
		this.desafio = new Desafio();
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
		
		Double cash = personagem.getCash();
		Integer experiencia = personagem.getExperiencia();
		cash += desafio.getAposta();
		experiencia += resultado.getPontosGanhos();
		
		personagem = new Personagem();
		personagem = p.findTById(resultado.getIdGanhador());
		personagem.setCash(cash);
		personagem.setExperiencia(experiencia);
		
		p.save(personagem);
		
		return "IndexUsuario";
	}
	
	
	private void obtemResultado(Personagem desafiante, Personagem desafiado) {
		int totalDesafiante, totalDesafiado;
		totalDesafiante = desafiante.getForca() + desafiante.getMana();
		totalDesafiado = desafiado.getAgilidade() + desafiado.getDefesa();		
		if(totalDesafiante > totalDesafiado){
			resultado.setIdGanhador(desafiante.getIdPersonagens());
			resultado.setIdPerdedor(desafiado.getIdPersonagens());
		}else{
			resultado.setIdGanhador(desafiado.getIdPersonagens());
			resultado.setIdPerdedor(desafiante.getIdPersonagens());
		}
		resultado.setDesafio(desafio);
		resultado.setDataDuelo(new Date());
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

	public Resultado getResultado() {
		return resultado;
	}

	public void setResultado(Resultado resultado) {
		this.resultado = resultado;
	}

	public Desafio getDesafio() {
		return desafio;
	}

	public void setDesafio(Desafio desafio) {
		this.desafio = desafio;
	}
	
	public String url(){
    	UsuarioHIB userHib = new UsuarioHIB();
		PersonagemHIB personHib = new PersonagemHIB();
		personagem = personHib.findTByIdUser(userHib.findTByLogin(Util.getUserSession()).getIdUsuarios());
		if(personagem.getAtivo()==false)
		{
			return "Trabalhando?faces-redirect=true";
		}
    	return "Duelo?faces-redirect=true";
    }
	
}
