package com.jpop.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jpop.model.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer>{
	
}
