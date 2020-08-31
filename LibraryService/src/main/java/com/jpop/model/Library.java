package com.jpop.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "Library")
public class Library {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int libraryId;
	
	private String libraryName;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn
	private List<Users> user;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn
	private List<Book> book;
}
