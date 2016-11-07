package se.plushogskolan.taskhandler.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import se.plushogskolan.taskhandler.model.abstractclass.AbstractModelClass;

@Entity
public class Issue extends AbstractModelClass {

	private String reason;
	@ManyToOne
	private WorkItem workItem;
	
	protected Issue(){}
	
	public Issue(String reason, int workItemId) {
		this.reason = reason;
	}
	

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
		
}