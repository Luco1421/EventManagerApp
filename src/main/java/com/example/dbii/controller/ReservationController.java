package com.example.dbii.controller;

import com.example.dbii.entity.Image;
import com.example.dbii.entity.Salon;
import com.example.dbii.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Controller
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/catalogView")
    public String catalogView() {
        return "catalogView";
    }


    @GetMapping("/confirmation")
    public String confirmation() {
        return "confirmation";
    }

    @PostMapping("/searchAvaible")
    public String searchAvaible(@RequestParam("schedule") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
                                Model model) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, d 'de' MMMM 'de' yyyy", Locale.getDefault());
        String formattedDate = dateFormat.format(date);
        Image image = new Image();
        image.setUrl("https://i.imgur.com/QYWAcXk.jpeg");
        Salon salon = new Salon();
        salon.setSalonName("Pan");
        salon.setLocation("sfdasd");
        salon.setMaxCapacity(15155L);
        List<Salon> results = List.of(salon,salon,salon,salon);
        model.addAttribute("researchName", formattedDate);
        model.addAttribute("image", image);
        model.addAttribute("results", results);
        return "catalogView";
    }

    @PostMapping("/reservation")
    public String makeReservation(
                               Model model) {
        try {
            reservationService.makeReservation();
            return "redirect:/home";
        } catch (Exception e) {
            model.addAttribute("reservationError", e.getMessage());
            return "redirect:/reservationDetails";
        }
    }

}
