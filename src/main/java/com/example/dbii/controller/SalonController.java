package com.example.dbii.controller;

import com.example.dbii.entity.Characteristic;
import com.example.dbii.entity.Image;
import com.example.dbii.entity.Salon;
import com.example.dbii.service.CharacteristicService;
import com.example.dbii.service.SalonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Set;

@Controller
public class SalonController {

    @Autowired
    private SalonService salonService;

    @Autowired
    private CharacteristicService characteristicService;

    @GetMapping("/salon")
    public String salonView() { return "salonView"; }

    @GetMapping("/addSalon")
    public String addSalon() { return "addSalon"; }

    @GetMapping("/editSalon")
    public String editSalon() { return "editSalon"; }

    @GetMapping("/deleteSalon")
    public String deleteSalon() { return "deleteSalon"; }

    @GetMapping("/updateSalon/{id}")
    public String updateSalon(@PathVariable Long id, Model model) {
        Salon salon = salonService.getSalonById(id);
        List<Characteristic> features = characteristicService.getAllCharacteristic();
        model.addAttribute("salon", salon);
        model.addAttribute("features", features);
        return "updateSalon";
    }

    @PostMapping("/newSalon")
    public String newSalon(@RequestParam("name") String name,
                           @RequestParam("location") String location,
                           @RequestParam("maxCapacity") Long maxCapacity,
                           Model model) {
        Long id =  salonService.addSalon(name, location, maxCapacity);
        if(id != -1) return "redirect:/updateSalon/"+id;
        model.addAttribute("errorAdd", "Ya este salón existe");
        return "addSalon";
    }

    @PostMapping("/searchSalon")
    public String search(@RequestParam("nombre") String nombre,
                         Model model) {
        Set<Salon> salons = salonService.getSalonByName(nombre);
        model.addAttribute("salons", salons);
        model.addAttribute("researchName", nombre);
        return "editSalon";
    }

    @PostMapping("/searchSalonDrop")
    public String searchForDrop(@RequestParam("name") String name,
                                Model model) {
        Set<Salon> salons = salonService.getSalonByName(name);
        model.addAttribute("salons", salons);
        model.addAttribute("researchName", name);
        return "deleteSalon";
    }

    @PostMapping("/searchAvaible")
    public String searchAvaible(@RequestParam("schedule") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                Model model) {
        if (date.isBefore(LocalDate.now())) {
            model.addAttribute("errorDate","La fecha no es válida");
            return "catalogView";
        }
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("EEEE, d 'de' MMMM 'de' yyyy", Locale.forLanguageTag("es-ES"));
        String formattedDate = date.format(dateFormatter);
        Image image = new Image();
        image.setUrl("https://i.imgur.com/QYWAcXk.jpeg");
        List<Salon> salons = salonService.getAvailableSalons(date);
        model.addAttribute("researchName", formattedDate);
        model.addAttribute("image", image);
        model.addAttribute("salons", salons);
        return "catalogView";
    }

    @GetMapping("/dropSalon/{id}")
    public String deleteSalon(@PathVariable("id") Long id) {
        salonService.deleteSalon(id);
        return "redirect:/salon";
    }
}