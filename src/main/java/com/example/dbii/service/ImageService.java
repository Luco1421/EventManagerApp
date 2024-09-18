package com.example.dbii.service;

import com.example.dbii.entity.Image;
import com.example.dbii.entity.Salon;
import com.example.dbii.repository.ImageRepository;
import com.example.dbii.repository.SalonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private SalonRepository salonRepository;

    private static String cleanFileName(String originalFileName, Long salonId, String salonName) {
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String cleanedSalonName = salonName.trim().replaceAll("[^a-zA-Z0-9]", "_");
        String newFileName = cleanedSalonName + "_" + salonId + extension;
        return newFileName;
    }

    private static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/src/main/resources/static/uploads/";

    @Transactional
    public void addImage(MultipartFile file, Long salonId) throws IOException {
        Path uploadPath = Paths.get(UPLOAD_DIRECTORY);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        Salon salon = salonRepository.findById(salonId).orElse(null);
        String newFileName = cleanFileName(file.getOriginalFilename(), imageRepository.count(), salon.getSalonName());
        Path newFilePath = Paths.get(UPLOAD_DIRECTORY, newFileName);
        Files.write(newFilePath, file.getBytes());
        Image image = new Image();
        image.setUrl(newFileName);
        image.setSalon(salon);
        imageRepository.save(image);
    }
}
