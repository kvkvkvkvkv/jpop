package com.jpop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.annotation.SpanTag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jpop.client.BookClient;
import com.jpop.client.UserClient;
import com.jpop.dto.BookDTO;
import com.jpop.dto.UserDTO;
import com.jpop.model.Book;
import com.jpop.model.Users;

import brave.Tracer;

@RestController
@RequestMapping("/library")
public class LibraryController {
	
	@Autowired
	BookClient bookClient;
	
	@Autowired
	UserClient userClient;
	
	@Autowired
	Tracer tracer;

	@GetMapping("/")
	public ResponseEntity<UserDTO> getUserById(@PathVariable("user_id") int userId) {
		return userClient.getUserById(userId);
	}
	
	@GetMapping("/books")
	public ResponseEntity<List<BookDTO>> getAllBooks() {
		return bookClient.getAllBooks();
	}
	
	@GetMapping("/books/{book_id}")
	public ResponseEntity<BookDTO> getBookById(@SpanTag("bookId") @PathVariable("book_id") int bookId) {
		return bookClient.getBookById(bookId);
	} 
	
	@PostMapping("/books")
	public ResponseEntity<BookDTO> addBook(@RequestBody BookDTO bookDTO) {
		return bookClient.addBook(bookDTO);
	}
	
	@DeleteMapping("/books/{book_id}")
	public ResponseEntity<Book> deleteBook(@PathVariable("book_id") int bookId) {
		return bookClient.deleteBook(bookId);
	}
	
	@PutMapping("/books/{book_id}")
	public ResponseEntity<BookDTO> updateBook(@PathVariable("book_id") int bookId, @RequestBody BookDTO bookDTO) {
		return bookClient.updateBook(bookId, bookDTO);
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<UserDTO>> getAllUsers() {
		return userClient.getAllUsers();
	}
	
	@GetMapping("/users/{user_id}")
	public ResponseEntity<UserDTO> getUsersById(@PathVariable("user_id") int userId) {
		return userClient.getUserById(userId);
	}
	
	@PostMapping("/users")
	public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO userDTO) {
		return userClient.addUser(userDTO);
	}
	
	@DeleteMapping("/users/{user_id}")
	public ResponseEntity<Users> deleteUser(@PathVariable("user_id") int userId) {
		return userClient.deleteUser(userId);
	}
	
	@PutMapping("/users/{user_id}")
	public ResponseEntity<UserDTO> updateUser(@PathVariable("user_id") int userId, @RequestBody UserDTO userDTO) {
		return userClient.updateUser(userId, userDTO);
	}
}
