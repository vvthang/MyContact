package com.vvthang.mycontact.repository;

import org.springframework.data.repository.CrudRepository;

import com.vvthang.mycontact.entity.User;

public interface UserRepository extends CrudRepository<User, Integer>{
    
    User findByEmail(String email);
    
}
