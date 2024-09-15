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

    @GetMapping("/editSalon")
    public String editSalon() { return "editSalon"; }

    @PostMapping("/newSalon")
    public String newSalon(@RequestParam("name") String name,
                               @RequestParam("location") String location,
                               @RequestParam("maxCapacity") Long maxCapacity,
                               Model model) {
        if(salonService.addSalon(name, location, maxCapacity)) return "redirect:/admin";
        else model.addAttribute("errorAdd", "Ya este salón existe");
        return "addNewSalon";
    }
}
