package com.example.location.repository;

import com.example.location.model.Geodata;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface GeodataRepository extends CrudRepository<Geodata, Integer> {
    Optional<Geodata> findByName(String name);
}
