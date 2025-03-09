package com.example.demo.controller.car;


import com.example.demo.entity.cars.Brand;
import com.example.demo.entity.cars.Car;
import com.example.demo.repo.car.BrandRepository;
import com.example.demo.repo.car.CarRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cars")
public class CarController {

    private final CarRepository carRepository;
    private final BrandRepository brandRepository;

    public CarController(CarRepository carRepository, BrandRepository brandRepository) {
        this.carRepository = carRepository;
        this.brandRepository = brandRepository;
    }

    @PostMapping("/addcar")
    public ResponseEntity<?> addCars(
            @RequestBody Car car
    ){
        return new ResponseEntity<>(carRepository.save(car), HttpStatus.CREATED);
    }

    @PostMapping("cars")
    public List<Car> searchCars(
            @RequestParam String details
    ){
        return carRepository.searchCarJoins(details);
    }

}
