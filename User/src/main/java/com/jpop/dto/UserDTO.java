package com.jpop.dto;

import org.springframework.stereotype.Repository;

import lombok.Data;

@Repository
@Data
public class UserDTO {

	private Integer userId;
	
	private String userName;
}

