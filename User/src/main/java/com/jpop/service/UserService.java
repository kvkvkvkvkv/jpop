package com.jpop.service;

import java.util.List;

import com.jpop.model.Users;

public interface UserService {
	
	public List<Users> getAllUsers();
	
	public Users getUserById(int id);
	
	public Users addUser(Users user);
	
	public void deleteUser(int id);
	
	public Users updateUser(Users user, int userId);
	
}
