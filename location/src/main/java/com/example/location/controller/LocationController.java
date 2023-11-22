package com.example.location.controller;

import com.example.location.model.Geodata;
import com.example.location.model.Weather;
import com.example.location.repository.GeodataRepository;
import com.example.location.service.GeodataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@RestController
@RequestMapping("/location")
public class LocationController {
    @Autowired
    private GeodataService service;

    //Перенаправление на сервис weather
    @GetMapping("/weather")
    public Weather redirectRequestWeather(@RequestParam String location) {
        return service.getWeather(location);
    }

    @GetMapping
    public Optional<Geodata> getWeather(@RequestParam String location) {
        return service.getGeodata(location);
    }

    @PostMapping
    public Geodata save(@RequestBody Geodata geodata) {
        return service.saveGeodata(geodata);
    }
}
