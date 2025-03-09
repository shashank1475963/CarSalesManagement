package com.example.demo.controller;

import com.example.demo.entity.cars.Brand;
import com.example.demo.service.BulkUploadBrandNameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/brand/bulk-upload")
public class BrandBulkUploadController {

    private final BulkUploadBrandNameService bulkUploadBrandNameService;

    public BrandBulkUploadController(BulkUploadBrandNameService bulkUploadBrandNameService) {
        this.bulkUploadBrandNameService = bulkUploadBrandNameService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadBrands(@RequestParam("file") MultipartFile file) {
        try {
            List<Brand> savedBrands = bulkUploadBrandNameService.uploadBrands(file);
            return ResponseEntity.ok("Uploaded successfully! Saved " + savedBrands.size() + " brands.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}
