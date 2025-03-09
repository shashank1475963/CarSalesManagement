package com.example.demo.controller.car;

import com.example.demo.entity.cars.Transmission;
import com.example.demo.repo.car.TransmissionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/transmission")
public class TransmissionController {

    private final TransmissionRepository transmissionRepository;

    public TransmissionController(TransmissionRepository transmissionRepository) {
        this.transmissionRepository = transmissionRepository;
    }

    @PostMapping("/addTransmission")
    public ResponseEntity<?> addTransmission(
            @RequestBody Transmission transmission
            ){
        return new ResponseEntity<>(transmissionRepository.save(transmission), HttpStatus.CREATED);
    }
}
