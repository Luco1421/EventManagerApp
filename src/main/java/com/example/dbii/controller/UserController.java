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

    @GetMapping({"/", "/home"})
    public String home() {
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping({"/admin"})
    public String adminView() { return "adminOptions"; }

    @PostMapping("/signup")
    public String signup(@RequestParam("name") String name,
                               @RequestParam("last_name") String lastName,
                               @RequestParam("phone") Long phone,
                               @RequestParam("email") String email,
                               @RequestParam("password") String password,
                               Model model) {
        Long id = userService.registerUser(name, lastName, phone, email, password, false);
        if (id != -1) return "redirect:/login";
        else model.addAttribute("registrationError", "Ya existe una cuenta asociada a este correo");
        return "login";
    }

    @PostMapping("/log")
    public String log(@RequestParam("email") String email,
                               @RequestParam("password") String password,
                               Model model) {
        int code = userService.processLogin(email, password);
        if (code == 100) return "redirect:/catalogView";
        if (code == 50) model.addAttribute("loginError", "Contraseña incorrecta");
        else model.addAttribute("loginError", "Correo incorrecto");
        return "login";
    }
}
