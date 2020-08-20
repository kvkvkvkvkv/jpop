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

import com.jpop.dto.UserDTO;
import com.jpop.model.Users;
import com.jpop.service.UserMapper;
import com.jpop.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserMapper userMapper;
	
	@GetMapping("/users")
	public ResponseEntity<List<UserDTO>> getAllUsers() {
		return ResponseEntity.ok(userMapper.convertListToDTO(userService.getAllUsers()));
	}
	
	@GetMapping("/users/{user_id}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable("user_id") int userId) {
		return ResponseEntity.ok(userMapper.convertToDTO(userService.getUserById(userId)));
	}
	
	@PostMapping("/users")
	public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO userDTO) {
		Users user = userMapper.convertToEntity(userDTO);
		Users newUser = userService.addUser(user);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{userId}")
				.buildAndExpand(newUser.getUserId())
				.toUri();
		
		return ResponseEntity.created(location).body(userMapper.convertToDTO(newUser));
		
	}
	
	@DeleteMapping("/users/{user_id}")
	public ResponseEntity<Users> deleteUser(@PathVariable("user_id") int userId) {
		userService.deleteUser(userId);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/users/{user_id}")
	public ResponseEntity<UserDTO> updateUser(@PathVariable("user_id") int userId, @RequestBody UserDTO userDTO) {
		Users user = userMapper.convertToEntity(userDTO);
		return ResponseEntity.ok(userMapper.convertToDTO(userService.updateUser(user,userId)));
	}
}
