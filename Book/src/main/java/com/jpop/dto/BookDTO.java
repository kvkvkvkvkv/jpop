package com.jpop.dto;

import org.springframework.stereotype.Repository;

import lombok.Data;

@Repository
@Data
public class BookDTO {

	private Integer bookId;
	
	private String bookName;
	
}
