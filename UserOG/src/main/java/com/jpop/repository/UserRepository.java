package com.jpop.repository;

import org.springframework.data.repository.CrudRepository;

import com.jpop.model.Users;

public interface UserRepository extends CrudRepository<Users, Integer>{

}
