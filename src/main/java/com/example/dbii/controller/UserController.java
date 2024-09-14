package com.example.dbii.controller;

import com.example.dbii.service.UserEService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @Autowired
    UserEService userEService;

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }

    @GetMapping({"/", "/home"})
    public String getHome() {
        return "home";
    }



}
