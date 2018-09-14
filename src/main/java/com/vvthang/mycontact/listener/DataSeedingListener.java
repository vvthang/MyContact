/*
 * Copyright©2017 NTT corp． All Rights Reserved．
 */
package com.vvthang.mycontact.listener;

import java.util.HashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.vvthang.mycontact.common.Constant.Role;
import com.vvthang.mycontact.entity.User;
import com.vvthang.mycontact.repository.RoleRepository;
import com.vvthang.mycontact.repository.UserRepository;

@Component("com.vvthang")
public class DataSeedingListener implements ApplicationListener<ContextRefreshedEvent> {

    /** The Constant logger. */
    private static final Logger logger = LoggerFactory.getLogger(DataSeedingListener.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent arg0) {
        logger.debug("IN - onApplicationEvent()");

        if (roleRepository.findByName(Role.ROLE_ADMIN) == null) {
            roleRepository.save(new com.vvthang.mycontact.entity.Role(Role.ROLE_ADMIN));
        }

        if (roleRepository.findByName(Role.ROLE_MEMBER) == null) {
            roleRepository.save(new com.vvthang.mycontact.entity.Role(Role.ROLE_MEMBER));
        }

        // Admin account
        if (userRepository.findByEmail("admin@gmail.com") == null) {
            User admin = new User();
            admin.setEmail("admin@gmail.com");
            admin.setPassword(passwordEncoder.encode("123456"));
            HashSet<com.vvthang.mycontact.entity.Role> roles = new HashSet<>();
            roles.add(roleRepository.findByName("ROLE_ADMIN"));
            roles.add(roleRepository.findByName("ROLE_MEMBER"));
            admin.setRoles(roles);
            userRepository.save(admin);
        }

        // Member account
        if (userRepository.findByEmail("member@gmail.com") == null) {
            User user = new User();
            user.setEmail("member@gmail.com");
            user.setPassword(passwordEncoder.encode("123456"));
            HashSet<com.vvthang.mycontact.entity.Role> roles = new HashSet<>();
            roles.add(roleRepository.findByName("ROLE_MEMBER"));
            user.setRoles(roles);
            userRepository.save(user);
        }

        logger.debug("OUT - onApplicationEvent()");
    }

}
