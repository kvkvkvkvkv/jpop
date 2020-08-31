package com.jpop.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.jpop.dto.UserDTO;
import com.jpop.fallback.UserClientFallback;
import com.jpop.model.Users;

@FeignClient(name = "USER", fallback = UserClientFallback.class)
@Component
public interface UserClient {

	@GetMapping(value = "/users/produces", produces = "application/jpop-v1+json")
	public ResponseEntity<List<UserDTO>> getAllUsers();
	
	@GetMapping(value = "/users/{user_id}/produces", produces = "application/jpop-v1+json")
	public ResponseEntity<UserDTO> getUserById(@PathVariable("user_id") int userId);
	
	@PostMapping(value = "/users/produces", produces = "application/jpop-v1+json")
	public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO userDTO);
	
	@DeleteMapping(value = "/users/{user_id}/produces", produces = "application/jpop-v1+json")
	public ResponseEntity<Users> deleteUser(@PathVariable("user_id") int userId);
	
	@PutMapping(value = "/users/{user_id}/produces", produces = "application/jpop-v1+json")
	public ResponseEntity<UserDTO> updateUser(@PathVariable("user_id") int userId, @RequestBody UserDTO userDTO);
}
