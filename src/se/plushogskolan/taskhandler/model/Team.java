package se.plushogskolan.taskhandler.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import se.plushogskolan.taskhandler.model.abstractclass.AbstractModelClass;

@Entity
public class Team extends AbstractModelClass {

	private String teamName;
	@OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
	private Collection<User> users;
	private boolean isTeamActive;
	
	protected Team(){}

	public Team(String teamName, boolean isActive) {
		this.teamName = teamName;
		this.isTeamActive = isActive;
	}

	@Override
	public String toString() {
		return "id =" + " " + getId() + " Teamname= " + teamName;
	}

	public boolean isTeamActive() {
		return isTeamActive;
	}

	public void setTeamActive(boolean isTeamActive) {
		this.isTeamActive = isTeamActive;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getTeamName() {
		return teamName;
	}

	public Collection<User> getUsers() {
		return users;
	}

	public void setUsers(Collection<User> users) {
		this.users = users;
	}

}