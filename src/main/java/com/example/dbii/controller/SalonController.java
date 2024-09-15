package com.example.dbii.controller;

import com.example.dbii.entity.Salon;
import com.example.dbii.service.SalonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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

    @PostMapping("/search")
    public String search(@RequestParam("nombre") String nombre,
                         Model model) {

        Salon s1 = new Salon();
        Salon s2 = new Salon();
        Salon s3 = new Salon();

        s1.setSalonName("Salón de Eventos Las Rosas");
        s1.setLocation("Un salón amplio y moderno.");
        s1.setMaxCapacity(200L);

        s2.setSalonName("Salón La Estrella");
        s2.setLocation("Perfecto para bodas y eventos grandes.");
        s2.setMaxCapacity(300L);

        s3.setSalonName("Salón El Sol");
        s3.setLocation("Ideal para eventos pequeños.");
        s3.setMaxCapacity(100L);

        List<Salon> salons = List.of(
                s1,s2,s3,s3,s1,s2,s3,s3
        );

        model.addAttribute("results", salons);
        model.addAttribute("researchName", nombre);

        return "editSalon";
    }
}
