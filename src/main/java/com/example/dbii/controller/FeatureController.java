package com.example.dbii.controller;

import com.example.dbii.service.CharacteristicService;
import com.example.dbii.service.SalonFeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class FeatureController {

    @Autowired
    private SalonFeatureService salonCharacteristicService;

    @Autowired
    private CharacteristicService characteristicService;

    @PostMapping("/saveFeature/{id}")
    public String saveFeature(@PathVariable("id") Long salonId,
                              @RequestParam("selectedFeature") String selectedFeature,
                              @RequestParam("otherFeature") String otherFeature,
                              @RequestParam("quantity") int quantity,
                              Model model) {
        Long characteristicId;
        if (selectedFeature.isEmpty()) {
            model.addAttribute("errorAdd", "No seleccionó ninguna característica");
        }
        if (selectedFeature.equals("other")) {
            characteristicId = characteristicService.addFeature(otherFeature);
            if (characteristicId == -1) characteristicId = characteristicService.getCharacteristicByName(otherFeature).getId();
        } else characteristicId = characteristicService.getCharacteristicByName(selectedFeature).getId();
        salonCharacteristicService.addFeatureToSalon(salonId, characteristicId, quantity);
        return "redirect:/updateSalon/" + salonId;
    }

    @PostMapping("/dropFeature")
    public String deleteFeature(@RequestParam("salonId") Long salonId,
                                @RequestParam("characteristicId") Long characteristicId) {
        salonCharacteristicService.removeCharacteristicFromSalon(salonId, characteristicId);
        return "redirect:/salon";
    }
}
