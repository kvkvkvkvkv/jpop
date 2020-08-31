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

import com.jpop.dto.DetailedUserDTO;
import com.jpop.dto.UserDTO;
import com.jpop.model.DetailedUser;
import com.jpop.model.Users;
import com.jpop.service.UserMapper;
import com.jpop.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserMapper userMapper;
	
	@GetMapping(value = "/users/produces", produces = "application/jpop-v1+json")
	public ResponseEntity<List<UserDTO>> getAllUsers() {
		return ResponseEntity.ok(userMapper.convertListToDTO(userService.getAllUsers()));
	}
	
	@GetMapping(value = "/users/produces", produces = "application/jpop-v2+json")
	public ResponseEntity<List<DetailedUserDTO>> getAllDetailedUsers() {
		return ResponseEntity.ok(userMapper.convertDetailedUserListToDTO(userService.getAllDetailedUsers()));
	}
	
	
	@GetMapping(value = "/users/{user_id}/produces", produces = "application/jpop-v1+json")
	public ResponseEntity<UserDTO> getUserById(@PathVariable("user_id") int userId) {
		return ResponseEntity.ok(userMapper.convertToDTO(userService.getUserById(userId)));
	}
	
	@GetMapping(value = "/users/{user_id}/produces", produces = "application/jpop-v2+json")
	public ResponseEntity<DetailedUserDTO> getDetailedUserById(@PathVariable("user_id") int userId) {
		return ResponseEntity.ok(userMapper.convertDetailedUserToDTO(userService.getDetailedUserById(userId)));
	}
	
	@PostMapping(value = "/users/produces", produces = "application/jpop-v1+json")
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
	
	@PostMapping(value = "/users/produces", produces = "application/jpop-v2+json")
	public ResponseEntity<DetailedUserDTO> addDetailedUser(@RequestBody DetailedUserDTO userDTO) {
		DetailedUser user = userMapper.convertDetailedUserDtoToEntity(userDTO);
		DetailedUser newUser = userService.addDetailedUser(user);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{userId}")
				.buildAndExpand(newUser.getUserId())
				.toUri();
		
		return ResponseEntity.created(location).body(userMapper.convertDetailedUserToDTO(newUser));
		
	}
	
	@DeleteMapping(value = "/users/{user_id}/produces", produces = "application/jpop-v1+json")
	public ResponseEntity<Users> deleteUser(@PathVariable("user_id") int userId) {
		userService.deleteUser(userId);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping(value = "/users/{user_id}/produces", produces = "application/jpop-v2+json")
	public ResponseEntity<DetailedUser> deleteDetailedUser(@PathVariable("user_id") int userId) {
		userService.deleteDetailedUser(userId);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/users/{user_id}/produces", produces = "application/jpop-v1+json")
	public ResponseEntity<UserDTO> updateUser(@PathVariable("user_id") int userId, @RequestBody UserDTO userDTO) {
		Users user = userMapper.convertToEntity(userDTO);
		return ResponseEntity.ok(userMapper.convertToDTO(userService.updateUser(user,userId)));
	}
	
	@PutMapping(value = "/users/{user_id}/produces", produces = "application/jpop-v2+json")
	public ResponseEntity<DetailedUserDTO> updateDetailedUser(@PathVariable("user_id") int userId, @RequestBody DetailedUserDTO userDTO) {
		DetailedUser user = userMapper.convertDetailedUserDtoToEntity(userDTO);
		return ResponseEntity.ok(userMapper.convertDetailedUserToDTO(userService.updateDetailedUser(user,userId)));
	}
	
	
}
