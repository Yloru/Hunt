package com.jsf.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.jsf.entities.User;
import com.jsf.entities.UserRole;
import com.jsf.entities.Vote;




@Stateless
public class UserRoleDAO {
	
	@PersistenceContext
	EntityManager em;
	
	public void create(UserRole user_role) {
		em.persist(user_role);
	}

	public UserRole merge(UserRole user_role) {
		return em.merge(user_role);
	}

	public void remove(UserRole user_role) {
		em.remove(em.merge(user_role));
	}

	public UserRole find(Object id) {
		return em.find(UserRole.class, id);
	}
}
