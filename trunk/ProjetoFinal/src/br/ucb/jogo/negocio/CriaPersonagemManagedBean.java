package br.ucb.jogo.negocio;

  
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.ucb.jogo.HIB.ClasseHIB;
import br.ucb.jogo.HIB.PersonagemHIB;
import br.ucb.jogo.HIB.UsuarioHIB;
import br.ucb.jogo.bean.Classe;
import br.ucb.jogo.bean.Personagem;
import br.ucb.jogo.service.Util;


@ManagedBean (name="criaPersonagemManagedBean")
@SessionScoped
public class CriaPersonagemManagedBean {  

	private Personagem personagem;
    private String imagem;
    private String nome;
    
	public Personagem getPersonagem() {
		return personagem;
	}

	public void setPersonagem(Personagem personagem) {
		this.personagem = personagem;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public String escolheClass()
    {
		return "CriaPersonagem?faces-redirect=true";
    	
    }
	
	public String save()
    {
		List<Personagem> personagem = new ArrayList<Personagem>();
		populaPersonagem(personagem);
		return "IndexUsuario?faces-redirect=true";
    	
    }
	
	private void populaPersonagem(List<Personagem> personagem) {
		
		
	}

	public String encaminha()
    {
		UsuarioHIB userHib = new UsuarioHIB();
		
		return userHib.findTByLogin(Util.getUserSession()).getPersonagem() == null ? "/usuario/CriaPersonagem?faces-redirect=true": "/usuario/IndexUsuario?faces-redirect=true";
    }
}  