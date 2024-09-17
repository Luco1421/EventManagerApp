package com.example.dbii.controller;

import com.example.dbii.service.PackService;
import com.example.dbii.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ServiceController {

    @Autowired
    private ServiceService serviceService;
    @Autowired
    private PackService packService;

    @PostMapping("/saveService/{id}")
    public String saveFeature(@PathVariable("id") Long packId,
                              @RequestParam("selectedService") String selectedService,
                              @RequestParam("otherService") String otherService,
                              @RequestParam("description") String description,
                              @RequestParam(value = "price", required = false) Double price,
                              Model model) {
        Long serviceId;
        if (selectedService.isEmpty()) {
            model.addAttribute("errorAdd", "No seleccionó ninguna característica");
            return "redirect:/updatePack/" + packId;
        }
        if (selectedService.equals("other")) {
            serviceId = serviceService.addService(otherService, description, price);
            if (serviceId == -1) serviceId = serviceService.getServiceByName(otherService).getId();
        } else serviceId = serviceService.getServiceByName(selectedService).getId();
        packService.addServiceToPackS(packId, serviceId);
        return "redirect:/updatePack/" + packId;
    }


    @PostMapping("/dropService")
    public String deleteService(@RequestParam("serviceId") Long serviceId,
                                @RequestParam("packId") Long packId) {
        packService.removeServiceFromPackS(serviceId, packId);
        return "redirect:/updatePack/" + packId;
    }
}
