package com.jpop.service;

import java.util.List;

import com.jpop.model.Book;

public interface BookService {
	
	public List<Book> getAllBooks();
	
	public Book getBookById(int id);
	
	public Book addBook(Book book);
	
	public void deleteBook(int id);
	
	public Book updateBook(Book book, int bookId);
	
}
