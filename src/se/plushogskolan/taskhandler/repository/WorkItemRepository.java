package se.plushogskolan.taskhandler.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import se.plushogskolan.taskhandler.assets.WorkItemStatus;
import se.plushogskolan.taskhandler.model.WorkItem;

public interface WorkItemRepository extends CrudRepository<WorkItem, Long> {
	
	List<WorkItem> findByStatus(WorkItemStatus status);
	
}