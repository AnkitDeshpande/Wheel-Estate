package com.wheelEstate.dao;

import java.util.List;

import com.wheelEstate.Utility.Util;
import com.wheelEstate.entity.Car;
import com.wheelEstate.exceptions.CarNotAvailableException;
import com.wheelEstate.exceptions.NoRecordFoundException;
import com.wheelEstate.exceptions.SomethingWentWrongException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

public class CarDAOImpl implements CarDAO { // this class is done without errors

	@Override
	public Car addCar(Car car) throws SomethingWentWrongException {
		EntityManager em = null;
		EntityTransaction et = null;

		try {
			em = Util.getEm();
			et = em.getTransaction();

			et.begin();
			em.persist(car);
			et.commit();
			return car;
		} catch (Exception e) {
			et.rollback();
			throw new SomethingWentWrongException("Something went wrong while adding Details. Try again later.");
		} finally {
			em.close();
		}
	}

	@Override
	public Car updateCar(Car cr) throws CarNotAvailableException, SomethingWentWrongException {
		EntityManager em = null;
		EntityTransaction et = null;

		try {
			em = Util.getEm();
			et = em.getTransaction();
			Car car = em.find(Car.class, cr.getCarId());
			et.begin();
			if (car != null) {
				em.merge(cr);
				et.commit();
				return cr;
			} else {
				throw new NoRecordFoundException("No record found with the given ID : " + cr.getCarId());
			}

		} catch (Exception e) {
			et.rollback();
			throw new CarNotAvailableException("car is Not available at the moment. Try again later.");
		} finally {
			em.close();
		}
	}

	@Override
	public void deleteCar(Long carId) throws NoRecordFoundException, SomethingWentWrongException {
		EntityManager em = null;
		EntityTransaction et = null;

		try {
			em = Util.getEm();
			et = em.getTransaction();
			Car car = em.find(Car.class, carId);
			et.begin();
			if (car != null) {
				em.remove(car);
				et.commit();
			} else {
				throw new NoRecordFoundException("No record found with the given ID : " + carId);
			}

		} catch (Exception e) {
			et.rollback();
			// throw new NoRecordFoundException("Something went wrong while adding Details.
			// Try again later.");
			e.printStackTrace();
		} finally {
			em.close();
		}

	}

	@Override
	public List<Car> getAllCars() throws SomethingWentWrongException {
		EntityManager em = null;
		EntityTransaction et = null;

		try {
			em = Util.getEm();
			et = em.getTransaction();
			Query query = em.createQuery("SELECT c FROM Car c");
			et.begin();
			List<Car> list = (List<Car>) query.getResultList();
			if (list != null) {
				et.commit();
				return list;
			} else {
				throw new SomethingWentWrongException("List is empty.");
			}

		} catch (Exception e) {
			et.rollback();
			throw new SomethingWentWrongException("Something went wrong while fetching car Details. Try again later.");
		} finally {
			em.close();
		}
	}

	@Override
	public Car getCarById(Long carId) throws NoRecordFoundException {
		EntityManager em = null;
		EntityTransaction et = null;

		try {
			em = Util.getEm();
			et = em.getTransaction();
			Car car = em.find(Car.class, carId);
			et.begin();
			if (car != null) {
				et.commit();
				return car;
			} else {
				throw new NoRecordFoundException("No record found with the given ID : " + car.getCarId());
			}

		} catch (Exception e) {
			et.rollback();
			throw new NoRecordFoundException("Something went wrong while adding Details. Try again later.");
		} finally {
			em.close();
		}
	}

	@Override
	public List<Car> searchCarsByBrand(String brand) throws SomethingWentWrongException {
		EntityManager em = null;
		try {
			em = Util.getEm();
			Query query = em.createQuery("SELECT c FROM Car c WHERE c.brand = :brand");
			query.setParameter("brand", brand);
			return query.getResultList();
		} catch (Exception e) {
			throw new SomethingWentWrongException("Something went wrong while fetching car details. Try again later.");
		} finally {
			em.close();

		}
	}

	@Override
	public List<Car> searchCarsByModel(String model) throws NoRecordFoundException {
		EntityManager em = null;
		try {
			em = Util.getEm();
			Query query = em.createQuery("SELECT c FROM Car c WHERE c.model = :model");
			query.setParameter("model", model);
			List<Car> cars = query.getResultList();
			if (cars.isEmpty()) {
				throw new NoRecordFoundException("No cars found with the given model: " + model);
			}
			return cars;
		} catch (NoRecordFoundException e) {
			throw new NoRecordFoundException("No cars found with the given model: " + model);
		} finally {
			em.close();

		}
	}

	@Override
	public List<Car> searchCarsByPrice(double start, double end) throws SomethingWentWrongException {
		EntityManager em = null;
		try {
			em = Util.getEm();
			Query query = em.createQuery("SELECT c FROM Car c WHERE c.price BETWEEN :start AND :end");
			query.setParameter("start", start);
			query.setParameter("end", end);
			return query.getResultList();
		} catch (Exception e) {
			throw new SomethingWentWrongException("Something went wrong while fetching car details. Try again later.");
		} finally {
			em.close();
		}
	}

}
