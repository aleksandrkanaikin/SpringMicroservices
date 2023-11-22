package com.example.person.controller;

import com.example.person.model.Person;
import com.example.person.model.Weather;
import com.example.person.repository.PersonRepository;
import com.example.person.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private PersonService service;

    @GetMapping("{id}/weather")
    public ResponseEntity<Weather> getWeather(@PathVariable int id) {
        Weather weather = service.getWeather(id);
        if (weather != null) {
            return new ResponseEntity(weather, HttpStatus.OK);
        }
        else return new ResponseEntity(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public Iterable<Person> findAll(){
        return service.getAllPersons();
    }

    @GetMapping("/{id}")
    public Optional<Person> findById(@PathVariable int id){
        return service.getPerson(id);
    }

    @PostMapping
    public ResponseEntity<Person> save(@RequestBody Person person){
        return service.savePerson(person) == null
                ? new ResponseEntity(service.savePerson(person), HttpStatus.BAD_REQUEST)
                : new ResponseEntity(service.savePerson(person), HttpStatus.CREATED);
    }
}
