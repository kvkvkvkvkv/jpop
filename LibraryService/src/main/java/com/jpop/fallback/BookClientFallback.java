package com.jpop.fallback;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.jpop.client.BookClient;
import com.jpop.dto.BookDTO;
import com.jpop.model.Book;

@Component
public class BookClientFallback implements BookClient{

	@Override
	public ResponseEntity<List<BookDTO>> getAllBooks() {
		BookDTO defaultBook = new BookDTO();
		defaultBook.setBookId(1);
		defaultBook.setBookName("Sapiens");
		return ResponseEntity.ok(Arrays.asList(defaultBook));
	}

	@Override
	public ResponseEntity<BookDTO> getBookById(int bookId) {
		return ResponseEntity.notFound().build();
	}

	@Override
	public ResponseEntity<BookDTO> addBook(BookDTO bookDTO) {
		return ResponseEntity.notFound().build();
	}

	@Override
	public ResponseEntity<Book> deleteBook(int bookId) {
		return ResponseEntity.notFound().build();
	}

	@Override
	public ResponseEntity<BookDTO> updateBook(int bookId, BookDTO bookDTO) {
		return ResponseEntity.notFound().build();
	}

}
