package se.plushogskolan.taskhandler.model.abstractclass;

import java.time.LocalDate;

import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AbstractModelClass {

	
	@Id
	@GeneratedValue
	private Long id;

	@CreatedBy
	private String createdBy;
	@CreatedDate
	private LocalDate createdDate = LocalDate.now();
	
	public Long getId() {
		return id;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	public String getCreatedBy() {
		return createdBy;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}
	

	
}
