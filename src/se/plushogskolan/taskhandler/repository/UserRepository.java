package se.plushogskolan.taskhandler.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import se.plushogskolan.taskhandler.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	List<User> findByNumber(String number);
	
	List<User> findByFirstName(String firstName);
	
	List<User> findByLastName(String lastName);
	
	List<User> findByUsername(String username);
	
	List<User> findByLastNameAndUsername(String lastName, String username);
	
	List<User> findByFirstNameAndUsername(String firstName, String username);
	
	List<User> findByFirstNameAndLastName(String firstName, String lastName);

	List<User> findByFirstNameAndLastNameAndUsername(String firstName, String lastName, String username);
	
	List<User> findByFirstNameOrLastName(String firstName, String lastName);
	
	List<User> findByLastNameOrUsername(String lastName, String username);
	
	List<User> findByFirstNameOrUsername(String firstName, String username);
	
	List<User> findByFirstNameOrLastNameOrUsername(String firstName, String lastName, String username);
	
	List<User> findByTeamId(Long id);
	
}