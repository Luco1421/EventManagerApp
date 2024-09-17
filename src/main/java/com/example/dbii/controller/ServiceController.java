package com.example.dbii.controller;

import com.example.dbii.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ServiceController {

    @Autowired
    private ServiceService serviceService;

    @PostMapping("/saveService/{id}")
    public String saveFeature(@PathVariable("id") Long packId,
                              @RequestParam("selectedService") String selectedService,
                              @RequestParam("otherService") String otherService,
                              @RequestParam("description") String description,
                              @RequestParam("price") Double price,
                              Model model) {
        Long serviceId;
        if (selectedService.isEmpty()) {
            model.addAttribute("errorAdd", "No seleccionó ninguna característica");
        }
        if (selectedService.equals("other")) {
            serviceId = serviceService.addService(otherService, description, price);
            if (serviceId == -1) serviceId = serviceService.getServiceByName(otherService).getId();
        } else serviceId = serviceService.getServiceByName(selectedService).getId();
        serviceService.addServiceToPack(packId, serviceId);
        return "redirect:/updatePack/" + packId;
    }

    @PostMapping("/dropService")
    public String deleteService(@RequestParam("serviceId") Long serviceId,
                                @RequestParam("packId") Long packId) {
        serviceService.removeServiceFromPack(serviceId, packId);
        return "redirect:/updatePack/" + packId;
    }
}
