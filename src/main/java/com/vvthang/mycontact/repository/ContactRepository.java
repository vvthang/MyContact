/*
 * Copyright©2017 NTT corp． All Rights Reserved．
 */
package com.vvthang.mycontact.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.vvthang.mycontact.entity.Contact;

/**
 * The Interface ContactRepository.
 */
public interface ContactRepository extends CrudRepository<Contact, Integer>{
    
    /**
     * Find contact by name
     *
     * @param q the q
     * @return the list
     */
    List<Contact> findByNameContaining(String q);

}
