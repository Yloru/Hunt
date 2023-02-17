package com.jsf.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the bettings database table.
 * 
 */
@Entity
@Table(name="bettings")
@NamedQuery(name="Betting.findAll", query="SELECT b FROM Betting b")
public class Betting implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private byte id;

	private String activate;

	@Column(name="first_group_votes")
	private int firstGroupVotes;

	@Column(name="second_group_votes")
	private int secondGroupVotes;

	@Temporal(TemporalType.DATE)
	@Column(name="when_end")
	private Date whenEnd;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="betting_creator")
	private User user;

	//bi-directional many-to-one association to Group
	@ManyToOne
	@JoinColumn(name="group_first_id")
	private Group group1;

	//bi-directional many-to-one association to Group
	@ManyToOne
	@JoinColumn(name="group_second_id")
	private Group group2;

	//bi-directional many-to-one association to Vote
	@OneToMany(mappedBy="betting")
	private List<Vote> votes;

	public Betting() {
	}

	public byte getId() {
		return this.id;
	}

	public void setId(byte id) {
		this.id = id;
	}

	public String getActivate() {
		return this.activate;
	}

	public void setActivate(String activate) {
		this.activate = activate;
	}

	public int getFirstGroupVotes() {
		return this.firstGroupVotes;
	}

	public void setFirstGroupVotes(int firstGroupVotes) {
		this.firstGroupVotes = firstGroupVotes;
	}

	public int getSecondGroupVotes() {
		return this.secondGroupVotes;
	}

	public void setSecondGroupVotes(int secondGroupVotes) {
		this.secondGroupVotes = secondGroupVotes;
	}

	public Date getWhenEnd() {
		return this.whenEnd;
	}

	public void setWhenEnd(Date whenEnd) {
		this.whenEnd = whenEnd;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Group getGroup1() {
		return this.group1;
	}

	public void setGroup1(Group group1) {
		this.group1 = group1;
	}

	public Group getGroup2() {
		return this.group2;
	}

	public void setGroup2(Group group2) {
		this.group2 = group2;
	}

	public List<Vote> getVotes() {
		return this.votes;
	}

	public void setVotes(List<Vote> votes) {
		this.votes = votes;
	}

	public Vote addVote(Vote vote) {
		getVotes().add(vote);
		vote.setBetting(this);

		return vote;
	}

	public Vote removeVote(Vote vote) {
		getVotes().remove(vote);
		vote.setBetting(null);

		return vote;
	}

}