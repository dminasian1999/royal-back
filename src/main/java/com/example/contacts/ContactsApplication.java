package com.example.contacts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the Spring Boot application.  The {@code @SpringBootApplication}
 * annotation enables component scanning, auto–configuration and allows the
 * application to run as a Java program.  When {@link SpringApplication#run}
 * is invoked, Spring Boot will bootstrap the application context and start the
 * embedded web server.
 */
@SpringBootApplication
public class ContactsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContactsApplication.class, args);
    }
}