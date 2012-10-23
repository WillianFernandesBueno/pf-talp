package br.ucb.jogo.negocio;

import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.FlowEvent;

import br.ucb.jogo.HIB.ClasseHIB;
import br.ucb.jogo.bean.Classe;



@ManagedBean (name = "classeManagedBean")
@SessionScoped
public class ClasseManagedBean {
	private Classe classe = new Classe();  
    
    private boolean skip;  
      
    private static Logger logger = Logger.getLogger(ClasseManagedBean.class.getName());  
  
    public Classe getClasse() {  
        return classe;  
    }  
  
    public void setClasse(Classe classe) {  
        this.classe = classe;  
    }  
      
    public String save(ActionEvent actionEvent) {   
    	
        FacesMessage msg = new FacesMessage("Sucesso", "Classe "+classe.getNome()+" cadastrada com sucesso");  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
        ClasseHIB c = new ClasseHIB();
        c.save(getClasse());
        return "IndexAdmin.xhtml";
    }  
      
    public boolean isSkip() {  
        return skip;  
    }  
  
    public void setSkip(boolean skip) {  
        this.skip = skip;  
    }  
      
    public String onFlowProcess(FlowEvent event) {  
        logger.info("Passo atual: " + event.getOldStep());  
        logger.info("Próximo passo: " + event.getNewStep());  
          
        if(skip) {  
            skip = false;   //reset in case user goes back  
            return "confirm";  
        }  
        else {  
            return event.getNewStep();  
        }  
    }
}
