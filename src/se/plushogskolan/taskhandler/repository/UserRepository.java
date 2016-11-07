package se.plushogskolan.taskhandler.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import se.plushogskolan.taskhandler.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	List<User> findByNumber(String number);
	
	List<User> findByFirstNameAndLastName(String firstName, String lastName);
	
	List<User> findByFirstNameOrLastNameOrUserName(String firstName, String lastName, String userName);
	
}