package se.plushogskolan.taskhandler.service;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import se.plushogskolan.taskhandler.assets.WorkItemStatus;
import se.plushogskolan.taskhandler.exception.ServiceException;
import se.plushogskolan.taskhandler.model.User;
import se.plushogskolan.taskhandler.model.WorkItem;
import se.plushogskolan.taskhandler.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public User saveOrUpdateUser(User user) {
		if (user.getUserName().length() > 9) {
			return userRepository.save(user);
		} else {
			throw new ServiceException("Username to short!");
		}
	}

	@Transactional
	public User setUserStatus(boolean active, Long id) {
		User user = userRepository.findOne(id);
		if (!active) {
			Collection<WorkItem> workItems = user.getWorkItems();
			for (WorkItem workItem : workItems) {
				workItem.setStatus(WorkItemStatus.UNSTARTED);
			}
			user.setWorkItems(workItems);
		}
		user.setActive(active);
		return userRepository.save(user);
	}
	
	public User findOne(Long id) {
		return userRepository.findOne(id);
	}
	
	public List<User> findUserByNumber(String number) {
		return userRepository.findByNumber(number);
	}

	public List<User> findUserByFirstName(String firstName) {
		return userRepository.findByFirstName(firstName);
	}

	public List<User> findUserByLastName(String lastName) {
		return userRepository.findByLastName(lastName);
	}

	public List<User> findUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public List<User> findUserByLastNameAndUsername(String lastName, String username) {
		return userRepository.findByLastNameAndUsername(lastName, username);
	}

	public List<User> findUserByFirstNameAndUsername(String firstName, String username) {
		return userRepository.findByFirstNameAndUsername(firstName, username);
	}

	public List<User> findUserByFirstNameAndLastName(String firstName, String lastName) {
		return userRepository.findByFirstNameAndLastName(firstName, lastName);
	}

	public List<User> findUserByFirstNameAndLastNameAndUsername(String firstName, String lastName, String username) {
		return userRepository.findByFirstNameAndLastNameAndUsername(firstName, lastName, username);
	}

	public List<User> findUserByFirstNameOrLastName(String firstName, String lastName) {
		return userRepository.findByFirstNameOrLastName(firstName, lastName);
	}

	public List<User> findUserByLastNameOrUsername(String lastName, String username) {
		return userRepository.findByLastNameOrUsername(lastName, username);
	}

	public List<User> findUserByFirstNameOrUsername(String firstName, String username) {
		return userRepository.findByFirstNameOrUsername(firstName, username);
	}

	public List<User> findUserByFirstNameOrLastNameOrUsername(String firstName, String lastName, String username) {
		return userRepository.findByFirstNameOrLastNameOrUsername(firstName, lastName, username);
	}

	public List<User> findUserByTeam(Long id) {
		return userRepository.findByTeamId(id);
	}
	
	public Page<User> findAllUsingPage(Pageable page){
		return userRepository.findAll(page);
	}

}
