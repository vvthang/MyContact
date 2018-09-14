package com.vvthang.mycontact.service;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vvthang.mycontact.DAO.AccountInfo;
import com.vvthang.mycontact.entity.User;
import com.vvthang.mycontact.repository.RoleRepository;
import com.vvthang.mycontact.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
    
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private UserRepository userRepository;

    @Override
    public void saveUser(AccountInfo accountInfo) {
        
        User user = new User();
        user.setEmail(accountInfo.getEmail());
        user.setPassword(passwordEncoder.encode(accountInfo.getPassword()));
        HashSet<com.vvthang.mycontact.entity.Role> roles = new HashSet<>();
        
        if(accountInfo.getRole().equals("1")){
            roles.add(roleRepository.findByName("ROLE_MEMBER"));
        }else if(accountInfo.getRole().equals("2")){
            roles.add(roleRepository.findByName("ROLE_MEMBER"));
            roles.add(roleRepository.findByName("ROLE_ADMIN"));
        }
        
        user.setRoles(roles);
        userRepository.save(user);
        
    }
    
}
