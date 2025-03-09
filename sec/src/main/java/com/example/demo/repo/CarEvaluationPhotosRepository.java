package com.example.demo.repo;

import com.example.demo.entity.evaluation.CarEvaluationPhotos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarEvaluationPhotosRepository extends JpaRepository<CarEvaluationPhotos, Long> {
}