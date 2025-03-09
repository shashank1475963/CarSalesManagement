package com.example.demo.controller;

import com.example.demo.service.GeoLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/location")
public class LocationController {

    @Autowired
    private GeoLocationService geoLocationService;

    @GetMapping
    public String getLocation() {
        return geoLocationService.getLocation();
    }
}
