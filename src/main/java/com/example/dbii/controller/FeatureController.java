package com.example.dbii.controller;

import com.example.dbii.service.CharacteristicService;
import com.example.dbii.service.SalonFeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class FeatureController {

    @Autowired
    private SalonFeatureService salonCharacteristicService;

    @Autowired
    private CharacteristicService characteristicService;

    @PostMapping("/saveFeature/{id}")
    public String saveFeature(@PathVariable("id") Long salonId,
                              @RequestParam("selectedFeature") Long featureId,
                              @RequestParam(value = "otherFeature", required = false) String otherFeature,
                              @RequestParam(value = "quantity", required = false) int quantity) {
        if (featureId == -1) {
            Long newFeatureId = characteristicService.addFeature(otherFeature);
            salonCharacteristicService.addFeatureToSalon(salonId, newFeatureId, quantity);
        }
        else salonCharacteristicService.addFeatureToSalon(salonId, featureId, quantity);
        return "redirect:/updateSalon/" + salonId;
    }

    @PostMapping("/dropFeature")
    public String deleteFeature(@RequestParam("salonId") Long salonId,
                                @RequestParam("characteristicId") Long characteristicId) {
        salonCharacteristicService.removeFeatureFromSalon(salonId, characteristicId);
        return "redirect:/updateSalon/" + salonId;
    }
}
