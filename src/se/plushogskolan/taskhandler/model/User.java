package se.plushogskolan.taskhandler.model;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import se.plushogskolan.taskhandler.model.abstractclass.AbstractModelClass;


@Entity
public final class User extends AbstractModelClass {

	private String firstName;
	private String lastName;
	@Column(unique = true)
	private String username;
	private String password;
	private String number;
	@ManyToOne
	private Team team;
	@ManyToMany(fetch = FetchType.EAGER)
	private Collection<WorkItem> workItems;
	private boolean isActive;
	
	protected User() {}
	
	public User(String firstName, String lastName, String userName, String password, boolean isActive) {
		this.isActive = isActive;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = userName;
		this.password = password;
		workItems = new HashSet<>();
	}


	@Override
	public String toString() {	
		return "Used id=" + getId() + "firstname: " + firstName;
	}


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return username;
	}

	public void setUserName(String userName) {
		this.username = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public Collection<WorkItem> getWorkItems() {
		return workItems;
	}

	public void setWorkItems(Collection<WorkItem> workItems) {
		this.workItems = workItems;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

}