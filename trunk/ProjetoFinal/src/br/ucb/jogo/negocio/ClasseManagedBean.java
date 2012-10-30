package br.ucb.jogo.negocio;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
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
	private Classe classe;
	private List<Classe> classes;
	private List<Classe> filtroClasses;
	
	private boolean skip;  
      
    private static Logger logger = Logger.getLogger(ClasseManagedBean.class.getName());  
  
    
    public ClasseManagedBean() {
    	populaClasse();
    	classe = new Classe();
    }  

    private void populaClasse() {
    	ClasseHIB c = new ClasseHIB();
    	setClasses(c.list());
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
    	if (this.classe.getIdClasse() == 0) {
    		c.save(getClasse());
    		FacesMessage msg = new FacesMessage("Sucesso", "Classe "+classe.getNome()+" cadastrada com sucesso");  
            FacesContext.getCurrentInstance().addMessage(null, msg);
		}else {
			c.save(getClasse());
			FacesMessage msg = new FacesMessage("Sucesso", "Classe "+classe.getNome()+" alterada com sucesso");  
            FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		
        return "IndexAdmin?faces-redirect=true";
    }
    
    public void excluir(ActionEvent evento) throws SQLException{
    	FacesMessage msg = new FacesMessage("Sucesso", "Classe "+getClasse().getNome()+" excluida com sucesso");  
        FacesContext.getCurrentInstance().addMessage(null, msg); 
        this.classe = (Classe) evento.getComponent().getAttributes().get("classe");
    	ClasseHIB c = new ClasseHIB();
    	c.excluir(getClasse());
    	this.classes = c.list();
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
