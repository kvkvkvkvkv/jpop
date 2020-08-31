package com.jpop.service;

import java.util.List;

import com.jpop.model.DetailedUser;
import com.jpop.model.Users;

public interface UserService {

	public List<Users> getAllUsers();

	public Users getUserById(int id);

	public Users addUser(Users user);

	public void deleteUser(int id);

	public Users updateUser(Users user, int userId);

	public List<DetailedUser> getAllDetailedUsers();

	public DetailedUser getDetailedUserById(int id);

	public DetailedUser addDetailedUser(DetailedUser user);

	public void deleteDetailedUser(int id);

	public DetailedUser updateDetailedUser(DetailedUser user, int userId);

}
