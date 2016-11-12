package se.plushogskolan.taskhandler.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.plushogskolan.taskhandler.exception.ServiceException;
import se.plushogskolan.taskhandler.model.Team;
import se.plushogskolan.taskhandler.repository.TeamRepository;
import se.plushogskolan.taskhandler.repository.UserRepository;

@Service
public class TeamService {

	private final UserRepository userRepository;
	private final TeamRepository teamRepository;

	@Autowired
	public TeamService(TeamRepository teamRepository, UserRepository userRepository) {
		this.userRepository = userRepository;
		this.teamRepository = teamRepository;

	}

	@Transactional
	public Team saveOrUpdateTeam(Team team) {
		return teamRepository.save(team);
	}

	@Transactional
	public Team setTeamStatus(boolean status, Long id) {
		Team team = teamRepository.findOne(id);
		team.setTeamActive(status);
		return teamRepository.save(team);
	}

	public int getTeamSize(Long id) {
		Team teamList = teamRepository.findOne(id);
		return teamList.getUsers().size();

	}

	@Transactional
	public Team AddUserToTeam(Long userId, Long teamId) {
		Team returnTeam = teamRepository.findOne(teamId);
		if (returnTeam.getUsers().size() < 10 && userRepository.findOne(userId).getTeam() == null) {
			returnTeam.getUsers().add(userRepository.findOne(userId));
			return teamRepository.save(returnTeam);
		} else if (userRepository.findOne(userId).getTeam() != null) {
			throw new ServiceException("User is already in team!");
		} else {
			throw new ServiceException("Team is full!");
		}

	}

	public List<Team> getAllTeams() {
		Iterable<Team> team = teamRepository.findAll();
		List<Team> teamList = new ArrayList<>();
		team.forEach(t -> teamList.add(t));
		return teamList;

	}

}