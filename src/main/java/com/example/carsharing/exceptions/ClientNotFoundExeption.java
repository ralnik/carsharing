package com.example.carsharing.exceptions;

public class ClientNotFoundExeption extends Exception{
	
	public ClientNotFoundExeption(long idClient) {
		super(String.format("Client is not found with id : %s", idClient));
	}
	
	public ClientNotFoundExeption(String fio) {
		super(String.format("Client is not found by %s", fio));
	}
}