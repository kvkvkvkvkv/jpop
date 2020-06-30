package com.jpop.repository;

import org.springframework.data.repository.CrudRepository;

import com.jpop.model.User;

public interface UserRepository extends CrudRepository<User, Integer>{

}
