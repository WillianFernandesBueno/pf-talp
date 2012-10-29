package br.ucb.jogo.tabela;


import java.io.Serializable;
import java.util.List;  
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import br.ucb.jogo.HIB.ClasseHIB;
import br.ucb.jogo.bean.Classe;


@SuppressWarnings("serial")
@SessionScoped
@ManagedBean (name = "tableClasse")
public class TableClasse implements Serializable{  
	private List<Classe> filtroClasses;
	private List<Classe> classes;
	private Classe classeSelecionada;

	public TableClasse() {
		populaClasse();

	}  

	private void populaClasse() {
		ClasseHIB c = new ClasseHIB();
		setClasses(c.list());
	}

	public List<Classe> getFiltroClasses() {
		return filtroClasses;
	}

	public void setFiltroClasses(List<Classe> filtroClasses) {
		this.filtroClasses = filtroClasses;
	}

	public Classe getClasseSelecionada() {
		return classeSelecionada;
	}

	public void setClasseSelecionada(Classe classeSelecionada) {
		this.classeSelecionada = classeSelecionada;
	}

	public List<Classe> getClasses() {  
		return classes;  
	}  	

	public void setClasses(List <Classe> classes) {  
		this.classes = classes;  
	}  	

}
