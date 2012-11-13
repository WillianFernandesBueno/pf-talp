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

import br.ucb.jogo.HIB.ItemHIB;
import br.ucb.jogo.HIB.PersonagemHIB;
import br.ucb.jogo.HIB.UsuarioHIB;
import br.ucb.jogo.bean.Item;
import br.ucb.jogo.bean.Personagem;
import br.ucb.jogo.service.Util;

@ManagedBean (name = "itensManagedBean")
@SessionScoped
public class ItensManagedBean {
	private Item item;
	private List<Item> itens;
	private List<Item> filtroItens;
	private Personagem personagem;
    public ItensManagedBean() {
    	populaItem();
    	item = new Item();
    	filtroItens = new ArrayList<Item>();
    }  

    private void populaItem() {
    	ItemHIB c = new ItemHIB();
    	setItens(c.list());
    }
    
    public Item getItem() {  
        return item;  
    }  
  
    public void setItem(Item item) {  
        this.item = item;  
    }  
    
    public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}      
	
	public List<Item> getFiltroItens() {
		return filtroItens;
	}

	public void setFiltroItens(List<Item> filtroItens) {
		this.filtroItens = filtroItens;
	}
	
    public String incluir() {
		this.item = new Item();
		return "CadastroItens.xhtml";
	}
    
    public String save() throws IOException {
    	ItemHIB c = new ItemHIB();
    	FacesMessage msg;
    	if (this.item.getIdItem() == null) {
    		msg = new FacesMessage("Sucesso", "Item "+getItem().getNome()+" cadastrada com sucesso");  
		}else {
			msg = new FacesMessage("Sucesso", "Item "+getItem().getNome()+" alterada com sucesso");  
		}
    	FacesContext.getCurrentInstance().addMessage(null, msg);
		c.save(getItem());
		this.item = new Item();
		this.itens = c.list();
        return "ListarItens?faces-redirect=true";
    }
    
    public String saveCompra() throws IOException {
    	
    	PersonagemHIB userHib = new PersonagemHIB();
    	
    	
    	
    	System.out.println(userHib.findTById(1));
    	
        return "ListarItens?faces-redirect=true";
    }
    
    public void excluir(ActionEvent evento) throws SQLException{
    	FacesMessage msg = new FacesMessage("Sucesso", "Item "+getItem().getNome()+" excluido com sucesso");  
        FacesContext.getCurrentInstance().addMessage(null, msg); 
        this.item = (Item) evento.getComponent().getAttributes().get("item");
    	ItemHIB c = new ItemHIB();
    
    	c.excluir(getItem());
    	this.itens = c.list();
	}
    
    public String url(){
    	UsuarioHIB userHib = new UsuarioHIB();
		PersonagemHIB personHib = new PersonagemHIB();
		personagem = personHib.findTByIdUser(userHib.findTByLogin(Util.getUserSession()).getIdUsuarios());
		if(personagem.getAtivo()==false)
		{
			return "Trabalhando?faces-redirect=true";
		}
    	return "Loja?faces-redirect=true";
    }
}
