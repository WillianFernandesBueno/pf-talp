package br.ucb.jogo.negocio;

  
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean (name="criaPersonagemManagedBean")
@SessionScoped
public class CriaPersonagemManagedBean {  

    private String imagem;
    private String nome;
  
    
    
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
		System.out.println("teste");
		System.out.println(getImagem());
		System.out.println(getNome());
		return "IndexUsuario?faces-redirect=true";
    	
    }
}  