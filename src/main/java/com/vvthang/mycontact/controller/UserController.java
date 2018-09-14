/*
 * Copyright©2017 NTT corp． All Rights Reserved．
 */
package com.vvthang.mycontact.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vvthang.mycontact.entity.Contact;
import com.vvthang.mycontact.service.ContactService;

@Controller
@RequestMapping("/user")
public class UserController {
    
    /** The Constant logger. */
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    
    /** The contact service. */
    @Autowired
    private ContactService contactService;
    
    @RequestMapping(value="/", method=RequestMethod.GET)
    public String home() {
        return "a";
    }
    
    @RequestMapping(value="/list", method=RequestMethod.GET)
    public String getList(Model model, HttpServletRequest request) {
        logger.debug("IN - getList()");
        logger.debug("HEADER=" + request.getHeader("authorization"));
        model.addAttribute("contacts", contactService.findAll());
        logger.debug("OUT - getList()");
        return "list";
    }

    @RequestMapping(value="/create", method=RequestMethod.GET)
    public String create(Model model) {
        model.addAttribute("contact", new Contact());
        return "form";
    }

    @RequestMapping(value="/save", method=RequestMethod.POST)
    public String save(@Valid Contact contact, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return "/form";
        }

        contactService.save(contact);
        redirect.addFlashAttribute("success", "Saved contact successfully!");
        return "redirect:/list";

    }

    @RequestMapping(value="/{id}/edit", method=RequestMethod.GET)
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("contact", contactService.findOne(id));
        return "form";
    }

    @RequestMapping(value="/{id}/delete", method=RequestMethod.GET)
    public String delete(@PathVariable int id, RedirectAttributes redirect) {
        contactService.delete(id);
        redirect.addFlashAttribute("success", "Deleted contact successfully!");

        return "redirect:/list";
    }

    @RequestMapping(value="/search", method=RequestMethod.GET)
    public String search(@RequestParam("q") String q, Model model) {
        if (q.equals("")) {
            return "redirect:/";
        }

        model.addAttribute("contacts", contactService.search(q));
        return "list";
    }
    

}
