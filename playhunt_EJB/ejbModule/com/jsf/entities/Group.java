package com.jsf.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the groups database table.
 * 
 */
@Entity
@Table(name="groups")
@NamedQuery(name="Group.findAll", query="SELECT g FROM Group g")
public class Group implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String activate;

	private String name;

	//bi-directional many-to-one association to Betting
	@OneToMany(mappedBy="group1")
	private List<Betting> bettings1;

	//bi-directional many-to-one association to Betting
	@OneToMany(mappedBy="group2")
	private List<Betting> bettings2;

	//bi-directional many-to-one association to Vote
	@OneToMany(mappedBy="group")
	private List<Vote> votes;




	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getActivate() {
		return this.activate;
	}

	public void setActivate(String activate) {
		this.activate = activate;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Betting> getBettings1() {
		return this.bettings1;
	}

	public void setBettings1(List<Betting> bettings1) {
		this.bettings1 = bettings1;
	}

	public Betting addBettings1(Betting bettings1) {
		getBettings1().add(bettings1);
		bettings1.setGroup1(this);

		return bettings1;
	}

	public Betting removeBettings1(Betting bettings1) {
		getBettings1().remove(bettings1);
		bettings1.setGroup1(null);

		return bettings1;
	}

	public List<Betting> getBettings2() {
		return this.bettings2;
	}

	public void setBettings2(List<Betting> bettings2) {
		this.bettings2 = bettings2;
	}

	public Betting addBettings2(Betting bettings2) {
		getBettings2().add(bettings2);
		bettings2.setGroup2(this);

		return bettings2;
	}

	public Betting removeBettings2(Betting bettings2) {
		getBettings2().remove(bettings2);
		bettings2.setGroup2(null);

		return bettings2;
	}

	public List<Vote> getVotes() {
		return this.votes;
	}

	public void setVotes(List<Vote> votes) {
		this.votes = votes;
	}

	public Vote addVote(Vote vote) {
		getVotes().add(vote);
		vote.setGroup(this);

		return vote;
	}

	public Vote removeVote(Vote vote) {
		getVotes().remove(vote);
		vote.setGroup(null);

		return vote;
	}

}