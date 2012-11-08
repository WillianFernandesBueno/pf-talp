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
import br.ucb.jogo.HIB.AliancaHIB;
import br.ucb.jogo.bean.Alianca;




@ManagedBean (name = "aliancaManagedBean")
@SessionScoped
public class AliancaManagedBean {
	private Alianca alianca;
	private List<Alianca> aliancas;
	private List<Alianca> filtroAliancas;
	
    public AliancaManagedBean() {
    	populaAlianca();
    	alianca = new Alianca();
    	filtroAliancas = new ArrayList<Alianca>();
    }  

    public Alianca getAlianca() {
		return alianca;
	}

	public void setAlianca(Alianca alianca) {
		this.alianca = alianca;
	}

	public List<Alianca> getAliancas() {
		return aliancas;
	}

	public void setAliancas(List<Alianca> aliancas) {
		this.aliancas = aliancas;
	}

	public List<Alianca> getFiltroAliancas() {
		return filtroAliancas;
	}

	public void setFiltroAliancas(List<Alianca> filtroAliancas) {
		this.filtroAliancas = filtroAliancas;
	}

	private void populaAlianca() {
    	AliancaHIB c = new AliancaHIB();
    	setAliancas(c.list());
    }

    public String save() throws IOException {
    	AliancaHIB c = new AliancaHIB();
    	FacesMessage msg;
    	if (this.alianca.getIdAlianca() == null || this.alianca.getIdAlianca() == 0) {
    		msg = new FacesMessage("Sucesso", "Alianca "+alianca.getNome()+" cadastrada com sucesso");  
		}else {
			msg = new FacesMessage("Sucesso", "Alianca "+alianca.getNome()+" alterada com sucesso");  
		}
    	FacesContext.getCurrentInstance().addMessage(null, msg);
		c.save(getAlianca());
		this.alianca = new Alianca();
		this.aliancas = c.list();
        return "EditarAlianca?faces-redirect=true";
    }
    
    public void excluir(ActionEvent evento) throws SQLException{
    	FacesMessage msg = new FacesMessage("Sucesso", "Alianca "+getAlianca().getNome()+" excluida com sucesso");  
        FacesContext.getCurrentInstance().addMessage(null, msg); 
        this.alianca = (Alianca) evento.getComponent().getAttributes().get("alianca");
    	AliancaHIB c = new AliancaHIB();
    	c.excluir(getAlianca());
    	this.aliancas = c.list();
	}
}
