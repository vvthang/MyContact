/*
 * Copyright©2017 NTT corp． All Rights Reserved．
 */
package com.vvthang;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The Class MyContactApplication.
 */
@SpringBootApplication
public class MyContactApplication {

    /** The Constant logger. */
    private static final Logger logger = LoggerFactory.getLogger(MyContactApplication.class);

    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
        logger.debug("IN - main()");
        SpringApplication.run(MyContactApplication.class, args);
        logger.debug("OUT - main()");
    }
}
