package com.example.dbii.controller;

import com.example.dbii.service.SalonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SalonController {

    @Autowired
    private SalonService salonService;

    @GetMapping("/admin")
    public String viewsAdmin() { return "adminView"; }

    @GetMapping("/addSalon")
    public String addSalon() { return "addNewSalon"; }

    @PostMapping("/newSalon")
    public String newSalon(@RequestParam("name") String name,
                               @RequestParam("location") String location,
                               @RequestParam("maxCapacity") Long maxCapacity,
                               Model model) {
        try {
            salonService.addSalon(name, location, maxCapacity);
            return "redirect:/admin";
        } catch (Exception e) {
            model.addAttribute("addError", e.getMessage());
            return "redirect:/addSalon";
        }
    }

}
