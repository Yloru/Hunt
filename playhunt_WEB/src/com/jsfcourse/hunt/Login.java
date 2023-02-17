package com.jsfcourse.hunt;

import com.jsf.dao.UserDAO;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.simplesecurity.RemoteClient;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.jsf.dao.*;
import com.jsf.entities.*;

@Named
@RequestScoped
public class Login {
	private static final String PAGE_LOGIN = "/public/login?faces-redirect=true";
	private static final String PAGE_MAIN = "/public/main?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private String login;
	private String password;
	private String repeat;
	private String email;

	@Inject
	Flash flash;

	@EJB
	UserDAO userDAO;

	public void setLogin(String login) {
		this.login = login;
	}

	public String getLogin() {
		return login;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public String getRepeat() {
		return repeat;
	}

	public void setRepeat(String repeat) {
		this.repeat = repeat;
	}

	public String doLogin() {

		FacesContext ctx = FacesContext.getCurrentInstance();

		User user = userDAO.getUserByLoginPassword(login, password);

		if (user == null) {
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Niepoprawny login lub hasło", null));
			return PAGE_LOGIN;
		}

		RemoteClient<User> client = new RemoteClient<User>();
		client.setDetails(user);

		List<String> roles = userDAO.getUserRolesByUser(user);

		if (roles != null) {
			for (String role : roles) {
				client.getRoles().add(role);
			}
		}

		HttpServletRequest request = (HttpServletRequest) ctx.getExternalContext().getRequest();
		client.store( request);
		
		return PAGE_MAIN;
	}
		
	public String doRegister() {
		FacesContext ctx = FacesContext.getCurrentInstance();
		 User user = new User(); 
		
		/*  if (password != repeat) { ctx.addMessage(null, new
		  FacesMessage(FacesMessage.SEVERITY_ERROR, "Hasła nie są takie same", null));
		  return PAGE_STAY_AT_THE_SAME; }*/
		  
		  user.setLogin(login); user.setPassword(password); user.setActivate("T");
		  user.setEmail(email);
		  
		  userDAO.create(user); 
		  return PAGE_MAIN;
		 
	}
	
	public boolean login(){
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);	
		RemoteClient<User> cl = RemoteClient.load(session);
		
		if (cl != null) {
		return true;}
		else {
		return false;}
	}

	public String doLogout() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		session.invalidate();
		return PAGE_LOGIN;
	}

}
