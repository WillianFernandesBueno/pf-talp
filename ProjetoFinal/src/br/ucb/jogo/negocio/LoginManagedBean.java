package br.ucb.jogo.negocio;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.ucb.jogo.service.Util;

@ManagedBean(name="loginManagedBean")
@SessionScoped
public class LoginManagedBean {

	public String logOut(){

		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session = request.getSession();
		session.invalidate();
		return "/Index";
	}
	
	public boolean isUserInRole(String role){
		return Util.isUser(role);
	}

	
}
