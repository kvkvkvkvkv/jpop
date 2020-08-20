package com.jpop.exception;

import lombok.Data;

@Data
public class ExceptionResponse {

	private String errorMessage;
	
	private String description;

	public ExceptionResponse(String errorMessage, String description) {
		super();
		this.errorMessage = errorMessage;
		this.description = description;
	}
		
}
