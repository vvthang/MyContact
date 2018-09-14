/*
 * Copyright©2017 NTT corp． All Rights Reserved．
 */
package com.vvthang.mycontact.DAO;

import lombok.Getter;
import lombok.Setter;

/**
 * The Class AccountInfo.
 */
@Getter
@Setter
public class AccountInfo {
    
    public AccountInfo(){}

    public AccountInfo(String name, String password) {
        this.name = name;
        this.password = password;
    }


    /** The name. */
    private String name;
    
    /** The email. */
    private String email;
    
    /** The password. */
    private String password;
    
    /** The role. */
    private String role;

}
