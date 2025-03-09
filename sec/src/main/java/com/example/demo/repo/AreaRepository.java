package com.example.demo.repo;

import com.example.demo.entity.evaluation.Agent;
import com.example.demo.entity.evaluation.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AreaRepository extends JpaRepository<Area, Long> {

//    @Query("select ag from Area a join a.agent ag" +
//            "where a.pinCode=:pinCode")
//    List<Agent> searchAgent(
//            @Param("pinCode") int pinCode
//    );

    List<Area> findByPinCode(int pinCode);
}
