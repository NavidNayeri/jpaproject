package se.plushogskolan.taskhandler.model.abstractclass;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class AbstractModelClass {

	
	@Id
	@GeneratedValue
	private Long id;

	public Long getId() {
		return id;
	}
	

	
}
