package com.example.dbii.controller;

import com.example.dbii.entity.Pack;
import com.example.dbii.service.PackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

@Controller
public class PackController {

    @Autowired
    private PackService packService;

    @GetMapping("/pack")
    public String packViewsAdmin() { return "adminPackView"; }

    @GetMapping("/addPack")
    public String addPack() { return "addNewPack"; }

    @GetMapping("/editPack")
    public String editPack() { return "editPack"; }

    @GetMapping("/updatePack/{id}")
    public String updatePack(@PathVariable Long id, Model model) {
        Pack pack = packService.getPackById(id);
        model.addAttribute("pack", pack);
        return "updatePack";
    }

    @PostMapping("/newPack")
    public String newPack(@RequestParam("name") String name,
                           @RequestParam("description") String description,
                           Model model) {
        Long id = packService.addPack(name, description);
        if(id != -1) return "redirect:/updatePack/"+id;
        else model.addAttribute("errorAdd", "Ya este paquete existe");
        return "addNewSalon";
    }

    @PostMapping("/searchPack")
    public String search(@RequestParam("nombre") String nombre,
                         Model model) {
        Set<Pack> packs = packService.getSalonByName(nombre);
        model.addAttribute("results", packs);
        model.addAttribute("researchName", nombre);
        return "editPack";
    }


}
