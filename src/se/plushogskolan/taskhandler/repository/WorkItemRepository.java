package se.plushogskolan.taskhandler.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import se.plushogskolan.taskhandler.assets.WorkItemStatus;
import se.plushogskolan.taskhandler.model.WorkItem;

public interface WorkItemRepository extends CrudRepository<WorkItem, Long> , PagingAndSortingRepository<WorkItem, Long>{
	
	
	@Query("SELECT e FROM #{#entityName} e WHERE e.status = 2 AND e.lastModifiedDate BETWEEN :fromDate AND :toDate")
	List<WorkItem> getWorkItemHistory(@Param("fromDate") LocalDate fromDate, @Param("toDate") LocalDate toDate);
	
	Page<WorkItem> findAll(Pageable page);
	
	List<WorkItem> findByStatus(WorkItemStatus status);
	
	List<WorkItem> findByDescriptionContaining(String text);
	
}