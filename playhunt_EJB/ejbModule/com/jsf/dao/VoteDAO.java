package com.jsf.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import com.jsf.entities.User;
import com.jsf.entities.Vote;




@Stateless
public class VoteDAO {
	
	@PersistenceContext
	EntityManager em;
	
	public void create(Vote vote) {
		em.persist(vote);
	}

	public Vote merge(Vote vote) {
		return em.merge(vote);
	}

	public void remove(Vote vote) {
		em.remove(em.merge(vote));
	}

	public Vote find(Object id) {
		return em.find(Vote.class, id);
	}
	
}
