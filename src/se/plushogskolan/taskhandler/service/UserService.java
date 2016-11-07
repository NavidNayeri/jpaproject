package se.plushogskolan.taskhandler.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.plushogskolan.taskhandler.model.User;
import se.plushogskolan.taskhandler.repository.UserRepository;

@Service
public final class UserService {

	private final UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Transactional
	public User saveOrUpdateUser(User user) {
		return userRepository.save(user);
	}

	@Transactional
	public User setUserStatus(boolean status, Long id) {
		User user = userRepository.findOne(id);
		user.setActive(status);
		return userRepository.save(user);
	}

	public List<User> findUserByNumber(String number) {
		return userRepository.findByNumber(number);
	}

}
