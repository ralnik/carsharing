package com.example.carsharing.entity;

import javax.persistence.*;

@Entity
@Table(name = "client")
public class Client {
	@Id
	@GeneratedValue
	private long idClient;
	
	@Column(nullable = false)
	private String fio;
	
	@Column
	private int yearBirthday;
	
	@Column(nullable = false)
	private Long car;
	

	public Client(String fio, int yearBirthday, Long car) {
		super();
		this.idClient = idClient;
		this.fio = fio;
		this.yearBirthday = yearBirthday;
		this.car = car;
	}

	public Client() {
		super();
	}

	public long getIdClient() {
		return idClient;
	}

	public void setIdClient(long idClient) {
		this.idClient = idClient;
	}

	public String getFio() {
		return fio;
	}

	public void setFio(String fio) {
		this.fio = fio;
	}

	public int getYearBirthday() {
		return yearBirthday;
	}

	public void setYearBirthday(int yearBirthday) {
		this.yearBirthday = yearBirthday;
	}

	public Long getCar() {
		return car;
	}

	public void setCar(Long car) {
		this.car = car;
	}
	
	
	

}
