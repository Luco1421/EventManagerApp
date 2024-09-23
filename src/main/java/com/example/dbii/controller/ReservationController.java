package com.example.dbii.controller;

import com.example.dbii.entity.*;
import com.example.dbii.repository.TypeRepository;
import com.example.dbii.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonMixinModuleEntries;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

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
    private TypeService typeService;
    @Autowired
    private TypeRepository typeRepository;

    @GetMapping("/reserves")
    public String showMyReservations(Model model,
                                     HttpSession session) {
        UserE user = (UserE) session.getAttribute("user");
        List<Reservation> reservations = reservationService.findReservationsByUser(user);
        model.addAttribute("reservations", reservations);
        return "reserves";
    }

    @GetMapping("/catalogView")
    public String catalogView(Model model) {
        model.addAttribute("types", typeService.getAllTypes());
        return "catalogView";
    }

    @GetMapping("/confirmation")
    public String confirmation() {
        return "confirmation";
    }

    @PostMapping("/reserve/{id}")
    public String reservationDetails(@RequestParam Long salonId,
                                     @RequestParam LocalDate date,
                                     @PathVariable Long id,
                                     Model model,
                                     HttpSession session) {
        if (id == -1) {
            Salon salon = salonService.getSalonById(salonId);
            session.setAttribute("salon", salon);
            Type type = (Type) session.getAttribute("selectedType");
            UserE user = (UserE) session.getAttribute("user");
            Reservation reservation = reservationService.makeReservation(salon, date, user, type);
            id = reservation.getId();
            session.setAttribute("id", id);
        }
        model.addAttribute("id", id);
        model.addAttribute("salon", session.getAttribute("salon"));
        model.addAttribute("reservation", reservationService.getReservationById(id));
        model.addAttribute("packs", packService.getAllPacks());
        model.addAttribute("services", serviceService.getAllServices());
        return "reservationDetails";
    }

    @PostMapping("/managePack")
    public String managePack(@RequestParam("selectedPack") Long packId,
                             @RequestParam("action") String action,
                             Model model,
                             HttpSession session) {
        Long id = (Long) session.getAttribute("id");
        if ("add".equals(action)) {
            if (reservationService.getReservationById(id).getPack() == null) {
                packService.addPackToReservation(id, packId);
            } else {
                model.addAttribute("error", "Sólo se puede añadir un paquete por reserva");
            }
        } else if ("info".equals(action)) {
            Pack selectedPack = packService.getPackById(packId);
            model.addAttribute("selectedPack", selectedPack);
        }
        model.addAttribute("salon", session.getAttribute("salon"));
        model.addAttribute("reservation", reservationService.getReservationById(id));
        model.addAttribute("packs", packService.getAllPacks());
        model.addAttribute("services", serviceService.getAllServices());
        return "reservationDetails";
    }

    @PostMapping("/manageService")
    public String manageService(@RequestParam("selectedService") Long serviceId,
                                @RequestParam("action") String action,
                                Model model,
                                HttpSession session) {
        Long id = (Long) session.getAttribute("id");
        if ("add".equals(action)) {
            serviceService.addServiceToReservation(id, serviceId);
        } else if ("info".equals(action)) {
            Service selectedService = serviceService.getServiceById(serviceId);
            model.addAttribute("selectedService", selectedService);
        }
        model.addAttribute("salon", session.getAttribute("salon"));
        model.addAttribute("reservation", reservationService.getReservationById(id));
        model.addAttribute("packs", packService.getAllPacks());
        model.addAttribute("services", serviceService.getAllServices());
        return "reservationDetails";
    }

    @PostMapping("/deleteServiceReservation")
    public String eraseServiceReservation(@RequestParam("serviceId") Long serviceId,
                                          HttpSession session,
                                          Model model) {
        Long id = (Long) session.getAttribute("id");
        serviceService.removeServiceFromReservation(id, serviceId);
        model.addAttribute("id", id);
        model.addAttribute("salon", session.getAttribute("salon"));
        model.addAttribute("reservation", reservationService.getReservationById(id));
        model.addAttribute("packs", packService.getAllPacks());
        model.addAttribute("services", serviceService.getAllServices());
        return "reservationDetails";
    }

    @PostMapping("/removePack")
    public String removePackFromReservation(HttpSession session,
                                            Model model) {
        Long id = (Long) session.getAttribute("id");
        packService.removePackFromReservation(id);
        model.addAttribute("id", id);
        model.addAttribute("salon", session.getAttribute("salon"));
        model.addAttribute("reservation", reservationService.getReservationById(id));
        model.addAttribute("packs", packService.getAllPacks());
        model.addAttribute("services", serviceService.getAllServices());
        return "reservationDetails";
    }

    @PostMapping("/makeReservation")
    public String makeReservation(HttpSession session,
                                  Model model) {
        Long id = (Long) session.getAttribute("id");
        reservationService.inProcess(id);
        return "confirmation";
    }

}