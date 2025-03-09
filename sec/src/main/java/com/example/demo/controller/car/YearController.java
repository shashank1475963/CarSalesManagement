package com.example.demo.controller.car;

import com.example.demo.entity.cars.Transmission;
import com.example.demo.repo.car.TransmissionRepository;
import com.example.demo.repo.car.YearRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/year")
public class YearController {

    private final YearRepository yearRepository;
    private final TransmissionRepository transmissionRepository;

    public YearController(YearRepository yearRepository,
                          TransmissionRepository transmissionRepository) {
        this.yearRepository = yearRepository;
        this.transmissionRepository = transmissionRepository;
    }

    @PostMapping("/addTransmission")
    public ResponseEntity<?> addTransmission(
            @RequestBody Transmission transmission
            ){
        return new ResponseEntity<>(transmissionRepository.save(transmission), HttpStatus.CREATED);
    }

}
