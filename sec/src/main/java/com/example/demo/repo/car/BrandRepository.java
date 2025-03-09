package com.example.demo.repo.car;

import com.example.demo.entity.cars.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    Brand findByName(String brand);
}