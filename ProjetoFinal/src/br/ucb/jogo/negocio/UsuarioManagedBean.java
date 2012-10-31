package br.ucb.jogo.negocio;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.FlowEvent;

import br.ucb.jogo.HIB.LoginHib;
import br.ucb.jogo.HIB.UsuarioHIB;
import br.ucb.jogo.bean.Autorizacao;
import br.ucb.jogo.bean.Usuario;

@ManagedBean(name="usuarioManagedBean")
@SessionScoped
public class UsuarioManagedBean implements Serializable {  


	private static final long serialVersionUID = 1L;
	private Usuario user;   
	private UsuarioHIB hib;
	private LoginHib lhib; 
	private boolean skip;    
	private static Logger logger = Logger.getLogger(UsuarioManagedBean.class.getName());


	public UsuarioManagedBean() {
		setUser(null);
		setHib( new UsuarioHIB());
		setLhib(new LoginHib());    	

	}
	
	public String cadastro(){
		setUser(new Usuario());
		return "/CadastroUsuario";
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
		getHib().save(getUser());
		
		//Autorizacao
		insertAutorizacao();
		
		return "/Index?faces-redirect=true";

	}  

	public void insertAutorizacao() {
		Autorizacao auto = new Autorizacao();
		auto.setUsuario(getUser());
		auto.setPapel("usuario");
		getLhib().save(auto);
	}

	public boolean isSkip() {  
		return skip;  
	}  

	public void setSkip(boolean skip) {  
		this.skip = skip;  
	}  

	public LoginHib getLhib() {
		return lhib;
	}

	public void setLhib(LoginHib lhib) {
		this.lhib = lhib;
	}  


}  