package com.example.person.service;

import com.example.person.model.Person;
import com.example.person.model.Weather;
import com.example.person.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    PersonRepository repository;
    @Autowired
    RestTemplate restTemplate;

    public Weather getWeather(int id){
        if(repository.existsById(id)){
            String location = repository.findById(id).get().getLocation();
            Weather weather = restTemplate.getForObject("http://localhost:8081/location/weather?location=" + location, Weather.class);
            return weather;
        }
        else return null;
    }

    public Iterable<Person> getAllPersons(){
        return repository.findAll();
    }

    public Optional<Person> getPerson(int id){
        return repository.findById(id);
    }

    public Person savePerson(Person person){
        return repository.findById(person.getId()).isPresent()
                ? null
                : repository.save(person);
    }
}
