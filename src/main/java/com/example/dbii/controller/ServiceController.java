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
    public String saveServiceToPack(@PathVariable Long id,
                                    @RequestParam("selectedService") Long serviceId,
                                    @RequestParam(name = "otherService", required = false) String otherService,
                                    @RequestParam(name = "description", required = false) String description,
                                    @RequestParam(name = "price", required = false) Double price) {

        if (serviceId == -1) {
            // Añadir nuevo servicio si "Otro" fue seleccionado
            Long newServiceId = serviceService.addService(otherService, description, price);
            packService.addServiceToPackS(id, newServiceId);
        } else {
            // Añadir servicio existente
            packService.addServiceToPackS(id, serviceId);
        }
        return "redirect:/updatePack/" + id;
    }



    @PostMapping("/dropService/{packId}/{serviceId}")
    public String deleteService(@PathVariable("packId") Long packId,
                                @PathVariable("serviceId") Long serviceId) {
        packService.removeServiceFromPackS(packId, serviceId);
        return "redirect:/updatePack/" + packId;
    }
}
