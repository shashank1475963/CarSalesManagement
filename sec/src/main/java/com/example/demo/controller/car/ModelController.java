package com.example.demo.controller.car;

import com.example.demo.entity.cars.Model;
import com.example.demo.repo.car.ModelRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/model")
public class ModelController {

    private ModelRepository modelRepository;

    public ModelController(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    @PostMapping("/addModel")
    public ResponseEntity<?> addModel(
            @RequestBody Model model
            ){
        return new ResponseEntity<>(modelRepository.save(model), HttpStatus.CREATED);
    }
}
