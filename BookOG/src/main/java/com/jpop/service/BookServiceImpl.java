package com.jpop.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jpop.exception.BookNotFoundException;
import com.jpop.model.Book;
import com.jpop.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService{

	private static final String NO_BOOK_FOUND_FOR_ID = "No book found for id ";
	
	Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

	@Autowired
	BookRepository bookRepository;

	@Override
	public List<Book> getAllBooks() {
		return (List<Book>) bookRepository.findAll();
	}

	@Override
	public Book getBookById(int id) {
		return bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(NO_BOOK_FOUND_FOR_ID+id));
	}

	@Override
	public Book addBook(Book book) {
		return bookRepository.save(book);
	}

	@Override
	public void deleteBook(int id) {	
		bookRepository.delete(getBookById(id));
	}

	@Override
	public Book updateBook(Book book, int bookId) {
		return bookRepository.findById(bookId)
				.map(element -> {
					element.setBookName(book.getBookName());
					return bookRepository.save(element);})
				.orElseGet(() -> { 
					book.setBookId(bookId);
					return addBook(book);
				});
	}

}
