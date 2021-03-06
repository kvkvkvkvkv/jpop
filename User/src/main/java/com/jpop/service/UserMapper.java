package com.jpop.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jpop.dto.DetailedUserDTO;
import com.jpop.dto.UserDTO;
import com.jpop.model.DetailedUser;
import com.jpop.model.Users;

@Service
public class UserMapper {

	@Autowired
	ModelMapper modelMapper;
	
	public UserDTO convertToDTO(Users user) {
	    return modelMapper.map(user, UserDTO.class);
	}
	
	public Users convertToEntity(UserDTO userDTO) {
	    return modelMapper.map(userDTO, Users.class);
	}
	
	public List<UserDTO> convertListToDTO(List<Users> users) {
		return users.stream().map(this::convertToDTO).collect(Collectors.toList());
	}
	
	public DetailedUserDTO convertDetailedUserToDTO(DetailedUser user) {
	    return modelMapper.map(user, DetailedUserDTO.class);
	}
	
	public DetailedUser convertDetailedUserDtoToEntity(DetailedUserDTO userDTO) {
	    return modelMapper.map(userDTO, DetailedUser.class);
	}
	
	public List<DetailedUserDTO> convertDetailedUserListToDTO(List<DetailedUser> users) {
		return users.stream().map(this::convertDetailedUserToDTO).collect(Collectors.toList());
	}
}
