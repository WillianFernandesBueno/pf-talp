package br.ucb.jogo.negocio;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import br.ucb.jogo.HIB.PersonagemHIB;
import br.ucb.jogo.HIB.TreinoHIB;
import br.ucb.jogo.HIB.UsuarioHIB;
import br.ucb.jogo.bean.Personagem;
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
		
		
		UsuarioHIB userHib = new UsuarioHIB();
		personagem = personHib.findTByIdUser(userHib.findTByLogin(Util.getUserSession()).getIdUsuarios());
		personagem.setAtivo(true);
		personHib.save(personagem);
		return "Trabalhando?faces-redirect=true";
		
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
    	return "Treino?faces-redirect=true";
    }
}
