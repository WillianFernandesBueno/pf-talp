package br.ucb.jogo.negocio;

import java.io.Serializable;  

import javax.faces.bean.ManagedBean;
@SuppressWarnings("serial")
@ManagedBean(name="pprBean")
public class PPRBean implements Serializable {  
  
    private String login;  
      
    public String getLogin() {  
        return login;  
    }  
  
    public void setLogin(String login) {  
        this.login = login;  
    }  
    
    public String encaminha()
    {
		System.out.println(login);
		return "Cadastro.xhtml";
    	
    }
}  