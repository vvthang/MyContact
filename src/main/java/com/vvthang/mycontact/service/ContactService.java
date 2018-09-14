/*
 * Copyright©2017 NTT corp． All Rights Reserved．
 */
package com.vvthang.mycontact.service;

import java.util.List;

import com.vvthang.mycontact.entity.Contact;

/**
 * The Interface ContactService.
 */
public interface ContactService {

    /**
     * Find all contact.
     *
     * @return the iterable
     */
    Iterable<Contact> findAll();

    /**
     * Search contact.
     *
     * @param q the q
     * @return the list
     */
    List<Contact> search(String q);

    /**
     * Find contact by Id
     *
     * @param id the id
     * @return the contact
     */
    Contact findOne(int id);

    /**
     * Save contact.
     *
     * @param contact the contact
     */
    void save(Contact contact);

    /**
     * Delete contact by Id.
     *
     * @param id the id
     */
    void delete(int id);
}
