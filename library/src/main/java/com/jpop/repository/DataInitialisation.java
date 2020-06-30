package com.jpop.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.jpop.model.Book;
import com.jpop.model.User;

@Component
public class DataInitialisation implements ApplicationRunner{

	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		Book book1 = new Book();
		book1.setBookName("Sapiens");
		Book book2 = new Book();
		book2.setBookName("Choosing");
		
		bookRepository.save(book1);
		bookRepository.save(book2);
		
		User user1 = new User();
		user1.setUserName("Rob");
		User user2 = new User();
		user2.setUserName("Robert");
		
		userRepository.save(user1);
		userRepository.save(user2);
		
	}
	

}
