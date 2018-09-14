/*
 * Copyright©2017 NTT corp． All Rights Reserved．
 */
package com.vvthang.mycontact.service;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vvthang.mycontact.entity.Role;
import com.vvthang.mycontact.entity.User;
import com.vvthang.mycontact.repository.UserRepository;

/**
 * The Class UserDetailsServiceImpl.
 */
@Service()
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        logger.debug("IN - loadUserByUsername(), email={}", userName);
        
        User user = userRepository.findByEmail(userName);
        if (user == null) {
            throw new UsernameNotFoundException("User not found aasssss.");
        }

        Set<GrantedAuthority> auth = new HashSet<>();
        Set<Role> roles = user.getRoles();
        for (Role role : roles) {
            auth.add(new SimpleGrantedAuthority(role.getName()));
            logger.debug("ROLE=" +new SimpleGrantedAuthority(role.getName()).toString());
        }

        logger.debug("OUT - loadUserByUsername()");
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), auth);
    }

}
