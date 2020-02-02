package com.example.carsharing.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.carsharing.db.CarRepository;
import com.example.carsharing.db.ClientRepository;
import com.example.carsharing.entity.Car;
import com.example.carsharing.entity.CarOfClient;
import com.example.carsharing.entity.Client;
import com.example.carsharing.entity.EntityForDeleteClient;
import com.example.carsharing.exceptions.CarNotFoundExeption;
import com.example.carsharing.exceptions.ClientNotFoundExeption;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/client")
@Api(value = "ClientController", description = "APIs for working with clients")
public class ClientController {
	@Autowired
	ClientRepository clientRepository;
	@Autowired
	CarRepository carRepository;
	
	// Получить все записи
		@ApiOperation(value = "Get all clients")
	   @RequestMapping(method = RequestMethod.GET, value = "/clients")	
	   public List getAllNotes() {
	       return clientRepository.findAll();
	   }
	
		@ApiOperation(value = "insert new client")
		@RequestMapping(method = RequestMethod.POST, value = "/insert_client")
	public Client createClient(@Valid @RequestBody CarOfClient carOfClient) throws CarNotFoundExeption, ClientNotFoundExeption {
		System.out.println("model: " + carOfClient.getModelCar());
		System.out.println("fio: " + carOfClient.getFio());
		System.out.println("birthday: " + carOfClient.getYearBirhtday());
		Car car = (Car) carRepository.findByModel(carOfClient.getModelCar());
		if (car == null) {
			car = new Car(carOfClient.getModelCar(),carOfClient.getYearCar(), carOfClient.getFio());
			carRepository.save(car);
			//throw new CarNotFoundExeption(carOfClient.getModelCar());		
		}
		car = (Car) carRepository.findByModel(carOfClient.getModelCar());
		Client client = clientRepository.findByFio(carOfClient.getFio());
		if(client == null) {
			client = new Client();
			client.setFio(carOfClient.getFio());
			client.setYearBirthday(carOfClient.getYearBirhtday());
			client.setCar(car.getId());
			//throw new ClientNotFoundExeption(carOfClient.getFio());
		}
		return (Client) clientRepository.save(client);
	}
	
	@ApiOperation(value = "delete client")	
	@RequestMapping(method = RequestMethod.DELETE, value = "/delete_client")
	public ResponseEntity deleteClient(@Valid @RequestBody EntityForDeleteClient response) throws ClientNotFoundExeption,Throwable{
		Car car = carRepository.findByModel(response.getModelCar());
		if(car == null) {
			throw new CarNotFoundExeption(response.getModelCar());
		}
		
		//Client client = (Client) clientRepository.findClientByFioByIdCar(response.getFio(), car.getId());
		Client client = (Client) clientRepository.findByFio(response.getFio());
		if(client == null) {
			throw new ClientNotFoundExeption(response.getFio());
		}
		if(client.getCar() == car.getId()) {
			clientRepository.delete(client);
			car.setOwner("");
			carRepository.save(car);
		}
		return ResponseEntity.ok().build();
	}
	
	
}