package com.example.dbii.controller;

import com.example.dbii.entity.*;
import com.example.dbii.service.PackService;
import com.example.dbii.service.ReservationService;
import com.example.dbii.service.SalonService;
import com.example.dbii.service.ServiceService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private SalonService salonService;

    @Autowired
    private PackService packService;

    @Autowired
    private ServiceService serviceService;

    @GetMapping("/catalogView")
    public String catalogView() {
        return "catalogView";
    }

    @GetMapping("/confirmation")
    public String confirmation() {
        return "confirmation";
    }

    @GetMapping("/reservate/{id}")
    public String reservationDetails(@PathVariable Long id,
                                     Model model,
                                     HttpSession session) {
        Long reservationId = (Long) session.getAttribute("reservationId");
        if (reservationId == null) {
            Reservation newReservation = reservationService.makeReservation();
            session.setAttribute("reservationId", newReservation.getId());
        }
        Salon salon = salonService.getSalonById(id);
        Image i1 = new Image();
        i1.setUrl("https://i.imgur.com/QYWAcXk.jpeg");
        Image i2 = new Image();
        i2.setUrl("https://i.blogs.es/82d7ef/pokemon/1366_2000.webp");
        Image i3 = new Image();
        i3.setUrl("https://i.imgur.com/QYWAcXk.jpeg");
        Image i4 = new Image();
        i4.setUrl("https://i.blogs.es/82d7ef/pokemon/1366_2000.webp");
        Image i5 = new Image();
        i5.setUrl("https://i.imgur.com/QYWAcXk.jpeg");
        Image i6 = new Image();
        i6.setUrl("https://i.blogs.es/82d7ef/pokemon/1366_2000.webp");
        salon.getImages().add(i1);
        salon.getImages().add(i2);
        salon.getImages().add(i3);
        salon.getImages().add(i4);
        salon.getImages().add(i5);
        salon.getImages().add(i6);
        model.addAttribute("salon", salon);
        model.addAttribute("packs", packService.getAllPacks());
        model.addAttribute("services", serviceService.getAllServices());

        return "reservationDetails";
    }

    @PostMapping("/managePack")
    public String managePack(@RequestParam("selectedPack") Long packId,
                             @RequestParam("action") String action,
                             Model model,
                             HttpSession session) {
        if ("add".equals(action)) {
            Long reservationId = (Long) session.getAttribute("reservaId");
            packService.addPackToReservation(reservationId, packId);
        } else if ("info".equals(action)) {
            Pack selectedPack = packService.getPackById(packId);
            model.addAttribute("selectedPack", selectedPack);
        }
        model.addAttribute("salon", salonService.getSalonById(packId));
        model.addAttribute("packs", packService.getAllPacks());
        model.addAttribute("services", serviceService.getAllServices());
        return "reservationDetails";
    }

    @PostMapping("/manageService")
    public String manageService(@RequestParam("selectedService") Long serviceId,
                                 @RequestParam("action") String action,
                                 Model model,
                                 HttpSession session) {
        if ("add".equals(action)) {
            Long reservationId = (Long) session.getAttribute("reservationId");
            serviceService.addServiceToReservation(reservationId, serviceId);
        } else if ("info".equals(action)) {
            Service selectedService = serviceService.getServiceById(serviceId);
            model.addAttribute("selectedService", selectedService);
        }
        model.addAttribute("packs", packService.getAllPacks());
        model.addAttribute("services", serviceService.getAllServices());
        return "reservationDetails";
    }

}