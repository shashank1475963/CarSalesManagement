package com.example.demo.controller.car;

import com.example.demo.entity.cars.Brand;
import com.example.demo.repo.car.BrandRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/brand")
public class BrandController {

    private final BrandRepository brandRepository;

    public BrandController(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @PostMapping("/addBrand")
    public ResponseEntity<Brand> enterBrand(
            @RequestBody Brand brand
    ){
       return new ResponseEntity<>(brandRepository.save(brand),HttpStatus.CREATED);
    }

}
