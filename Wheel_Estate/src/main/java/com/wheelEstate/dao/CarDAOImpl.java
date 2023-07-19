package com.wheelEstate.dao;

import java.util.List;

import com.wheelEstate.entity.Car;
import com.wheelEstate.exceptions.CarNotAvailableException;
import com.wheelEstate.exceptions.NoRecordFoundException;
import com.wheelEstate.exceptions.SomethingWentWrongException;

public class CarDAOImpl implements CarDAO {

	@Override
	public Car addCar(Car car) throws SomethingWentWrongException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Car updateCar(Car car) throws CarNotAvailableException, SomethingWentWrongException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteCar(Long carId) throws NoRecordFoundException, SomethingWentWrongException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Car> getAllCars() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Car getCarById(Long carId) throws NoRecordFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Car> searchCarsByBrand(String brand) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Car> searchCarsByModel(String model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Car> searchCarsByYear(int year) {
		// TODO Auto-generated method stub
		return null;
	}

}
