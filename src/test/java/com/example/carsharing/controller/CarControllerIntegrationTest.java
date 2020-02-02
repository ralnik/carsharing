package com.example.carsharing.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import com.example.carsharing.DemoApplication;
import com.example.carsharing.entity.Car;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CarControllerIntegrationTest {
	@Autowired
    private TestRestTemplate restTemplate;
	
	@LocalServerPort
    private int port;
	
	private String getRootUrl() {
        return "http://localhost:" + port;
    }
	
	@Test
    public void contextLoads() {

    }
	
	@Test
    public void testGetAllCars() {
    HttpHeaders headers = new HttpHeaders();
       HttpEntity<String> entity = new HttpEntity<String>(null, headers);
       ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/car",
       HttpMethod.GET, entity, String.class);  
       assertNotNull(response.getBody());
   }
	
	@Test
    public void testGetAllNotes() {
        Car car = restTemplate.getForObject(getRootUrl() + "/cars", Car.class);
        System.out.println(car.getModel());
        assertNotNull(car);
    }
	
	@Test
	public void testCreateCar() {
		Car car = new Car("honda", 2011, "Иванов И.И.");
		ResponseEntity<Car> postResponse = restTemplate.postForEntity(getRootUrl() + "/insert_car", car, Car.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
	}
	
	@Test
	public void testDeleteCar() {
		int id = 2;
		Car car = restTemplate.getForObject(getRootUrl() + "/delete_car/" + id, Car.class);
        assertNotNull(car);
        restTemplate.delete(getRootUrl() + "/delete_car/" + id);
        try {
             car = restTemplate.getForObject(getRootUrl() + "/delete_car/" + id, Car.class);
        } catch (final HttpClientErrorException e) {
             assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
	}

}
