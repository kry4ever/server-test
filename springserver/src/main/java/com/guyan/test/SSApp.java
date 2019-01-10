package com.guyan.test;

import com.guyan.test.register.RegisterController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SSApp {


    public static void main(String[] args) {
        SpringApplication.run(RegisterController.class, args);
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
