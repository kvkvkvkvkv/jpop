package com.jpop.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jpop.dto.User;
import com.jpop.exception.UserNotFoundException;
import com.jpop.service.UserService;

@RestController
public class UserController {
	
	private static final String NO_USER_FOUND_FOR_ID = "No user found for id ";
	@Autowired
	UserService userService;
	
	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}
	
	@GetMapping("/users/{user_id}")
	public User getUserById(@PathVariable("user_id") int userId) {
		return userService.getUserById(userId).orElseThrow(() -> new UserNotFoundException(NO_USER_FOUND_FOR_ID+userId));
	}
	
	@PostMapping("/users")
	public ResponseEntity<Object> addUser(@RequestBody User user) {
		User newUser = userService.addUser(user);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{userId}")
				.buildAndExpand(newUser.getUserId())
				.toUri();
		
		return ResponseEntity.created(location).build();
		
	}
	
	@DeleteMapping("/users/{user_id}")
	public void deleteUser(@PathVariable("user_id") int userId) {
		User user = userService.getUserById(userId).orElseThrow(()-> new UserNotFoundException(NO_USER_FOUND_FOR_ID+userId));
		userService.deleteUser(user);
	}
	
	@PutMapping("/users/{user_id}")
	public User updateUser(@PathVariable("user_id") int userId, @RequestBody User user) {
		
		User userToUpdate = userService.getUserById(userId).orElseThrow(()-> new UserNotFoundException(NO_USER_FOUND_FOR_ID+userId));
		userToUpdate.setUserName(user.getUserName());
		return userService.addUser(userToUpdate);
	}
}
