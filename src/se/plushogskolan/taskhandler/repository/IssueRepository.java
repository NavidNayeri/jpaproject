package se.plushogskolan.taskhandler.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import se.plushogskolan.taskhandler.model.Issue;

public interface IssueRepository extends CrudRepository<Issue, Long>{

	List<Issue> findByWorkItemId(Long id);
	
}