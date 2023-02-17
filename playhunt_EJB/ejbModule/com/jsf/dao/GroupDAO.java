package com.jsf.dao;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.jsf.entities.Group;
import com.jsf.entities.Betting;




@Stateless
public class GroupDAO {
	
	@PersistenceContext
	EntityManager em;
	
	public void create(Group group) {
		em.persist(group);
	}

	public Group merge(Group group) {
		return em.merge(group);
	}

	public void remove(Group group) {
		em.remove(em.merge(group));
	}

	public Group find(Object id) {
		return em.find(Group.class, id);
	}

	public List<Group> getFullList() {
		List <Group> list = null;
		
		Query query = em.createQuery("SELECT g FROM Group g");
		
		try {
			list = query.getResultList();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public List<Group> getGroup1List() {
		List <Group> list = null;
		
		Query query = em.createQuery("SELECT g FROM Group g, Betting b WHERE b.group1 = g.id ORDER BY b.id");
		
		try {
			list = query.getResultList();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	public List<Group> getGroup2List() {
		List <Group> list = null;
		
		Query query = em.createQuery("SELECT g FROM Group g, Betting b WHERE b.group2 = g.id ORDER BY b.id");
		
		try {
			list = query.getResultList();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public List<Group> getList(Map<String, Object> searchParams) {
		List<Group> list = null;

		String select = "select g ";
		String from = "from Group g ";
		String where = "";
		String orderby = "order by g.id asc";

		// search for surname
		String name = (String) searchParams.get("name");
		if (name != null) {
			if (where.isEmpty()) {
				where = "where ";
			} else {
				where += "and ";
			}
			where += "g.name like :name ";
		}
		
		// ... other parameters ... 

		// 2. Create query object
		Query query = em.createQuery(select + from + where + orderby);

		// 3. Set configured parameters
		if (name != null) {
			query.setParameter("name", name+"%");
		}

		// ... other parameters ... 

		// 4. Execute query and retrieve list of Person objects
		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
}
