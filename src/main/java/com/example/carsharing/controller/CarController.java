package com.example.carsharing.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.carsharing.db.CarRepository;
import com.example.carsharing.entity.Car;
import com.example.carsharing.exceptions.CarNotFoundExeption;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/car")
@Api(value = "carController", description = "APIs for working with cars")
public class CarController {
	@Autowired
	CarRepository carRepository;
	
	// Получить все записи
		@ApiOperation(value = "Get all records")
	   //@GetMapping("/cars")
	   @RequestMapping(method = RequestMethod.GET, value = "/cars")	   
	   public List getAllNotes() {
	       return carRepository.findAll();
	   }
	
	@ApiOperation(value = "insert car")
	//@PostMapping("/insert_car")	
	@RequestMapping(method = RequestMethod.POST, value = "/insert_car")
	public Car	createCar(@Valid @RequestBody Car car) {
		return (Car) carRepository.save(car);
	}
	
	@ApiOperation(value = "delete car")
	//@DeleteMapping("/delete_car/{id}")	
	@RequestMapping(method = RequestMethod.DELETE, value = "/delete_car/{id}")
	public ResponseEntity deleteCar(@PathVariable("id") Long idCar) throws CarNotFoundExeption,Throwable{
		Car car = (Car) carRepository.findById(idCar)
				.orElseThrow(() -> new CarNotFoundExeption(idCar));
		carRepository.delete(car);
		return ResponseEntity.ok().build();
	}
	
	
}
