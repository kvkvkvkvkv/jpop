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

import com.jpop.dto.BookDTO;
import com.jpop.model.Book;
import com.jpop.service.BookMapper;
import com.jpop.service.BookService;

@RestController
public class BookController {
	
	@Autowired
	BookService bookService;
	 
	@Autowired
	BookMapper bookMapper;
	
	@GetMapping("/books")
	public ResponseEntity<List<BookDTO>> getAllBooks() {
		return ResponseEntity.ok(bookMapper.convertListToDTO(bookService.getAllBooks()));
	}
	
	@GetMapping("/books/{book_id}")
	public ResponseEntity<BookDTO> getBookById(@PathVariable("book_id") int bookId) {
		return ResponseEntity.ok(bookMapper.convertToDTO(bookService.getBookById(bookId)));
	}
	
	@PostMapping("/books")
	public ResponseEntity<BookDTO> addBook(@RequestBody BookDTO bookDTO) {
		Book book = bookMapper.convertToEntity(bookDTO);
		Book newBook = bookService.addBook(book);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{bookId}")
				.buildAndExpand(newBook.getBookId())
				.toUri();
		
		return ResponseEntity.created(location).body(bookMapper.convertToDTO(newBook));
		
	}
	
	@DeleteMapping("/books/{book_id}")
	public ResponseEntity<Book> deleteBook(@PathVariable("book_id") int bookId) {
		bookService.deleteBook(bookId);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/books/{book_id}")
	public ResponseEntity<BookDTO> updateBook(@PathVariable("book_id") int bookId, @RequestBody BookDTO bookDTO) {
		Book book = bookMapper.convertToEntity(bookDTO);
		return ResponseEntity.ok(bookMapper.convertToDTO(bookService.updateBook(book,bookId)));
	}
}
