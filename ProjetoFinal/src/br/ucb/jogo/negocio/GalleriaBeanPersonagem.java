package br.ucb.jogo.negocio;

import java.util.ArrayList;  

import java.util.List;  
import javax.annotation.PostConstruct;   
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.ucb.jogo.bean.Char;
@SessionScoped
@ManagedBean (name="galleriaBeanPersonagem")
public class GalleriaBeanPersonagem {  
  
    private List<Char> players; 
  
    @PostConstruct  
    public void init() {  
        players = new ArrayList<Char>();  
  
        players.add(new Char("warrior.jpg","construcao",500,100,1000,300,600));  
        players.add(new Char("scout.jpg","construcao",500,100,1000,300,600));    
    }  
  
    public List<Char> getPlayers() {  
        return players;  
    }  
}  