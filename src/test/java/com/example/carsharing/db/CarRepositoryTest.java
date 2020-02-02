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

@RunWith(SpringRunner.class)
@SpringBootTest
class CarRepositoryTest {

	@Autowired
    private CarRepository carRepository;
	
	@Test
    public void findAllTest() {
    	List<Car> cars = carRepository.findAll();
    	assertEquals(1,cars.size());
    }
	@Test
	public void carRepositoryTest() throws Exception{
		assertThat(carRepository).isNotNull();
	}
    
    @Test
    public void findByModelTest() {
        Car car = (Car) carRepository.findByModel("mazda");
        assertEquals(1, car.getId());
    }    
    
    @Test
    public void saveCarTest() {
    	Car car = new Car("toyota", 2011, "Иванов И.И.");
    	carRepository.save(car);
    	Car findedCar = carRepository.findByModel("toyota");
    	assertThat(findedCar).isNotNull();
    }

    @Test
    public void deleteCarTest() {
    	Car car = new Car("honda", 2011, "Иванов И.И.");
    	carRepository.save(car);
    	
    	Car findedCar = carRepository.findByModel("honda");
    	carRepository.delete(findedCar);
    	
    	Car deletedCar = carRepository.findByModel("honda");    	
    	assertEquals(null, deletedCar);    	
    }
    
    @After
    public void afterTesting() {
    	Car findedCar = carRepository.findByModel("toyota");
    	carRepository.delete(findedCar);
    }
}
