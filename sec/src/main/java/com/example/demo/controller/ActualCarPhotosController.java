package com.example.demo.controller;

import com.example.demo.entity.cars.Car;
import com.example.demo.entity.evaluation.CarEvaluationPhotos;
import com.example.demo.repo.CarEvaluationPhotosRepository;
import com.example.demo.service.BucketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/actual-car-photos")
public class ActualCarPhotosController {

    private final BucketService bucketService;
    private final CarEvaluationPhotosRepository carEvaluationPhotosRepository;

    public ActualCarPhotosController(BucketService bucketService,
                                     CarEvaluationPhotosRepository carEvaluationPhotosRepository) {
        this.bucketService = bucketService;
        this.carEvaluationPhotosRepository = carEvaluationPhotosRepository;
    }

    @PostMapping("/file/{bucketName}/car/{carId}")
    public ResponseEntity<String> uploadCarPhoto(
            @RequestParam List<MultipartFile> files,
            @PathVariable String bucketName,
            @PathVariable Long carId) throws IOException {
        

        for(MultipartFile file:files){
            String url = bucketService.uploadFile(bucketName, file, carId);
            CarEvaluationPhotos carEvaluationPhotos=new CarEvaluationPhotos();
            carEvaluationPhotos.setPhotoUrl(url);
            carEvaluationPhotosRepository.save(carEvaluationPhotos);
        }

        return  new ResponseEntity<>("Saved", HttpStatus.CREATED);


    }

}
