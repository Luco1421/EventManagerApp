package com.example.dbii.controller;

import com.example.dbii.service.UserEService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private UserEService userService;

    @GetMapping("login")
    public String login() { return "login"; }

    @GetMapping({"/", "/home"})
    public String home() { return "home"; }

    @PostMapping("/rsignup")
    public String registerUser(@RequestParam("name") String name,
                               @RequestParam("last_name") String lastName,
                               @RequestParam("phone") Long phone,
                               @RequestParam("email") String email,
                               @RequestParam("password") String password,
                               Model model) {
        try {
            userService.registerUser(name, lastName, phone, email, password);
            return "redirect:/login";
        } catch (Exception e) {
            model.addAttribute("registrationError", e.getMessage());
            return "redirect:/login";
        }
    }

    @PostMapping("/rlogin")
    public String processLogin(@RequestParam("email") String email,
                               @RequestParam("password") String password,
                               Model model) {
        int code = userService.processLogin(email, password);
        if (code == 100) return "redirect:/home";
        if (code == 50) model.addAttribute("loginError", "Contraseña incorrecta");
        else model.addAttribute("loginError", "Correo incorrecto");
        return "login";
    }
}
