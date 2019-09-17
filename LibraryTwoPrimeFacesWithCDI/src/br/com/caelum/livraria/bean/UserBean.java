package br.com.caelum.livraria.bean;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.livraria.dao.UserLogin;
import br.com.caelum.livraria.modelo.User;

@Named
@SessionScoped
public class UserBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private User user= new User();
	
	@Inject
	FacesContext context; //Criada sua fábrica
	
	@Inject
	UserLogin userLogin;
	
	public String login() {
		boolean existe = userLogin.login(user);
		if (existe) {
			displayMsg("Loged successfully!");
			context.getExternalContext().getSessionMap().put("user", this.user);
			return returnPage("livro");
		}
		displayMsg("User or password invalids!");
		return returnPage("login");
	}
	
	public String logout() {
		context.getExternalContext().invalidateSession();
		return returnPage("login");
	}
	
	public User getUser() {
		return user;
	}
	
	public void displayMsg(String msg) {
		context.getExternalContext().getFlash().setKeepMessages(true); // Mantenha a msg por 2 requisições
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, ""));
	}
	
	public String returnPage(String page) {
		return "/"+ page +"?faces-redirect=true";
	}



}
