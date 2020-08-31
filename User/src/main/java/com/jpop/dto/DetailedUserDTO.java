package com.jpop.dto;

import org.springframework.stereotype.Repository;

import com.jpop.model.Name;

import lombok.Data;

@Repository
@Data
public class DetailedUserDTO {

	private Integer userId;
	
	private Name name;

}

