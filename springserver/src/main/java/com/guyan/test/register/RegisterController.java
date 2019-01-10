package com.guyan.test.register;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {


    @RequestMapping("/register")
    public String registerUser() {
        return "reg!!!!";
    }
}
