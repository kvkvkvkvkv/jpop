package com.jpop.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jpop.exception.BookNotFoundException;
import com.jpop.model.Book;
import com.jpop.service.BookService;

@RestController
public class BookController {
	
	private static final String NO_BOOK_FOUND_FOR_ID = "No book found for id ";
	@Autowired
	BookService bookService;
	
	@GetMapping("/books")
	public List<Book> getAllBooks() {
		return bookService.getAllBooks();
	}
	
	@GetMapping("/books/{book_id}")
	public Book getBookById(@PathVariable("book_id") int bookId) {
		return bookService.getBookById(bookId).orElseThrow(() -> new BookNotFoundException(NO_BOOK_FOUND_FOR_ID+bookId));
	}
	
	@PostMapping("/books")
	public ResponseEntity<Object> addBook(@RequestBody Book book) {
		Book newBook = bookService.addBook(book);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{bookId}")
				.buildAndExpand(newBook.getBookId())
				.toUri();
		
		return ResponseEntity.created(location).build();
		
	}
	
	@DeleteMapping("/books/{book_id}")
	public void deleteBook(@PathVariable("book_id") int bookId) {
		Book book = bookService.getBookById(bookId).orElseThrow(()-> new BookNotFoundException(NO_BOOK_FOUND_FOR_ID+bookId));
		bookService.deleteBook(book);
	}
	
	@PutMapping("/books/{book_id}")
	public Book updateBook(@PathVariable("book_id") int bookId, @RequestBody Book book) {
		
		Book bookToUpdate = bookService.getBookById(bookId).orElseThrow(()-> new BookNotFoundException(NO_BOOK_FOUND_FOR_ID+bookId));
		bookToUpdate.setBookName(book.getBookName());
		return bookService.addBook(bookToUpdate);
	}
}
