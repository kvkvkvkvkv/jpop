package com.jpop.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.jpop.dto.BookDTO;
import com.jpop.fallback.BookClientFallback;
import com.jpop.model.Book;

@FeignClient(name = "BOOK", fallback = BookClientFallback.class)
@Component
public interface BookClient {
	
	@GetMapping("/books")
	public ResponseEntity<List<BookDTO>> getAllBooks(); 
	
	@GetMapping("/books/{book_id}")
	public ResponseEntity<BookDTO> getBookById(@PathVariable("book_id") int bookId); 
	
	@PostMapping("/books")
	public ResponseEntity<BookDTO> addBook(@RequestBody BookDTO bookDTO); 
	
	@DeleteMapping("/books/{book_id}")
	public ResponseEntity<Book> deleteBook(@PathVariable("book_id") int bookId);
	
	@PutMapping("/books/{book_id}")
	public ResponseEntity<BookDTO> updateBook(@PathVariable("book_id") int bookId, @RequestBody BookDTO bookDTO);
}

