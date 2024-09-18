package com.example.dbii.controller;

import com.example.dbii.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ServiceController {

    @Autowired
    private ServiceService serviceService;

    @PostMapping("/saveService/{id}")
    public String saveServiceToPack(@PathVariable Long id,
                                    @RequestParam("selectedService") Long serviceId,
                                    @RequestParam(name = "otherService", required = false) String otherService,
                                    @RequestParam(name = "description", required = false) String description,
                                    @RequestParam(name = "price", required = false) Double price) {

        if (serviceId == -1) {
            Long newServiceId = serviceService.addService(otherService, description, price);
            serviceService.addServiceToPack(id, newServiceId);
        }
        else serviceService.addServiceToPack(id, serviceId);
        return "redirect:/updatePack/" + id;
    }

    @PostMapping("/dropService")
    public String deleteService(@RequestParam("packId") Long packId,
                                @RequestParam("serviceId") Long serviceId) {
        serviceService.removeServiceToPack(packId, serviceId);
        return "redirect:/updatePack/" + packId;
    }
}
