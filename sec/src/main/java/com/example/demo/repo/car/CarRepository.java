package com.example.demo.repo.car;

import com.example.demo.entity.cars.Brand;
import com.example.demo.entity.cars.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {

    @Query(
            "select c from Car c where c.brand=:brand"
    )
    List<Car> searchCar(
            @Param("brand") Brand brand
    );

    @Query(
            "select c from Car c join c.brand b " +
                    " join c.transmission t " +
                    " join c.model m"+
                    " join c.year y"+
                    " where b.name=:details or t.type=:details or m.name=:details or y.year>=:details"
    )
    List<Car> searchCarJoins(
            @Param("details") String details
    );
}