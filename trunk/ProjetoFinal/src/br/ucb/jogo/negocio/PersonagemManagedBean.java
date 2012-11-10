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


@ManagedBean (name="personagemManagedBean")
@SessionScoped
public class PersonagemManagedBean {  

	private Personagem personagem;
	private List<Personagem> personagens;
	private List<Personagem> filtroPersonagens;
	private String imagem;
    private String nome;
    private Classe classe;
    
    
    public PersonagemManagedBean() {
    	setPersonagem(new Personagem());
    	setFiltroPersonagens(new ArrayList<Personagem>());
    	PersonagemHIB p = new PersonagemHIB();
    	setPersonagens(p.list());
    	this.classe = new Classe();
	}
    
    public List<Personagem> getFiltroPersonagens() {
		return filtroPersonagens;
	}

	public void setFiltroPersonagens(List<Personagem> filtroPersonagens) {
		this.filtroPersonagens = filtroPersonagens;
	}

	public Classe getClasse() {
		return classe;
	}

	public void setClasse(Classe classe) {
		this.classe = classe;
	}
	
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
	
	public String saveClass()
    {
		personagem.setClasse(classe);
		System.out.println(personagem.getNick());
		return "CriaPersonagem2?faces-redirect=true";
    	
    }
	
	public String savePersonagem()
    {
		
		PersonagemHIB persHIB = new PersonagemHIB();
		persHIB.save(personagem);
		
		
		return "IndexUsuario?faces-redirect=true";
    	
    }
	

	public String encaminha()
    {
		UsuarioHIB userHib = new UsuarioHIB();
		
		return userHib.findTByLogin(Util.getUserSession()).getPersonagem() == null ? "/usuario/CriaPersonagem?faces-redirect=true": "/usuario/IndexUsuario?faces-redirect=true";
    }
	public List<Personagem> getPersonagens() {
		return personagens;
	}

	public void setPersonagens(List<Personagem> personagens) {
		this.personagens = personagens;
	}

	
}  