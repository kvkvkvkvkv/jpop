package com.jpop.service;

import java.util.List;
import java.util.Optional;

import com.jpop.model.User;

public interface UserService {
	
	public List<User> getAllUsers();
	
	public Optional<User> getUserById(int id);
	
	public User addUser(User user);
	
	public void deleteUser(User user);
	
	public User updateUser(User user);
	
}
