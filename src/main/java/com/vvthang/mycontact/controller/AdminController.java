package com.vvthang.mycontact.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vvthang.mycontact.DAO.AccountInfo;
import com.vvthang.mycontact.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    
    /** The Constant logger. */
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    
    @RequestMapping(value="/", method=RequestMethod.GET)
    public String admin() {
        logger.debug("IN - admin()");
        logger.debug("OUT - admin()");
        return "admin";
    }
    
    
    
}
