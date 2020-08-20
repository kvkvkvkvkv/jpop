package com.jpop.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jpop.dto.BookDTO;
import com.jpop.model.Book;

@Service
public class BookMapper {

	@Autowired
	ModelMapper modelMapper;
	
	public BookDTO convertToDTO(Book book) {
	    return modelMapper.map(book, BookDTO.class);
	}
	
	public Book convertToEntity(BookDTO bookDTO) {
	    return modelMapper.map(bookDTO, Book.class);
	}
	
	public List<BookDTO> convertListToDTO(List<Book> books) {
		return books.stream().map(this::convertToDTO).collect(Collectors.toList());
	}
	
}
