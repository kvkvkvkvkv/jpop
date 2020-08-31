package com.jpop.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jpop.model.DetailedUser;

@Repository
public interface DetailedUserRepository extends CrudRepository<DetailedUser, Integer>{

}
