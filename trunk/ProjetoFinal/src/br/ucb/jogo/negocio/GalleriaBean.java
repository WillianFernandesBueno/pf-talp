package br.ucb.jogo.negocio;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
@ManagedBean
public class GalleriaBean {  
	  
    private List<String> images;  
  
    @PostConstruct  
    public void init() {  
        images = new ArrayList<String>();  
  
        for(int i=1;i<=5;i++) {  
            images.add("galleria" + i + ".jpg");  
        }  
    }  
  
    public List<String> getImages() {  
        return images;  
    }   
}  