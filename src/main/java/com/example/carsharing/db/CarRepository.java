package com.example.carsharing.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.carsharing.entity.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

	Car findByModel(String model);
}
