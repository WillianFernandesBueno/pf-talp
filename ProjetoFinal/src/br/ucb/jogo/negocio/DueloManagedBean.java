package br.ucb.jogo.negocio;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.log4j.Logger;

import br.ucb.jogo.HIB.ClasseHIB;
import br.ucb.jogo.HIB.DesafioHIB;
import br.ucb.jogo.HIB.PersonagemHIB;
import br.ucb.jogo.HIB.ResultadoHIB;
import br.ucb.jogo.HIB.UsuarioHIB;
import br.ucb.jogo.bean.Classe;
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
	private Desafio selectDesafio;
	private DesafioHIB d;
	private List<Desafio> desafios; 
	
	private static final Logger log = Logger.getLogger(DueloManagedBean.class);
	
	PersonagemHIB p;
	public DueloManagedBean() {
		this.personagem = new Personagem();
		this.resultado = new Resultado();
		this.desafio = new Desafio();
		this.personHib = new PersonagemHIB();
		this.d = new DesafioHIB();
		this.p = new PersonagemHIB();
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
			log.info("duelo cadastrado! Desafiante: " +desafiante.getNick()+", Desafiado: "+desafiado.getNick() );
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

	public String carregaDesafios(){

		personagem = new PersonagemHIB().findTByIdUser(new UsuarioHIB().findTByLogin(Util.getUserSession()).getIdUsuarios()); 

		setDesafios(d.findByDesafios(personagem.getIdPersonagens()));


		return "DueloDesafiado?faces-redirect=true";
	}


	public String resultado(){		
		selectDesafio.setDueloAtivo(false);
		d.save(selectDesafio);
		Personagem desafiante = p.findTById(selectDesafio.getIdDesafiante());
		Personagem desafiado  = p.findTById(selectDesafio.getIdDesafiado());
		obtemResultado(desafiante, desafiado);
		
		log.info("duelo finalizado! Desafiante: " +desafiante.getNick()+", Desafiado: "+desafiado.getNick() );
		return "IndexUsuario?faces-redirect=true";
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

		resultado.setDesafio(selectDesafio);
		resultado.setDataDuelo(new Date());
		resultado.setPontosGanhos(100);
		personagem = p.findTById(resultado.getIdGanhador());
		ResultadoHIB r = new ResultadoHIB();
		atualizaPersonGanhador(p);
		desafiado.setSituacaoDuel(1);
		p.save(desafiado);
		r.save(resultado);
	}

	public void atualizaPersonGanhador(PersonagemHIB p){

		Double cash = personagem.getCash();
		Integer experiencia = personagem.getExperiencia();
		cash += selectDesafio.getAposta();
		experiencia += resultado.getPontosGanhos();
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

	public Desafio getSelectDesafio() {
		return selectDesafio;
	}

	public void setSelectDesafio(Desafio selectDesafio) {
		this.selectDesafio = selectDesafio;
	}

	public List<Desafio> getDesafios() {
		return desafios;
	}

	public void setDesafios(List<Desafio> desafios) {
		this.desafios = desafios;
	}

	public void excluir(ActionEvent evento) throws SQLException{
		this.selectDesafio = (Desafio) evento.getComponent().getAttributes().get("desafio");
		Personagem desafiado = p.findTById(selectDesafio.getIdDesafiado());
		desafiado.setSituacaoDuel(desafiado.getSituacaoDuel()+1);
		p.save(desafiado);
		excluiResultado(selectDesafio);
		d.excluir(selectDesafio);
		this.desafios = d.findByDesafios(selectDesafio.getIdDesafiado());
	}

	public void excluiResultado(Desafio desafio){
		ResultadoHIB resulHib = new ResultadoHIB();

		List<Resultado> resultados = resulHib.findByResultados(desafio.getIdDesafios());
		if(resultados != null){
			for (Resultado resultado : resultados) {
				resulHib.excluir(resultado);
			}
		}

	}

}
