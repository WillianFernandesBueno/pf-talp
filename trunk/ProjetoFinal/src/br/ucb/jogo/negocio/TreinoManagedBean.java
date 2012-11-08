package br.ucb.jogo.negocio;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import br.ucb.jogo.HIB.TreinoHIB;
import br.ucb.jogo.bean.Treino;


@ManagedBean (name = "treinoManagedBean")
@SessionScoped
public class TreinoManagedBean {
	private Treino treino;
	private List<Treino> treinos;
	private List<Treino> filtroTreinos;
	private Treino selectTreino; 
	
    public TreinoManagedBean() {
    	populaTreino();
    	treino = new Treino();
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
		System.out.println(selectTreino.getNome());
		return "IndexAdmin";
		
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
    	FacesMessage msg = new FacesMessage("Sucesso", "Treino excluido com sucesso");  
        FacesContext.getCurrentInstance().addMessage(null, msg); 
        this.treino = (Treino) evento.getComponent().getAttributes().get("treino");
    	TreinoHIB c = new TreinoHIB();
    	c.excluir(getTreino());
    	this.treinos = c.list();
	}
}
