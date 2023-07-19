package com.wheelEstate.dao;

import java.util.List;

import com.wheelEstate.entity.Car;
import com.wheelEstate.exceptions.CarNotAvailableException;
import com.wheelEstate.exceptions.NoRecordFoundException;
import com.wheelEstate.exceptions.SomethingWentWrongException;

public interface CarDAO {
	Car addCar(Car car) throws SomethingWentWrongException;

	Car updateCar(Car car) throws CarNotAvailableException, SomethingWentWrongException;

	void deleteCar(Long carId) throws NoRecordFoundException, SomethingWentWrongException;

	List<Car> getAllCars();

	Car getCarById(Long carId) throws NoRecordFoundException;

	List<Car> searchCarsByBrand(String brand);

	List<Car> searchCarsByModel(String model);

	List<Car> searchCarsByYear(int year);
}
