package com.example.carsharing.db;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.After;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.carsharing.entity.Car;
import com.example.carsharing.entity.Client;

@RunWith(SpringRunner.class)
@SpringBootTest
class ClientRepositoryTest {
	
	@Autowired
    private ClientRepository clientRepository;
	
	@Test
	public void clientRepositoryTest() throws Exception{
		assertThat(clientRepository).isNotNull();
	}

	@Test
	void testFindAll() {
		List<Client> client = clientRepository.findAll();
    	assertEquals(1,client.size());
	}
	
	@Test
	void testFindByFio() {
		Client client = clientRepository.findByFio("Иванов И.И.");
		assertEquals("Иванов И.И.", client.getFio());
	}


	@Test
	void testSave() {
		Client client = new Client("Петров П.П.", 2000, Long.valueOf(1));
    	clientRepository.save(client);
    	Client findedClient = clientRepository.findByFio("Петров П.П.");
    	assertThat(findedClient).isNotNull();
	}

	@Test
	void testDelete() {
		Client client = new Client("Сидоров С.С.", 2001, Long.valueOf(1));
		clientRepository.save(client);
    	
		Client findedСlient = clientRepository.findByFio("Сидоров С.С.");
		clientRepository.delete(findedСlient);
    	
    	Client deletedClient = clientRepository.findByFio("Сидоров С.С.");
    	
    	assertEquals(null, deletedClient); 
	}

	@After
    public void afterTesting() {
		Client findedClient = clientRepository.findByFio("Петров П.П.");
    	clientRepository.delete(findedClient);
    }
}
