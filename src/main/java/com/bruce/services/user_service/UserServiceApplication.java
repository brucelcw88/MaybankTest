package com.bruce.services.user_service;


import com.bruce.services.user_service.services.impl.UserServiceImpl;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.bruce.services"})
public class UserServiceApplication {

    private static final Logger log = LoggerFactory.getLogger(UserServiceApplication.class);
    private static String applicationName;
    @Value("${spring.application.name}")
    private String applicationNameValue; // Non-static field to capture the property

    @PostConstruct
    public void init() {
        // Main is using static, value need to be assign when retrieving from application file
        applicationName = applicationNameValue;
    }

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
        log.trace(String.format(">>>  Application %s succeesully started...  <<<", applicationName.toUpperCase()));
    }

}


