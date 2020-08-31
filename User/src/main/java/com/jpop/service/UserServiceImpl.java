package com.jpop.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jpop.exception.UserNotFoundException;
import com.jpop.model.DetailedUser;
import com.jpop.model.Users;
import com.jpop.repository.DetailedUserRepository;
import com.jpop.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	
	Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	private static final String NO_USER_FOUND_FOR_ID = "No user found for id ";

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	DetailedUserRepository detailedUserRepository;

	@Override
	public List<Users> getAllUsers() {
		userRepository.findAll().forEach(ele -> logger.info(ele.getUserName()));
		return (List<Users>) userRepository.findAll();
	}

	@Override
	public Users getUserById(int id) {
		return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(NO_USER_FOUND_FOR_ID+id));
	}

	@Override
	public Users addUser(Users user) {
		return userRepository.save(user);
	}

	@Override
	public void deleteUser(int id) {
		userRepository.delete(getUserById(id));

	}

	@Override
	public Users updateUser(Users user,int userId) {
		return userRepository.findById(userId)
				.map(element -> {
					element.setUserName(user.getUserName());
					return userRepository.save(element);
				})
				.orElseGet(() -> {
					user.setUserId(userId);
					return userRepository.save(user);
				});
	}

	@Override
	public List<DetailedUser> getAllDetailedUsers() {
		return (List<DetailedUser>) detailedUserRepository.findAll();
	}

	@Override
	public DetailedUser getDetailedUserById(int id) {
		return detailedUserRepository.findById(id).orElseThrow(() -> new UserNotFoundException(NO_USER_FOUND_FOR_ID+id));
	}

	@Override
	public DetailedUser addDetailedUser(DetailedUser user) {
		return detailedUserRepository.save(user);
	}

	@Override
	public void deleteDetailedUser(int id) {
		detailedUserRepository.deleteById(id);
		
	}

	@Override
	public DetailedUser updateDetailedUser(DetailedUser user, int userId) {
		return detailedUserRepository.findById(userId)
				.map(ele -> {
					ele.setName(user.getName());
					return detailedUserRepository.save(ele);
				})
				.orElseGet(() -> {
					user.setUserId(userId);
					return detailedUserRepository.save(user);
				});
	}
}
