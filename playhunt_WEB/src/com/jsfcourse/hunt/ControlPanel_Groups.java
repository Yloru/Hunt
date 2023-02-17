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
import javax.servlet.http.HttpSession;

import com.jsf.dao.GroupDAO;
import com.jsf.entities.Group;

@Named
@RequestScoped
public class ControlPanel_Groups {
	private static final String PAGE_USER_EDIT = "/admin/controlPanel_GroupEdit?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private String name;
		
	@Inject
	ExternalContext extcontext;
	
	@Inject
	Flash flash;
	
	@EJB
	GroupDAO groupDAO;
		
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Group> getFullList(){ //Tworzenie całej listy
		return groupDAO.getFullList();
	}

	public List<Group> getList(){
		List<Group> list = null;
		
		Map<String,Object> searchParams = new HashMap<String, Object>();
		if (name != null && name.length() > 0){
			searchParams.put("name", name);
		}
		list = groupDAO.getList(searchParams);
		
		return list;
	}
	
	public String editGroup(Group group){
		
		flash.put("group", group);
		
		return PAGE_USER_EDIT;
	}
	
	public String newGroup(){
		Group group = new Group();
		flash.put("group", group);
		
		return PAGE_USER_EDIT;
	}

	public String deleteGroup(Group group){
		groupDAO.remove(group);
		return PAGE_STAY_AT_THE_SAME;
	}
	 

}
