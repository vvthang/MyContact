package com.vvthang.mycontact.repository;

import org.springframework.data.repository.CrudRepository;

import com.vvthang.mycontact.entity.Role;

public interface RoleRepository extends CrudRepository<Role, Integer>{
    
    Role findByName(String name);
    
}
