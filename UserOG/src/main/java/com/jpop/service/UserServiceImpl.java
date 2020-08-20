package com.jpop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jpop.exception.UserNotFoundException;
import com.jpop.model.Users;
import com.jpop.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{


	private static final String NO_USER_FOUND_FOR_ID = "No user found for id ";

	@Autowired
	UserRepository userRepository;

	@Override
	public List<Users> getAllUsers() {
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
}
