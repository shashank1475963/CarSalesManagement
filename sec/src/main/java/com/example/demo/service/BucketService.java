package com.example.demo.service;

import com.example.demo.entity.cars.Car;
import com.example.demo.entity.cars.CarImage;
import com.example.demo.repo.car.CarImageRepository;
import com.example.demo.repo.car.CarRepository;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.UUID;

@Service
public class BucketService {

    private final S3Client s3Client;
    private final CarRepository carRepository;
    private final CarImageRepository carImageRepository;
    private final String region;

    public BucketService(S3Client s3Client, CarRepository carRepository, CarImageRepository carImageRepository, String region) {
        this.s3Client = s3Client;
        this.carRepository = carRepository;
        this.carImageRepository = carImageRepository;
        this.region = region;  // Assign the region from AWSS3Config
    }

    public String uploadFile(String bucketName, MultipartFile file, Long carId) throws IOException {
        Car car = carRepository.findById(carId).orElseThrow(() -> new RuntimeException("Car not found"));

        String fileName = UUID.randomUUID() + "-" + file.getOriginalFilename();

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .contentType(file.getContentType())
                .build();

        s3Client.putObject(putObjectRequest, RequestBody.fromBytes(file.getBytes()));

        // Corrected URL format
        String fileUrl = "https://" + bucketName + ".s3." + region + ".amazonaws.com/" + fileName;

        CarImage carImage = new CarImage();
        carImage.setUrl(fileUrl);
        carImage.setCar(car);
        carImageRepository.save(carImage);

        return fileUrl;
    }
}
