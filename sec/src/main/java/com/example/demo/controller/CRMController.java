package com.example.demo.controller;

import com.example.demo.entity.evaluation.Agent;
import com.example.demo.entity.evaluation.Area;
import com.example.demo.entity.evaluation.CustomerVisit;
import com.example.demo.repo.AgentRepository;
import com.example.demo.repo.AreaRepository;
import com.example.demo.repo.CustomerVisitRepository;
import com.example.demo.service.SmsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/crm")
public class CRMController {
    private final AreaRepository areaRepository;
    private final AgentRepository agentRepository;
    private final CustomerVisitRepository customerVisitRepository;
    private final SmsService smsService;

    public CRMController(AreaRepository areaRepository, AgentRepository agentRepository, CustomerVisitRepository customerVisitRepository, SmsService smsService) {
        this.areaRepository = areaRepository;
        this.agentRepository = agentRepository;
        this.customerVisitRepository = customerVisitRepository;
        this.smsService = smsService;
    }

    @GetMapping
    public ResponseEntity<List<Area>> searchAgent(
            @RequestParam int pinCode        //search agent based on pincode
    ){
        return new ResponseEntity<>(areaRepository.findByPinCode(pinCode), HttpStatus.OK);
    }

    @PutMapping
    public String allocateAgent(
            @RequestParam long customerId,
            @RequestParam long agentId
    ){
        Agent agent=null;
        Optional<Agent> opAgent = agentRepository.findById(agentId);
        if(opAgent.isPresent()){
            agent = opAgent.get();
        }else{
            return "agent not found";
        }
        CustomerVisit customerVisit = customerVisitRepository.findById(customerId).get();
        customerVisit.setAgent(agent);
        customerVisitRepository.save(customerVisit);
        smsService.sendSms("+918296202624","heyyyyy there YOUR AGENT IS ALLOCATED!");
        return "agent allocated";
    }
}
