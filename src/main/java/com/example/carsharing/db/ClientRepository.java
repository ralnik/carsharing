package com.example.carsharing.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.carsharing.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{
	Client findByFio(String fio);
	
//	@Query(" from carsharing.client c where fio=:fio and car=:idCar")
//	Client findClientByFioByIdCar(@Param("fio") String fio, @Param("idCar") Long idCar);
	
}
