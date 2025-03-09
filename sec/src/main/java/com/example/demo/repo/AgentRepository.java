package com.example.demo.repo;

import com.example.demo.entity.evaluation.Agent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgentRepository extends JpaRepository<Agent, Long> {
}