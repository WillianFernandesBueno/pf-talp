package br.ucb.jogo.negocio;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import br.ucb.jogo.HIB.ClasseHIB;
import br.ucb.jogo.HIB.LoginHib;
import br.ucb.jogo.HIB.UsuarioHIB;
import br.ucb.jogo.bean.Autorizacao;
import br.ucb.jogo.bean.Classe;
import br.ucb.jogo.bean.Usuario;
import br.ucb.jogo.service.Util;

@ManagedBean(name="usuarioManagedBean")
@SessionScoped
public class UsuarioManagedBean implements Serializable {  


	private static final long serialVersionUID = 1L;
	private Usuario user;   
	private UsuarioHIB hib;
	private LoginHib lhib; 
	private boolean skip;    
	private List<Usuario> usuarios;
	private List<Usuario> filtroUsers;
	
	
	private static Logger logger = Logger.getLogger(UsuarioManagedBean.class.getName());

	public UsuarioManagedBean() {
		setUser(null);
		setHib( new UsuarioHIB());
		setLhib(new LoginHib());  
		setFiltroUsers(new ArrayList<Usuario>());
		populaUsers();
	}
	
	private void populaUsers() {
    	
    	setUsuarios(getHib().list());
    }
	
	public String cadastro(){
		setUser(new Usuario());
		getUser().setTipo(1);
		return "/CadastroUsuario?faces-redirect=true";
	}

	public Usuario getUser() {  
		return user;  
	}  

	public void setUser(Usuario user) {  
		this.user = user;  
	}  

	public UsuarioHIB getHib() {
		return hib;
	}

	public void setHib(UsuarioHIB hib) {
		this.hib = hib;
	}

	public String save() throws IOException {   
				
		insertAutorizacao();
		
		setUser(new Usuario());
		setUsuarios(getHib().list());

		
		if(Util.isUser("admin")){
			System.out.println("oi");
			return "/admin/ListarUsuario?faces-redirect=true";
		}
		if(Util.isUser("usuario")){
			return "/usuario/IndexUsuario?faces-redirect=true";
		}
		return "/Index?faces-redirect=true";

	}  
	
	public String alterar(){
		
		if(Util.isUser("admin")){
			return "/admin/CadastroUsuario?faces-redirect=true";
		}
		
		Usuario usuario = getHib().findTById(1);
		
		return "/usuario/CadastroUsuario?faces-redirect=true";
	}
	

	public void insertAutorizacao() {
		Autorizacao auto;
		if(getUser().getIdUsuarios() == 0){
			auto = new Autorizacao();
			auto.setUsuario(getUser());
		}else{
			auto = getLhib().findTById(getUser().getIdUsuarios());
		}
		getHib().save(getUser());
		getLhib().excluir(auto);
		auto.setPapel(getUser().getTipo() == 1 ? "usuario":"admin");
		getLhib().save(auto);
	}

	public LoginHib getLhib() {
		return lhib;
	}

	public void setLhib(LoginHib lhib) {
		this.lhib = lhib;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public List<Usuario> getFiltroUsers() {
		return filtroUsers;
	}

	public void setFiltroUsers(List<Usuario> filtroUsers) {
		this.filtroUsers = filtroUsers;
	}  
	
	public void excluir(ActionEvent evento) throws SQLException{
		
        setUser((Usuario) evento.getComponent().getAttributes().get("user")); 
    	getHib().excluir(getUser());
    	setUsuarios(getHib().list());
  
	}
	
}  