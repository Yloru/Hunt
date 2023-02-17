package com.jsf.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.jsf.entities.User;
import com.jsf.entities.UserRole;

@Stateless
public class UserDAO {
	private final static String UNIT_NAME = "jsfcourse-simplePU";

	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;

	public void create(User user) {
		em.persist(user);
	}

	public User merge(User user) {
		return em.merge(user);
	}

	public void remove(User user) {
		em.remove(em.merge(user));
	}

	public User find(Object id) {
		return em.find(User.class, id);
	}

	/*
	 * public User findLogin(String login) { return
	 * em.createQuery("SELECT u FROM User u WHERE u.login = :login", User.class)
	 * .setParameter("login", login) .getSingleResult(); }
	 */

	public User getUserByLoginPassword(String login, String password) {
		User u = null;

		try {
			u = em.createQuery("SELECT u FROM User u WHERE u.login = :login AND u.password = :password", User.class)
					.setParameter("login", login).setParameter("password", password).getSingleResult();
		} catch (NoResultException e) {

		}
		return u;
	}

	/*
	 * public User createUser(String login, String password) { return em.
	 * createQuery("INSERT INTO u (login,password,email) VALUES (:login,:password,'cos')"
	 * , User.class) .setParameter("login", login) .getSingleResult(); }
	 */

	public List<User> getFullList() {
		List<User> list = null;

		Query query = em.createQuery("SELECT u FROM User u");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public List<User> getList(Map<String, Object> searchParams) {
		List<User> list = null;

		String select = "select u ";
		String from = "from User u ";
		String where = "";
		String orderby = "order by u.login asc, u.password";

		// search for login
		String login = (String) searchParams.get("login");
		if (login != null) {
			if (where.isEmpty()) {
				where = "where ";
			} else {
				where += "and ";
			}
			where += "u.login like :login ";
		}

		// 2. Create query object
		Query query = em.createQuery(select + from + where + orderby);

		// 3. Set configured parameters
		if (login != null) {
			query.setParameter("login", login + "%");
		}

		// 4. Execute query and retrieve list of Person objects
		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public User getUserByLogin(String login) {
		return em.createQuery("SELECT u FROM User u WHERE u.login = :login", User.class).setParameter("login", login)
				.getSingleResult();
	}

	public List<String> getUserRolesByUser(User user) {

		ArrayList<String> roles = new ArrayList<String>();
		List<UserRole> ur = null;
		Integer id = user.getIdUser();

		Query query = em.createQuery("SELECT ur FROM UserRole ur WHERE ur.user.idUser = :id");
		query.setParameter("id", id);

		try {
			ur = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (UserRole uRole : ur) {
			roles.add(uRole.getRole().getName());
		}

		return roles;
	}

	/*
	 * public User getUserFromDatabase(String login, String password) { User user =
	 * getUserByLogin(login); if (user != null &&
	 * user.getPassword().equals(password)) { return user; } else{ return null; }
	 */
}
