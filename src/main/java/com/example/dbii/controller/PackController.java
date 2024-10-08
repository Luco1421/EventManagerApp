package com.example.dbii.controller;

import com.example.dbii.entity.Pack;
import com.example.dbii.entity.Salon;
import com.example.dbii.entity.Service;
import com.example.dbii.service.PackService;
import com.example.dbii.service.SalonService;
import com.example.dbii.service.ServiceService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Controller
public class PackController {

    @Autowired
    private PackService packService;

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private SalonService salonService;

    @GetMapping("/pack")
    public String packView() { return "packView"; }

    @GetMapping("/addPack")
    public String addPack() { return "addPack"; }

    @GetMapping("/editPack")
    public String editPack() { return "editPack"; }

    @GetMapping("/deletePack")
    public String deletePack() { return "deletePack"; }

    @GetMapping("/updatePack/{id}")
    public String updatePack(@PathVariable Long id,
                             Model model) {
        Pack pack = packService.getPackById(id);
        List<Service> services = serviceService.getAllServices();
        model.addAttribute("pack", pack);
        model.addAttribute("services", services);
        return "updatePack";
    }

    @PostMapping("/newPack")
    public String newPack(@RequestParam("name") String name,
                           @RequestParam("description") String description,
                           Model model) {
        Long id = packService.addPack(name, description);
        if (id != -1) return "redirect:/updatePack/"+id;
        model.addAttribute("errorAdd", "Ya este paquete existe");
        return "addPack";
    }

    @PostMapping("/searchPack")
    public String search(@RequestParam("name") String name,
                         Model model) {
        Set<Pack> packs = packService.getPackByName(name);
        model.addAttribute("results", packs);
        model.addAttribute("researchName", name);
        return "editPack";
    }

    @PostMapping("/searchPackDrop")
    public String searchForDrop(@RequestParam("name") String name,
                         Model model) {
        Set<Pack> packs = packService.getPackByName(name);
        model.addAttribute("results", packs);
        model.addAttribute("researchName", name);
        return "deletePack";
    }

    @GetMapping("/dropPack/{id}")
    public String dropPack(@PathVariable("id") Long packId,
                           Model model) {
        packService.deletePack(packId);
        return "redirect:/pack";
    }

}
