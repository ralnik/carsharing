package com.example.carsharing.entity;

public class CarOfClient {
	private String fio;
	private int yearBirthday;
	private String modelCar;
	private int yearCar;
	
	
public CarOfClient(String fio, int yearBirthday, String modelCar, int yearCar) {
	super();
	this.fio = fio;
	this.yearBirthday = yearBirthday;
	this.modelCar = modelCar;
	this.yearCar = yearCar;
}



public String getFio() {
	return fio;
}



public void setFio(String fio) {
	this.fio = fio;
}



public int getYearBirhtday() {
	return yearBirthday;
}



public void setYearBirhtday(int yearBirhtday) {
	this.yearBirthday = yearBirhtday;
}



public String getModelCar() {
	return modelCar;
}



public void setModelCar(String modelCar) {
	this.modelCar = modelCar;
}



public int getYearCar() {
	return yearCar;
}



public void setYearCar(int yearCar) {
	this.yearCar = yearCar;
}


}
