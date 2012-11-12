package br.ucb.jogo.negocio;

import java.util.Date;
import java.util.List;

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
	private List<Personagem> personagensDuelo;
	private PersonagemHIB personHib;

	public DueloManagedBean() {
		this.personagem = new Personagem();
		this.resultado = new Resultado();
		this.desafio = new Desafio();
		this.personHib = new PersonagemHIB();
		setPersonagensDuelo(personHib.listaDisponiveisDuelo(personHib.findTByIdUser(new UsuarioHIB().findTByLogin(Util.getUserSession()).getIdUsuarios())));
	}

	public PersonagemHIB getPersonHib() {
		return personHib;
	}

	public void setPersonHib(PersonagemHIB personHib) {
		this.personHib = personHib;
	}

	public String saveDuelo() {

		UsuarioHIB u = new UsuarioHIB();
		PersonagemHIB p = new PersonagemHIB();
		DesafioHIB d = new DesafioHIB();
		Usuario userLogado = u.findTByLogin(Util.getUserSession());
		Personagem desafiante = p.findTByIdUser(userLogado.getIdUsuarios());
		Personagem desafiado  = this.personagem;
		desafio = d.findByDesafio(desafiado.getIdPersonagens(), desafiante.getIdPersonagens());
		if(desafio == null){
			desafio = new Desafio();
			desafio.setDueloAtivo(false);
		}else{
			desafiante = p.findTById(desafio.getIdDesafiante());
			desafiado  = p.findTById(desafio.getIdDesafiado());
		}

		if(desafio != null && !desafio.getDueloAtivo()){
			desafio.setIdDesafiado(desafiado.getIdPersonagens());
			desafio.setIdDesafiante(desafiante.getIdPersonagens());
			desafio.setAposta(Float.parseFloat("100"));
			desafio.setDueloAtivo(true);
			d.save(desafio);
			return "PerfilPersonagem?faces-redirect=true";
		}else{ 
			if (desafiante.getIdPersonagens() != new PersonagemHIB().findTByIdUser(new UsuarioHIB().findTByLogin(Util.getUserSession()).getIdUsuarios()).getIdPersonagens()) {
				desafio.setDueloAtivo(false);
				d.save(desafio);
				obtemResultado(desafiante, desafiado);
				atualizaPersonGanhador(p);
			}
		}

		return "IndexUsuario";
	}
	
	public String resultado(){
		
		
		return "";
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

	public void atualizaPersonGanhador(PersonagemHIB p){
		//ATUALIZANDO AQUI OS DADOS DO PERSONAGEM

		Double cash = personagem.getCash();
		Integer experiencia = personagem.getExperiencia();
		cash += desafio.getAposta();
		experiencia += resultado.getPontosGanhos();

		personagem = new Personagem();
		personagem = p.findTById(resultado.getIdGanhador());
		personagem.setCash(cash);
		personagem.setExperiencia(experiencia);

		p.save(personagem);

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
		if(!personagem.getAtivo()){
			return "Treino?faces-redirect=true";
		}
		return "Duelo?faces-redirect=true";
	}

	public List<Personagem> getPersonagensDuelo() {
		return personagensDuelo;
	}

	public void setPersonagensDuelo(List<Personagem> personagensDuelo) {
		this.personagensDuelo = personagensDuelo;
	}
	

}
