package com.example.dbii.controller;

import com.example.dbii.entity.*;
import com.example.dbii.service.CharacteristicService;
import com.example.dbii.service.ImageService;
import com.example.dbii.service.SalonService;
import com.example.dbii.service.TypeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Set;

@Controller
public class SalonController {

    @Autowired
    private SalonService salonService;

    @Autowired
    private CharacteristicService characteristicService;

    @Autowired
    private TypeService typeService;

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
                                @RequestParam("selectedType") Long typeId,
                                HttpSession session,
                                Model model) {

        Type selectedType = typeService.getTypeById(typeId);
        model.addAttribute("selectedType", selectedType);
        session.setAttribute("selectedType", selectedType);

        model.addAttribute("types", typeService.getAllTypes());

        if (date.isBefore(LocalDate.now())) {
            model.addAttribute("errorDate","La fecha no es válida");
            return "catalogView";
        }
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("EEEE, d 'de' MMMM 'de' yyyy", Locale.forLanguageTag("es-ES"));
        String formattedDate = date.format(dateFormatter);

        List<Salon> salons = salonService.getAvailableSalons(date);

        model.addAttribute("date", date);
        model.addAttribute("researchName", formattedDate);
        model.addAttribute("salons", salons);
        return "catalogView";
    }

    @GetMapping("/dropSalon/{id}")
    public String deleteSalon(@PathVariable("id") Long id) {
        salonService.deleteSalon(id);
        return "redirect:/salon";
    }

    @GetMapping("/salon-evento-mas-comun")
    public String mostrarSalonYEventoMasComun(
            @RequestParam(value = "fechaInicio", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaInicio,
            @RequestParam(value = "fechaFin", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaFin,
            Model model) {

        if (fechaInicio == null || fechaFin == null) {
            model.addAttribute("resultado", "Por favor, selecciona un rango de fechas.");
        } else {
            String resultado = salonService.getSalonYEventoMasComun(fechaInicio, fechaFin);
            model.addAttribute("resultado", resultado);
        }
        return "salon-evento-mas-comun";
    }

    @PostMapping("/setSalon")
    public String setSalon(@RequestParam("id") Long id,
                           @RequestParam("name") String name,
                           @RequestParam("location") String location,
                           @RequestParam("maxCapacity") Long maxCapacity,
                           Model model) {
        Salon updatedSalon = salonService.updateSalon(id, name, location, maxCapacity);
        if (updatedSalon != null) {
            return "redirect:/salon";
        }
        model.addAttribute("errorAdd", "Error al actualizar el salón");
        return "updateSalon";
    }

}