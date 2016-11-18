package se.plushogskolan.taskhandler.model;


import java.time.LocalDate;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import org.springframework.data.annotation.LastModifiedDate;

import se.plushogskolan.taskhandler.assets.WorkItemStatus;
import se.plushogskolan.taskhandler.model.abstractclass.AbstractModelClass;

@Entity
public class WorkItem extends AbstractModelClass {
	
	
	private String name;	
	private String description;
	private WorkItemStatus status;
	@ManyToMany(mappedBy = "workItems", cascade = CascadeType.ALL)
	private Collection<User> users;
	
	@OneToOne(mappedBy = "workItem")
	private Issue issues;
	
	@LastModifiedDate
	private LocalDate lastModifiedDate = LocalDate.now();
	
	protected WorkItem(){}
	
	public WorkItem(String name, String description, WorkItemStatus workItemStatus) {
		this.name = name;
		this.status = workItemStatus;
		this.description = description;
	}

	public WorkItemStatus getStatus() {
		return status;
	}

	public void setStatus(WorkItemStatus status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public Collection<User> getUsers() {
		return users;
	}

	public void setUsers(Collection<User> users) {
		this.users = users;
	}

	public Issue getIssues() {
		return issues;
	}

	public void setIssues(Issue issues) {
		this.issues = issues;
	}
	
	public void setLastModifiedDate(LocalDate lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
}