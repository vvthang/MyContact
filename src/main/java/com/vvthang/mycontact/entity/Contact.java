/*
 * Copyright©2017 NTT corp． All Rights Reserved．
 */
package com.vvthang.mycontact.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;


/**
 * The Class Contact.
 */
@Entity
@Getter
@Setter
@Table(name = "contact")
public class Contact implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -5365071578427589079L;

    /** The id of the Contact. */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    /** The name of the Contact. */
    @NotEmpty
    @Column(name = "name", nullable = false)
    private String name;

    /** The email of the Contact. */
    @Email
    @Column(name = "email")
    private String email;

    /** The phone of the Contact. */
    @Column(name = "phone")
    private String phone;

    /**
     * Instantiates a new contact.
     *
     * @param id the id contact
     * @param name the name contact
     * @param email the email contact
     * @param phone the phone contact
     */
    public Contact(int id, String name, String email, String phone) {
        super();
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    /**
     * Instantiates a new contact.
     */
    public Contact() {
        super();
    }



}
