package se.plushogskolan.taskhandler.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import se.plushogskolan.taskhandler.model.Issue;

public interface IssueRepository extends CrudRepository<Issue, Long>, PagingAndSortingRepository<Issue, Long>{
	
	Page<Issue> findAll(Pageable page);

	List<Issue> findByWorkItemId(Long id);
	
}