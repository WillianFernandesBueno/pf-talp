package br.ucb.jogo.negocio;

import java.io.IOException;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.FlowEvent;

import br.ucb.jogo.HIB.UsuarioHIB;
import br.ucb.jogo.bean.Usuario;

@ManagedBean(name="usuarioManagedBean")
@SessionScoped
public class UsuarioManagedBean {  
	  
    private Usuario user;   
    private UsuarioHIB hib; 
    
    
    private boolean skip;  
      
    private static Logger logger = Logger.getLogger(UsuarioManagedBean.class.getName());  
  
    
    public UsuarioManagedBean() {
    	setUser(new Usuario());
    	setHib( new UsuarioHIB());
	}

	public Usuario getUser() {  
        return user;  
    }  
  
    public void setUser(Usuario user) {  
        this.user = user;  
    }  
    
      
    public UsuarioHIB getHib() {
		return hib;
	}

	public void setHib(UsuarioHIB hib) {
		this.hib = hib;
	}

	public String save() throws IOException {   
    	
        FacesMessage msg = new FacesMessage("Successful", "Welcome :" + user.getNome());  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
        getUser().setTipo(1);
       
        
        System.out.println("Oi");
        getHib().save(getUser());
         
        return "/Index?faces-redirect=true";
        
    }  
      
    public boolean isSkip() {  
        return skip;  
    }  
  
    public void setSkip(boolean skip) {  
        this.skip = skip;  
    }  
      
    public String onFlowProcess(FlowEvent event) {  
        logger.info("Current wizard step:" + event.getOldStep());  
        logger.info("Next step:" + event.getNewStep());  
          
        if(skip) {  
            skip = false;   //reset in case user goes back  
            return "confirm";  
        }  
        else {  
            return event.getNewStep();  
        }  
    }  
}  