package com.example.location.service;

import com.example.location.model.Geodata;
import com.example.location.model.Weather;
import com.example.location.repository.GeodataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class GeodataService {
    @Autowired
    GeodataRepository repository;

    @Autowired
    RestTemplate restTemplate;

    //Перенаправление на сервис weather
    public Weather getWeather(String location){
        Geodata geodata = repository.findByName(location).get();
        String url = String.format("http://localhost:8082/weather/lat=%s&lon=%s", geodata.getLat(), geodata.getLon());
        return restTemplate.getForObject(url, Weather.class);
    }

    public Optional<Geodata> getGeodata(String locationName){
        return repository.findByName(locationName);
    }

    public Geodata saveGeodata(Geodata geodata){
        return repository.save(geodata);
    }
}
