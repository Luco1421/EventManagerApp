package com.example.dbii.controller;

import com.example.dbii.service.ImageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

@Controller
public class ImageController {

    @Autowired
    ImageService imageService;

    @PostMapping("/uploadImage")
    public String uploadImage(@RequestParam("file") MultipartFile file,
                              @RequestParam("salonId") Long salonId) throws IOException {
        imageService.addImage(file, salonId);
        return "redirect:/updateSalon/" + salonId;
    }

}
