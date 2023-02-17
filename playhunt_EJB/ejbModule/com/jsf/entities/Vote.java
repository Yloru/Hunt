package com.jsf.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the votes database table.
 * 
 */
@Entity
@Table(name="votes")
@NamedQuery(name="Vote.findAll", query="SELECT v FROM Vote v")
public class Vote implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	//bi-directional many-to-one association to Betting
	@ManyToOne
	@JoinColumn(name="id_betting")
	private Betting betting;

	//bi-directional many-to-one association to Group
	@ManyToOne
	@JoinColumn(name="id_group")
	private Group group;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="id_user")
	private User user;

	public Vote() {
	}

	

	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public Betting getBetting() {
		return this.betting;
	}

	public void setBetting(Betting betting) {
		this.betting = betting;
	}

	public Group getGroup() {
		return this.group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	

}