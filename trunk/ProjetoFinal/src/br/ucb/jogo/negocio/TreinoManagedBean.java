package br.ucb.jogo.negocio;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import br.ucb.jogo.HIB.PersonagemHIB;
import br.ucb.jogo.HIB.PersonagemHasTreinoHIB;
import br.ucb.jogo.HIB.TreinoHIB;
import br.ucb.jogo.HIB.UsuarioHIB;
import br.ucb.jogo.bean.Personagem;
import br.ucb.jogo.bean.PersonagenHasTreino;
import br.ucb.jogo.bean.Treino;
import br.ucb.jogo.service.Util;


@ManagedBean (name = "treinoManagedBean")
@SessionScoped
public class TreinoManagedBean {

	private Treino treino;
	private List<Treino> treinos;
	private List<Treino> filtroTreinos;
	private Treino selectTreino; 
	private Personagem personagem;
	private PersonagemHIB personHib;

	private Date inicial;
	private Date saida;
	
	private boolean disable;

	private PersonagenHasTreino personHasTreino;

	public TreinoManagedBean() {
		populaTreino();
		treino = new Treino();
		personHib = new PersonagemHIB();
		filtroTreinos = new ArrayList<Treino>();
	}  

	private void populaTreino() {
		TreinoHIB c = new TreinoHIB();
		setTreinos(c.list());
	}

	public Treino getTreino() {  
		return treino;  
	}  

	public void setTreino(Treino treino) {  
		this.treino = treino;  
	}  

	public List<Treino> getTreinos() {
		return treinos;
	}

	public void setTreinos(List<Treino> treinos) {
		this.treinos = treinos;
	}      

	public List<Treino> getFiltroTreinos() {
		return filtroTreinos;
	}

	public void setFiltroTreinos(List<Treino> filtroTreinos) {
		this.filtroTreinos = filtroTreinos;
	}

	public Treino getSelectTreino() {
		return selectTreino;
	}

	public void setSelectTreino(Treino selectTreino) {
		this.selectTreino = selectTreino;
	}

	public String teste() {

		PersonagemHasTreinoHIB personHasTreinoHib = new PersonagemHasTreinoHIB();		
		personHasTreino = personHasTreinoHib.findByMaxCadastro(personagem.getIdPersonagens(), treino.getIdTreino());
		UsuarioHIB userHib = new UsuarioHIB();		
		personagem = personHib.findTByIdUser(userHib.findTByLogin(Util.getUserSession()).getIdUsuarios());
		
		
		System.out.println(personHasTreino);
		
		if(personHasTreino == null){

			personagem.setAtivo(false);
			personHib.save(personagem);
			
			inicial = new Date();
			saida = Util.somaData(treino.getTempoNecessario());

			personHasTreino = new PersonagenHasTreino();

			personHasTreino.setDataInicial(inicial);

			personHasTreino.setDataSaida(saida);
			personHasTreino.getPk().setPersonagem(personagem);
			personHasTreino.getPk().setTreino(treino);
			personHasTreinoHib.save(personHasTreino);

		}else{
			
			personHasTreino = new PersonagenHasTreino();
			personHasTreino = personHasTreinoHib.findByMaxCadastro(personagem.getIdPersonagens(), treino.getIdTreino());
			
		}
		if(Util.comparaData(personHasTreino.getDataSaida()) == -1) setDisable(true);
		else 	setDisable(false);
			
		return "Trabalhando?faces-redirect=true";

	}

	public String finalizaTreino() {
		UsuarioHIB userHib = new UsuarioHIB();
		personagem = personHib.findTByIdUser(userHib.findTByLogin(Util.getUserSession()).getIdUsuarios());
		Double cash = personagem.getCash();
		Integer experiencia = personagem.getExperiencia();
		cash += treino.getCash();
		experiencia += treino.getPontos();
		personagem.setAtivo(true);
		personagem.setCash(cash);
		personagem.setExperiencia(experiencia);
		personHib.save(personagem);
		aumentalevel(personagem);
		
		PersonagemHasTreinoHIB personHasTreinoHib = new PersonagemHasTreinoHIB();
		personHasTreino = personHasTreinoHib.findByMaxCadastro(personagem.getIdPersonagens(), treino.getIdTreino());
		personHasTreinoHib.excluir(personHasTreino);
		
		return "Treino?faces-redirect=true";
	}

	public void aumentalevel(Personagem personagem) {
		UsuarioHIB userHib = new UsuarioHIB();
		personagem = personHib.findTByIdUser(userHib.findTByLogin(Util.getUserSession()).getIdUsuarios());
		Integer level = personagem.getLevel();
		level = (personagem.getExperiencia()/1000);
		if(level>personagem.getLevel()){
			Integer AumentaMana = (level*2)+(personagem.getMana());
			Integer AumentaForca = (level*2)+(personagem.getForca());
			Integer AumentaAgilidade = (level*1)+(personagem.getAgilidade());
			Integer AumentaDefesa = (level*1)+(personagem.getDefesa());
			personagem.setAgilidade(AumentaAgilidade);
			personagem.setForca(AumentaForca);
			personagem.setDefesa(AumentaDefesa);
			personagem.setMana(AumentaMana);   
			personagem.setLevel(level);
		}
		personHib.save(personagem);
	}

	public String incluir() {
		this.treino = new Treino();
		return "CadastroTreino.xhtml";
	}

	public String save() throws IOException {
		TreinoHIB c = new TreinoHIB();
		FacesMessage msg;
		if (this.treino.getIdTreino() == null || this.treino.getIdTreino() == 0) {
			msg = new FacesMessage("Sucesso", "Treino cadastrado com sucesso");  
		}else {
			msg = new FacesMessage("Sucesso", "Treino alterado com sucesso");  
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
		c.save(getTreino());
		this.treino = new Treino();
		this.treinos = c.list();
		return "ListarTreino?faces-redirect=true";
	}

	public void excluir(ActionEvent evento) throws SQLException{
		System.out.println("ENTROU NO EXCLUIR!!!");
		FacesMessage msg = new FacesMessage("Sucesso", "Treino excluido com sucesso");  
		FacesContext.getCurrentInstance().addMessage(null, msg); 
		this.treino = (Treino) evento.getComponent().getAttributes().get("treino");
		System.out.println("TREINO = "+this.treino.getNome());
		TreinoHIB c = new TreinoHIB();
		c.excluir(getTreino());
		this.treinos = c.list();
	}


	public String url(){		
		UsuarioHIB userHib = new UsuarioHIB();
		PersonagemHIB personHib = new PersonagemHIB();
		personagem = personHib.findTByIdUser(userHib.findTByLogin(Util.getUserSession()).getIdUsuarios());

		//System.out.println(new TreinoHIB().findByIdMax(personagem.getIdPersonagens()));
		//if(personagem.getAtivo() == false){
		//	return "Trabalhando?faces-redirect=true";
		//}
		return "Treino?faces-redirect=true";
	}

	public Date getInicial() {
		return inicial;
	}

	public void setInicial(Date inicial) {
		this.inicial = inicial;
	}

	public Date getSaida() {
		return saida;
	}

	public void setSaida(Date saida) {
		this.saida = saida;
	}

	public PersonagenHasTreino getPersonHasTreino() {
		return personHasTreino;
	}

	public void setPersonHasTreino(PersonagenHasTreino personHasTreino) {
		this.personHasTreino = personHasTreino;
	}

	public boolean getDisable() {
		return disable;
	}

	public void setDisable(boolean disable) {
		this.disable = disable;
	}

}
