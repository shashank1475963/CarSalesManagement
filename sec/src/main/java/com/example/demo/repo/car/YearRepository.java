package com.example.demo.repo.car;

import com.example.demo.entity.cars.Year;
import org.springframework.data.jpa.repository.JpaRepository;

public interface YearRepository extends JpaRepository<Year, Long> {

}