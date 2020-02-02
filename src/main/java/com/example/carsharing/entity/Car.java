package com.example.carsharing.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "car")
public class Car {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCar", updatable = false, nullable = false)
	private long id;
	
	@Column(nullable = false)
	private String model;
	
	@Column
	private int year;
	
	@Column(nullable = false)
	private String owner;
	
	

	public Car() {
		super();
	}

	public Car(String model, int year, String owner) {
		super();
		this.id = id;
		this.model = model;
		this.year = year;
		this.owner = owner;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	
}
