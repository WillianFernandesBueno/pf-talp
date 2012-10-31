package br.ucb.jogo.negocio;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import br.ucb.jogo.bean.Trabalho;
@SuppressWarnings("serial")
@ManagedBean(name="trabalhoBean")
public class TrabalhoBean implements Serializable {  

	private List<Trabalho> trabalhos;
	private Trabalho selectTrabalho; 

	public List<Trabalho> getTrabalhos() {
		return trabalhos;
	}

	public void setTrabalhos(List<Trabalho> trabalhos) {
		this.trabalhos = trabalhos;
	}

	public Trabalho getSelectTrabalho() {
		return selectTrabalho;
	}

	public void setSelectTrabalho(Trabalho selectTrabalho) {
		this.selectTrabalho = selectTrabalho;
	}

	public TrabalhoBean() {
		trabalhos = new ArrayList<Trabalho>();
		popula(trabalhos);
	}

	private void popula(List<Trabalho> trabs) {
		
		for (int i = 0; i < 10; i++) {
			trabs.add(new Trabalho("Trabs"+i,"R$"+i,i+"h","exp"+i));
		}
		
	}

	public String teste() {
		System.out.println(selectTrabalho.getTitulo());
		return "IndexAdmin";
		
	}
	
	


	

}  