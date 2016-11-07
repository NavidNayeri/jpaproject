package se.plushogskolan.taskhandler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.plushogskolan.taskhandler.repository.TeamRepository;


@Service
public class TeamService {
	
	private final TeamRepository teamRepository;
	
	@Autowired
	public TeamService(TeamRepository teamRepository){
		this.teamRepository = teamRepository;
	}

}
