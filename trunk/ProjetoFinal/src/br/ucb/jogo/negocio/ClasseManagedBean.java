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


import br.ucb.jogo.HIB.ClasseHIB;
import br.ucb.jogo.bean.Classe;



@ManagedBean (name = "classeManagedBean")
@SessionScoped
public class ClasseManagedBean {
	private Classe classe;
	private List<Classe> classes;
	private List<Classe> filtroClasses;
	
    public ClasseManagedBean() {
    	classe = new Classe();
    	filtroClasses = new ArrayList<Classe>();
    	populaClasse();
    }  

    private void populaClasse() {
    	ClasseHIB c = new ClasseHIB();
    	setClasses(c.list());
    }
    
    public String incluir() {
		this.classe = new Classe();
		return "CadastroClasse.xhtml";
	}
    
    public Classe getClasse() {  
        return classe;  
    }  
  
    public void setClasse(Classe classe) {  
        this.classe = classe;  
    }  
    
    public List<Classe> getClasses() {
		return classes;
	}

	public void setClasses(List<Classe> classes) {
		this.classes = classes;
	}      
	
	public List<Classe> getFiltroClasses() {
		return filtroClasses;
	}

	public void setFiltroClasses(List<Classe> filtroClasses) {
		this.filtroClasses = filtroClasses;
	}
    public String save() throws IOException {
    	ClasseHIB c = new ClasseHIB();
    	FacesMessage msg;
    	if (this.classe.getIdClasse() == null || this.classe.getIdClasse() == 0) {
    		msg = new FacesMessage("Sucesso", "Classe "+classe.getNome()+" cadastrada com sucesso");  
		}else {
			msg = new FacesMessage("Sucesso", "Classe "+classe.getNome()+" alterada com sucesso");  
		}
    	FacesContext.getCurrentInstance().addMessage(null, msg);
		c.save(getClasse());
		this.classe = new Classe();
		this.classes = c.list();
        return "ListarClasse?faces-redirect=true";
    }
    
    public void excluir(ActionEvent evento) throws SQLException{
    	FacesMessage msg = new FacesMessage("Sucesso", "Classe "+getClasse().getNome()+" excluida com sucesso");  
        FacesContext.getCurrentInstance().addMessage(null, msg); 
        this.classe = (Classe) evento.getComponent().getAttributes().get("classe");
    	ClasseHIB c = new ClasseHIB();
    	c.excluir(getClasse());
    	this.classes = c.list();
	}
    
   
    
}
