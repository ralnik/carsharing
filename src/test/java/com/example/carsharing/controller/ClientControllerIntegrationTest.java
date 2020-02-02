package com.example.carsharing.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.example.carsharing.entity.Client;
import com.example.carsharing.entity.EntityForDeleteClient;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ClientControllerIntegrationTest {

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
    public void testGetAllClients() {
    HttpHeaders headers = new HttpHeaders();
       HttpEntity<String> entity = new HttpEntity<String>(null, headers);
       ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/client",
       HttpMethod.GET, entity, String.class);  
       assertNotNull(response.getBody());
   }
	
	@Test
    public void testGetAllNotes() {
        Client client = restTemplate.getForObject(getRootUrl() + "/clients", Client.class);
        System.out.println(client.getFio());
        assertNotNull(client);
    }
	
	@Test
	public void testCreateClient() {
		Client car = new Client("Петров П.П.", 2000, Long.valueOf(1));
		ResponseEntity<Client> postResponse = restTemplate.postForEntity(getRootUrl() + "/insert_car", car, Client.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
	}
	
	@Test
	public void testDeleteCar() {		
		EntityForDeleteClient entityForDeleteClient = restTemplate.getForObject(getRootUrl() + "/delete_car", EntityForDeleteClient.class);
        assertNotNull(entityForDeleteClient);
        restTemplate.delete(getRootUrl() + "/delete_car", entityForDeleteClient);
        try {
        	entityForDeleteClient = restTemplate.getForObject(getRootUrl() + "/delete_car", EntityForDeleteClient.class);
        } catch (final HttpClientErrorException e) {
             assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
	}

}
