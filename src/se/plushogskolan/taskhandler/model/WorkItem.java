package se.plushogskolan.taskhandler.model;


import java.util.Collection;
import java.util.HashSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import se.plushogskolan.taskhandler.assets.WorkItemStatus;
import se.plushogskolan.taskhandler.model.abstractclass.AbstractModelClass;

@Entity
public class WorkItem extends AbstractModelClass {
	
	
	private String name;	
	private WorkItemStatus status;
	@ManyToMany(cascade = CascadeType.ALL)
	private Collection<User> users;
	
	@OneToMany(mappedBy = "workItem")
	private Collection<Issue> issues;
	
	protected WorkItem(){}
	
	public WorkItem(String name, WorkItemStatus workItemStatus){
		this.name = name;
		this.status = workItemStatus;
		issues = new HashSet<>();
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

	public Collection<Issue> getIssues() {
		return issues;
	}

	public void setIssues(Collection<Issue> issues) {
		this.issues = issues;
	}
}