package com.example.carsharing.exceptions;

public class CarNotFoundExeption extends Exception{
	
	public CarNotFoundExeption(long idCar) {
		super(String.format("Car is not found with id : %s", idCar));
	}
	
	public CarNotFoundExeption(String model) {
		super(String.format("Car is not found with model : %s", model));
	}
}
