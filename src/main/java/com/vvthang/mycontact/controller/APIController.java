package com.vvthang.mycontact.controller;


import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vvthang.mycontact.entity.Contact;
import com.vvthang.mycontact.service.ContactService;


@RestController
@RequestMapping("/api")
public class APIController {
    
    private static final Logger logger = LoggerFactory.getLogger(APIController.class);
    
    @Autowired
    private ContactService contactService;
    
    @RequestMapping(value="/contact/list")
    public ResponseEntity<List<Contact>> getListContact(){
        
        List<Contact> contacts = (List<Contact>) contactService.findAll();
        
        return new ResponseEntity<List<Contact>>(contacts, HttpStatus.OK);
        
    }
    
    
    // API Start thread
    @RequestMapping(value = "/thread", method = RequestMethod.POST)
    public ResponseEntity<String> postThead(HttpServletRequest request){
        
        String threadName1 = "thread1";
        String threadName2 = "thread2";
        String threadName3 = "thread3";
        
        RunableProcess thread1 = BuildThread(threadName1);
        RunableProcess thread2 = BuildThread(threadName2);
        RunableProcess thread3 = BuildThread(threadName3);
        thread1.start();
        thread2.start();
        thread3.start();
        
        return new ResponseEntity<String>(HttpStatus.OK);
    }
    
    // API Stop a thread
    @RequestMapping(value = "/stopthread", method = RequestMethod.POST)
    public ResponseEntity<String> stopThead(HttpServletRequest request){
        
        String parameter = request.getParameter("threadname");
        if(parameter != null ){
            StopThreadByName(parameter);
        }
        
        return new ResponseEntity<String>(HttpStatus.OK);
    }
    
    
    private void StopThreadByName(String name){
        System.out.println("Start-StopThreadByName()");
        Set<Thread> setOfThread = Thread.getAllStackTraces().keySet();
      //Iterate over set to find yours
        for(Thread thread : setOfThread){
            if(thread.getName()==name){
                thread.interrupt();
            }
        }
        System.out.println("END-StopThreadByName()");
    }
    
    private boolean checkThread(String name){
        Set<Thread> setOfThread = Thread.getAllStackTraces().keySet();
        //Iterate over set to find yours
          for(Thread thread : setOfThread){
              if(thread.getName() == name){
                  return true;
              }
          }
          return false;
    }
    
    
    private RunableProcess BuildThread(String name){
        RunableProcess thread = null;
        if(!checkThread(name)){
            thread = new RunableProcess(name);
        }
        return thread;
    }

}
