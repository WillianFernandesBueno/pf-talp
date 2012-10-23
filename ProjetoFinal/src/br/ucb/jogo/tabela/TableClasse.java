package br.ucb.jogo.tabela;


import java.util.ArrayList;  
import java.util.List;  
  
import br.ucb.jogo.HIB.ClasseHIB;
import br.ucb.jogo.bean.Classe;

public class TableClasse {  
	private List<Classe> filtroClasses;
	private List<Classe> classes;
	private Classe classeSelecionada;
	
	
	
	    public TableClasse() {
	        classes = new ArrayList<Classe>();
	        populaClasse(classes);
	        
	    }  
	  
	    private void populaClasse(List<Classe> classes) {
	    	ClasseHIB c = new ClasseHIB();
			classes = c.list();
		}
	    

		public Classe getSelectedClasse() {  
	        return classeSelecionada;  
	    }  
	  
	    public void setSelectedClasse(Classe classeSelecionada) {  
	        this.classeSelecionada = classeSelecionada;  
	    }  
	  
	    public List<Classe> getFiltroClasses() {  
	        return filtroClasses;  
	    }  
	  
	    public void setFilteredCars(List<Classe> filtroClasses) {  
	        this.filtroClasses = filtroClasses;  
	    }  
	  
	    public List<Classe> getClasses() {  
	        return classes;  
	    }  	
	
}
