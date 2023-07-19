package com.wheelEstate.service;

import java.util.List;

import com.wheelEstate.dao.CarDAO;
import com.wheelEstate.dao.CarDAOImpl;
import com.wheelEstate.entity.Car;
import com.wheelEstate.exceptions.CarNotAvailableException;
import com.wheelEstate.exceptions.NoRecordFoundException;
import com.wheelEstate.exceptions.SomethingWentWrongException;

public class CarServiceImpl implements CarService {

	@Override
	public Car addCar(Car car) throws SomethingWentWrongException {
		CarDAO cd = new CarDAOImpl();
		return cd.addCar(car);
	}

	@Override
	public Car updateCar(Car car) throws CarNotAvailableException, SomethingWentWrongException {
		CarDAO cd = new CarDAOImpl();
		return cd.updateCar(car);
	}

	@Override
	public void deleteCar(Long carId) throws NoRecordFoundException, SomethingWentWrongException {
		CarDAO cd = new CarDAOImpl();
		cd.deleteCar(carId);
	}

	@Override
	public List<Car> getAllCars() {
		CarDAO cd = new CarDAOImpl();
		return cd.getAllCars();
	}

	@Override
	public Car getCarById(Long carId) throws NoRecordFoundException {
		CarDAO cd = new CarDAOImpl();
		return cd.getCarById(carId);
	}

	@Override
	public List<Car> searchCarsByBrand(String brand) {
		CarDAO cd = new CarDAOImpl();
		return cd.searchCarsByBrand(brand);
	}

	@Override
	public List<Car> searchCarsByModel(String model) {
		CarDAO cd = new CarDAOImpl();
		return cd.searchCarsByModel(model);
	}

	@Override
	public List<Car> searchCarsByYear(int year) {
		CarDAO cd = new CarDAOImpl();
		return cd.searchCarsByYear(year);
	}

}
