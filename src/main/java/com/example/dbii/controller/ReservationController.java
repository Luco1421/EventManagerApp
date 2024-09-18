package com.example.dbii.controller;

import com.example.dbii.entity.*;
import com.example.dbii.repository.UserERepository;
import com.example.dbii.service.PackService;
import com.example.dbii.service.ReservationService;
import com.example.dbii.service.SalonService;
import com.example.dbii.service.ServiceService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

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

    @Autowired
    private UserERepository userERepository;

    @GetMapping("/catalogView")
    public String catalogView() { return "catalogView"; }

    @GetMapping("/confirmation")
    public String confirmation() {
        return "confirmation";
    }

    @GetMapping("/reservate/{id}/{dma}")
    public String reservationDetails(@PathVariable Long id,
                                     @PathVariable LocalDate dma,
                                     Model model,
                                     HttpSession session) {
        Long rid = (Long) session.getAttribute("reservationId");
        if (rid == null) {
            String email = (String) session.getAttribute("email");
            Long nrid = reservationService.makeReservation(id,dma,email);
            session.setAttribute("reservationId", nrid);
        }

        Long dnrid = (Long) session.getAttribute("reservationId");
        Reservation reservation = (Reservation) reservationService.getReservationById(dnrid);
        Salon salon = salonService.getSalonById(id);
        session.setAttribute("salon", salon);
        model.addAttribute("salon", salon);
        model.addAttribute("reservation", reservation);
        model.addAttribute("packs", packService.getAllPacks());
        model.addAttribute("services", serviceService.getAllServices());
        return "reservationDetails";
    }

    @PostMapping("/managePack")
    public String managePack(@RequestParam("selectedPack") Long packId,
                             @RequestParam("action") String action,
                             Model model,
                             HttpSession session) {
        Reservation reservation = reservationService.getReservationById((Long)session.getAttribute("reservationId"));
        if ("add".equals(action)) {
            if (reservation.getPack() == null) {
                packService.addPackToReservation(reservation.getId(), packId);
            } else {
                model.addAttribute("error", "Sólo se puede añadir un paquete por reserva");
            }
        } else if ("info".equals(action)) {
            Pack selectedPack = packService.getPackById(packId);
            model.addAttribute("selectedPack", selectedPack);
        }
        Salon salon = (Salon) session.getAttribute("salon");
        model.addAttribute("salon", salon);
        model.addAttribute("reservation", reservation);
        model.addAttribute("packs", packService.getAllPacks());
        model.addAttribute("services", serviceService.getAllServices());
        return "reservationDetails";
    }

    @PostMapping("/manageService")
    public String manageService(@RequestParam("selectedService") Long serviceId,
                                 @RequestParam("action") String action,
                                 Model model,
                                 HttpSession session) {
        Reservation reservation = reservationService.getReservationById((Long)session.getAttribute("reservationId"));
        if ("add".equals(action)) {
            serviceService.addServiceToReservation(reservation.getId(), serviceId);
        } else if ("info".equals(action)) {
            Service selectedService = serviceService.getServiceById(serviceId);
            model.addAttribute("selectedService", selectedService);
        }
        Salon salon = (Salon) session.getAttribute("salon");
        model.addAttribute("salon", salon);
        model.addAttribute("reservation", reservation);
        model.addAttribute("packs", packService.getAllPacks());
        model.addAttribute("services", serviceService.getAllServices());
        return "reservationDetails";
    }

    @PostMapping("/deleteServiceReservation")
    public String eraseServiceReservation(@RequestParam("reservationId") Long reservationId,
                                          @RequestParam("serviceId") Long serviceId,
                                          HttpSession session,
                                          Model model) {
        Reservation reservation = reservationService.getReservationById((Long)session.getAttribute("reservationId"));
        serviceService.removeServiceFromReservation(reservationId, serviceId);
        Salon salon = (Salon) session.getAttribute("salon");
        model.addAttribute("salon", salon);
        model.addAttribute("reservation", reservation);
        model.addAttribute("packs", packService.getAllPacks());
        model.addAttribute("services", serviceService.getAllServices());
        return "reservationDetails";
    }

    @PostMapping("/removePack")
    public String removePackFromReservation(@RequestParam("reservationId") Long reservationId,
                                            HttpSession session,
                                            Model model) {
        Reservation reservation = reservationService.getReservationById((Long)session.getAttribute("reservationId"));
        packService.removePackFromReservation(reservationId);
        Salon salon = (Salon) session.getAttribute("salon");
        model.addAttribute("salon", salon);
        model.addAttribute("reservation", reservation);
        model.addAttribute("packs", packService.getAllPacks());
        model.addAttribute("services", serviceService.getAllServices());
        return "reservationDetails";
    }

    /*@PostMapping("/makeReservation")
    public String makeReservation() {}*/

}