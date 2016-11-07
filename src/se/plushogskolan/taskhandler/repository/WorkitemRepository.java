package se.plushogskolan.taskhandler.repository;

import org.springframework.data.repository.CrudRepository;

import se.plushogskolan.taskhandler.model.WorkItem;

public interface WorkitemRepository extends CrudRepository<WorkItem, Long> {
	
}