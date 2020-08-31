package com.jpop.model;

import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class Name {
	
	private String firstName;
	
	private String lastName;
}
