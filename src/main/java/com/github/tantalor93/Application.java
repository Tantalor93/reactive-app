package com.github.tantalor93;

import com.github.tantalor93.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Application {

    @Autowired
    private PersonService personService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
