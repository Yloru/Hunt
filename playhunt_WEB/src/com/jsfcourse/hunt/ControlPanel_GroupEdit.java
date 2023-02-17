package com.jsfcourse.hunt;

import java.io.IOException;
import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import com.jsf.dao.GroupDAO;
import com.jsf.entities.Group;

@Named
@ViewScoped
public class ControlPanel_GroupEdit implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String PAGE_PERSON_LIST = "/admin/controlPanel_Groups?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private Group group = new Group();
	private Group loaded = null;

	@EJB
	GroupDAO groupDAO;

	@Inject
	FacesContext context;

	@Inject
	Flash flash;

	public Group getGroup() {
		return group;
	}

	public void onLoad() throws IOException {
		loaded = (Group) flash.get("group");
		if (loaded != null) {
			group = loaded;
		} else {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błędne użycie systemu", null));
		}

	}

	public String saveData() {
		if (loaded == null) {
			return PAGE_STAY_AT_THE_SAME;
		}
		try {
			if (group.getId() == null) {
				group.setActivate("T");
				groupDAO.create(group);
			} else {
				groupDAO.merge(group);
			}
		} catch (Exception e) {
			e.printStackTrace();
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "wystąpił błąd podczas zapisu", null));
			return PAGE_STAY_AT_THE_SAME;
		}

		return PAGE_PERSON_LIST;
	}
}
