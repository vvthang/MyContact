/*
 * Copyright©2017 NTT corp． All Rights Reserved．
 */
package com.vvthang.mycontact.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vvthang.mycontact.DAO.AccountInfo;
import com.vvthang.mycontact.service.UserService;

/**
 * Controller for MyContact
 */
@Controller
public class ContactController {

    /** The Constant logger. */
    private static final Logger logger = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value="/", method=RequestMethod.GET)
    public String home(Model model) {
        logger.debug("IN - home()");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            model.addAttribute("user", userDetail);
        }
        logger.debug("OUT - home()");
        return "home";
    }

    @RequestMapping(value="/403", method=RequestMethod.GET)
    public String accessDenied() {
        logger.debug("IN - accessDenied()");
        logger.debug("OUT - accessDenied()");
        return "403";
    }

    @RequestMapping(value="/login", method=RequestMethod.GET)
    public String getLogin() {
        logger.debug("IN - getLogin()");
        logger.debug("OUT - getLogin()");
        return "login";
    }

    @RequestMapping(value="/logout", method=RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("IN - logout()");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        logger.debug("OUT - logout()");
        return "redirect:/";
    }

    @RequestMapping(value="/index", method=RequestMethod.GET)
    public String informationIndex(Model model) {
        logger.debug("IN - informationIndex()");
        logger.debug("OUT - informationIndex()");
        return "index";
    }

    @RequestMapping(value="/opp", method=RequestMethod.GET)
    public String opp() {
        logger.debug("IN - opp()");
        logger.debug("OUT - opp()");
        return "opp";
    }
    
    @RequestMapping(value="/signup", method=RequestMethod.GET)
    public String signUp(Model model) {
        logger.debug("IN - signUp()");
        model.addAttribute("accountInfo", new AccountInfo());
        logger.debug("OUT - signUp()");
        return "signup";
    }

    @RequestMapping(value="/register", method=RequestMethod.POST)
    public String register(@Valid AccountInfo accountInfo, BindingResult result, RedirectAttributes redirect) {
        logger.debug("IN - register()");
        if (result.hasErrors()) {
            return "/signup";
        }

        userService.saveUser(accountInfo);
        logger.debug("OUT - register()");
        return "redirect:/";
    }
    
    

}
