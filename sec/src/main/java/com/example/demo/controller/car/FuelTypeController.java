package com.example.demo.controller.car;

import com.example.demo.entity.cars.FuelType;
import com.example.demo.repo.car.FuelTypeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/fuelType")
public class FuelTypeController {

    private final FuelTypeRepository fuelTypeRepository;

    public FuelTypeController(FuelTypeRepository fuelTypeRepository) {
        this.fuelTypeRepository = fuelTypeRepository;
    }

    @PostMapping("/addFuelType")
    public ResponseEntity<?> addFueltype(
            @RequestBody FuelType fuelType
            ){

        return new ResponseEntity<>(fuelTypeRepository.save(fuelType), HttpStatus.CREATED);
    }
}
