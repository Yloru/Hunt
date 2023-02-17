package com.jsf.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.jsf.entities.Role;




@Stateless
public class RoleDAO {
	
	@PersistenceContext
	EntityManager em;
	
	public void create(Role role) {
		em.persist(role);
	}

	public Role merge(Role role) {
		return em.merge(role);
	}

	public void remove(Role role) {
		em.remove(em.merge(role));
	}

	public Role find(Object id) {
		return em.find(Role.class, id);
	}
}
