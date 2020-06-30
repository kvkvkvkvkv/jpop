package com.jpop.service;

import java.util.List;
import java.util.Optional;

import com.jpop.model.Book;

public interface BookService {
	
	public List<Book> getAllBooks();
	
	public Optional<Book> getBookById(int id);
	
	public Book addBook(Book book);
	
	public void deleteBook(Book book);
	
	public Book updateBook(Book book);
	
}
