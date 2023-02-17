package com.jsf.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.jsf.entities.Betting;
import com.jsf.entities.Group;
import com.jsf.entities.User;


@Stateless
public class BettingDAO {
	
	@PersistenceContext
	EntityManager em;
	
	
	public void create(Betting betting) {
		em.persist(betting);
	}

	public Betting merge(Betting betting) {
		return em.merge(betting);
	}

	public void remove(Betting betting) {
		em.remove(em.merge(betting));
	}

	public Betting find(Object id) {
		return em.find(Betting.class, id);
	}
	
	public List<Betting> getFullList() {
		List <Betting> list = null;
		
		Query query = em.createQuery("SELECT b FROM Betting b");
		
		try {
			list = query.getResultList();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

}
