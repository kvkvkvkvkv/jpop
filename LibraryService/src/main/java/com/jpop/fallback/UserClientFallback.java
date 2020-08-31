package com.jpop.fallback;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.jpop.client.UserClient;
import com.jpop.dto.UserDTO;
import com.jpop.model.Users;

@Component
public class UserClientFallback implements UserClient{

	@Override
	public ResponseEntity<List<UserDTO>> getAllUsers() {
		UserDTO defaultUser = new UserDTO();
		defaultUser.setUserId(1);
		defaultUser.setUserName("Rob");
		return ResponseEntity.ok(Arrays.asList(defaultUser));
	}

	@Override
	public ResponseEntity<UserDTO> getUserById(int userId) {
		return ResponseEntity.notFound().build();
	}

	@Override
	public ResponseEntity<UserDTO> addUser(UserDTO userDTO) {
		return ResponseEntity.notFound().build();
	}

	@Override
	public ResponseEntity<Users> deleteUser(int userId) {
		return ResponseEntity.notFound().build();
	}

	@Override
	public ResponseEntity<UserDTO> updateUser(int userId, UserDTO userDTO) {
		return ResponseEntity.notFound().build();
	}

}
