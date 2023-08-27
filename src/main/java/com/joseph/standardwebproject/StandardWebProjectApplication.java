package com.joseph.standardwebproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class StandardWebProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(StandardWebProjectApplication.class, args);
    }

}
