package com.example.dbii.controller;

import com.example.dbii.entity.Salon;
import com.example.dbii.entity.UserE;
import com.example.dbii.service.CharacteristicService;
import com.example.dbii.service.UserEService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

@Controller
public class UserController {

    @Autowired
    private UserEService userService;
    @Autowired
    private CharacteristicService characteristicService;

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

    @GetMapping({"/employee"})
    public String employee() { return "employeeView"; }

    @GetMapping({"/addEmployee"})
    public String addEmployee() { return "addEmployee"; }

    @GetMapping({"/editEmployee"})
    public String editEmployee() { return "editEmployee"; }

    @GetMapping({"/deleteEmployee"})
    public String deleteEmployee() { return "deleteEmployee"; }

    @GetMapping("/query")
    public String query() { return "queryView"; }

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

    @PostMapping("/signupEmployee")
    public String signupEmployee(@RequestParam("name") String name,
                         @RequestParam("last_name") String lastName,
                         @RequestParam("phone") Long phone,
                         @RequestParam("email") String email,
                         @RequestParam("password") String password,
                         Model model) {
        Long id = userService.registerUser(name, lastName, phone, email, password, true);
        if (id != -1) return "redirect:/employee";
        else model.addAttribute("registrationError", "Ya existe una cuenta asociada a este correo");
        return "addEmployee";
    }

    @PostMapping("/log")
    public String log(@RequestParam("email") String email,
                      @RequestParam("password") String password,
                      HttpSession session,
                      Model model) {
        UserE user = userService.getUserByEmail(email);
        session.setAttribute("user", user);
        int code = userService.processLogin(email, password);
        if (code == 100) return (userService.isUserEmployee(email))?"redirect:/admin":"redirect:/catalogView";
        if (code == 50) model.addAttribute("loginError", "Contraseña incorrecta");
        else model.addAttribute("loginError", "Correo incorrecto");
        return "login";
    }

    @PostMapping("/searchEmployee")
    public String searchEmployee(@RequestParam("email") String email,
                                 Model model) {
        UserE user = userService.getUserByEmail(email);
        model.addAttribute("users", user);
        model.addAttribute("researchName", email);
        return "editEmployee";
    }

    @PostMapping("/searchEmployeeDrop")
    public String searchForDrop(@RequestParam("email") String email,
                                Model model) {
        UserE user = userService.getUserByEmail(email);
        model.addAttribute("users", user);
        model.addAttribute("researchName", email);
        return "deleteEmployee";
    }

    @GetMapping("/updateEmployee/{id}")
    public String updateSalon(@PathVariable("id") Long userId,
                              Model model) {
        UserE user = userService.getUserById(userId);
        model.addAttribute("user", user);
        return "updateEmployee";
    }

    @GetMapping("/dropEmployee/{id}")
    public String dropEmployee(@PathVariable("id") Long userId,
                               Model model) {
        userService.deleteUserById(userId);
        return "redirect:/employee";
    }

    @GetMapping("/mostCurrencyFeature")
    public String mostCurrencyFeature(Model model) {
        String favorite = characteristicService.mostCurrencyFeature();
        model.addAttribute("favorite", favorite);
        return "queryView";
    }
}
