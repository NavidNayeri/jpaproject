package se.plushogskolan.taskhandler.repository;

import org.springframework.data.repository.CrudRepository;

import se.plushogskolan.taskhandler.model.Team;

public interface TeamRepository extends CrudRepository<Team, Long> {
	
}