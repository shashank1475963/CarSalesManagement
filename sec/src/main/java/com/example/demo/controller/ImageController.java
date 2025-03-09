package com.example.demo.controller;

import com.example.demo.service.BucketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")
public class ImageController {

    private final BucketService bucketService;

    public ImageController(BucketService bucketService) {
        this.bucketService = bucketService;
    }

    @PostMapping("/file/{bucketName}/car/{carId}")
    public ResponseEntity<String> uploadCarPhoto(
            @RequestParam("file") MultipartFile file,
            @PathVariable String bucketName,
            @PathVariable Long carId) {
        try {
            String fileUrl = bucketService.uploadFile(bucketName, file, carId);
            return ResponseEntity.ok("File uploaded successfully: " + fileUrl);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("File upload failed: " + e.getMessage());
        }
    }
}
