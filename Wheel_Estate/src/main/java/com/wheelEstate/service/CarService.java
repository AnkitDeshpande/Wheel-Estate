package com.wheelEstate.service;

import java.util.List;

import com.wheelEstate.entity.Car;
import com.wheelEstate.exceptions.CarNotAvailableException;
import com.wheelEstate.exceptions.NoRecordFoundException;
import com.wheelEstate.exceptions.SomethingWentWrongException;

public interface CarService {
	Car addCar(Car car) throws SomethingWentWrongException;

	Car updateCar(Car car) throws CarNotAvailableException, SomethingWentWrongException;

	void deleteCar(Long carId) throws NoRecordFoundException, SomethingWentWrongException;

	List<Car> getAllCars() throws SomethingWentWrongException;

	Car getCarById(Long carId) throws NoRecordFoundException;

	List<Car> searchCarsByBrand(String brand) throws SomethingWentWrongException;

	List<Car> searchCarsByModel(String model) throws NoRecordFoundException;

	List<Car> searchCarsByPrice(double start, double end) throws SomethingWentWrongException;
}
