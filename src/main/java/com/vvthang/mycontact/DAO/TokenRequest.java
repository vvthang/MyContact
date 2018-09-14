package com.vvthang.mycontact.DAO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenRequest {
    
    private String user;
    
    private String[] roles;
    
    private String duration;

}
