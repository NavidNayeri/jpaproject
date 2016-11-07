package se.plushogskolan.taskhandler.repository;

import org.springframework.data.repository.CrudRepository;

import se.plushogskolan.taskhandler.model.Issue;

public interface IssueRepository extends CrudRepository<Issue, Long>{

}