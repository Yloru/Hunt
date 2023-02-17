package com.jsfcourse.hunt;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.simplesecurity.RemoteClient;
import javax.servlet.http.HttpSession;

import com.jsf.dao.GroupDAO;
import com.jsf.entities.Group;
import com.jsf.entities.User;
import com.jsf.dao.BettingDAO;
import com.jsf.entities.Betting;

import com.jsf.dao.VoteDAO;
import com.jsf.entities.Vote;

@Named
@RequestScoped
public class ControlPanel_Bettings {
	private static final String PAGE_EDIT = "/admin/controlPanel_BettingEdit?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private String name;
		
	@Inject
	ExternalContext extcontext;
	
	@Inject
	Flash flash;
	
	@EJB
	BettingDAO bettingDAO;
	@EJB
	GroupDAO groupDAO;
	@EJB
	VoteDAO voteDAO;

	public List<Group> getGroup1List(){ //Tworzenie listy grp 1
		return groupDAO.getGroup1List();
	}
	public List<Group> getGroup2List(){ //Tworzenie listy grp 2
		return groupDAO.getGroup2List();
	}
	
	public List<Betting> getFullList(){ //Tworzenie całej listy
		return bettingDAO.getFullList();
	}
	
	public boolean login(){
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);	
		RemoteClient<User> cl = RemoteClient.load(session);
		
		if (cl != null) {
		return true;}
		else {
		return false;}
	}
	
	public boolean canVote(/* Object id */){
		/*
		 * HttpSession session = (HttpSession)
		 * FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		 * RemoteClient<User> cl = RemoteClient.load(session);
		 * 
		 * if (cl != null) { User u = cl.getDetails(); Vote v = voteDAO.find(id);
		 * 
		 * if (u.getIdUser() != v.getUser(idUser)) { return true; } }
		 * 
		 * return false;
		 */
		return true;
	}
	
	public String chooseOne(Betting betting) {
		// 1. Zaznaczyć, że dany użytkownik głosował
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		RemoteClient<User> cl = RemoteClient.load(session);
		
		if(cl != null) { 
		
		//2. Czy dany użytkownik zagłosował
		
		Vote v = new Vote();
		
		v.setUser(cl.getDetails());
		v.setBetting(betting);
		v.setGroup(betting.getGroup1());
		
		voteDAO.create(v);
		
		//3. Aktualizacja głosu
		betting.setFirstGroupVotes(betting.getFirstGroupVotes()+1);
		bettingDAO.merge(betting);
		}
		
		return null;
	}
	
	public String chooseTwo(Betting betting) {
		// 1. Zaznaczyć, że dany użytkownik głosował
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		RemoteClient<User> cl = RemoteClient.load(session);
		
		if(cl != null) { //osoba nie jest zalogowana
		
		//2. Czy dany użytkownik zagłosował
		
		Vote v = new Vote();
		
		v.setUser(cl.getDetails());
		v.setBetting(betting);
		v.setGroup(betting.getGroup2());
		
		voteDAO.create(v);
		
		//3. Aktualizacja głosu
		
		betting.setSecondGroupVotes(betting.getSecondGroupVotes()+1);
		bettingDAO.merge(betting);
		}
		return null;
	}
	
	
	
	// Procenty
	
	public double percentOne(double group1, double group2){
		return Math.round((group1*100/(group1+group2)) * 100.0) / 100.0;
	}
	
	public double percentTwo(double group1, double group2){
		return Math.round((group2*100/(group1+group2)) * 100.0) / 100.0;
	}
}
