package com.jsf.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idUser;

	private String activate;

	private String email;

	private String login;

	private byte loses;

	private String password;

	private byte wins;

	//bi-directional many-to-one association to Betting
	@OneToMany(mappedBy="user")
	private List<Betting> bettings;

	//bi-directional many-to-one association to UserRole
	@OneToMany(mappedBy="user")
	private List<UserRole> userRoles;

	//bi-directional many-to-one association to Vote
	@OneToMany(mappedBy="user")
	private List<Vote> votes;

	public User() {
	}
	
	

	public Integer getIdUser() {
		return idUser;
	}



	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}



	public String getActivate() {
		return this.activate;
	}

	public void setActivate(String activate) {
		this.activate = activate;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public byte getLoses() {
		return this.loses;
	}

	public void setLoses(byte loses) {
		this.loses = loses;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public byte getWins() {
		return this.wins;
	}

	public void setWins(byte wins) {
		this.wins = wins;
	}

	public List<Betting> getBettings() {
		return this.bettings;
	}

	public void setBettings(List<Betting> bettings) {
		this.bettings = bettings;
	}

	public Betting addBetting(Betting betting) {
		getBettings().add(betting);
		betting.setUser(this);

		return betting;
	}

	public Betting removeBetting(Betting betting) {
		getBettings().remove(betting);
		betting.setUser(null);

		return betting;
	}

	public List<UserRole> getUserRoles() {
		return this.userRoles;
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public UserRole addUserRole(UserRole userRole) {
		getUserRoles().add(userRole);
		userRole.setUser(this);

		return userRole;
	}

	public UserRole removeUserRole(UserRole userRole) {
		getUserRoles().remove(userRole);
		userRole.setUser(null);

		return userRole;
	}

	public List<Vote> getVotes() {
		return this.votes;
	}

	public void setVotes(List<Vote> votes) {
		this.votes = votes;
	}

	public Vote addVote(Vote vote) {
		getVotes().add(vote);
		vote.setUser(this);

		return vote;
	}

	public Vote removeVote(Vote vote) {
		getVotes().remove(vote);
		vote.setUser(null);

		return vote;
	}

}