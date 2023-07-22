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

/**
 * This class implements the CarDAO interface and provides methods to interact
 * with the database to perform CRUD operations for Car entities.
 */
public class CarDAOImpl implements CarDAO { // this class is done without errors

	/**
	 * Adds a new Car entity to the database.
	 *
	 * @param car The Car entity to be added.
	 * @return The Car entity that was added to the database.
	 * @throws SomethingWentWrongException If something unexpected happens during
	 *                                     the database operation.
	 */
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

	/**
	 * Updates the details of an existing Car entity in the database.
	 *
	 * @param cr The Car entity containing updated details.
	 * @return The updated Car entity.
	 * @throws CarNotAvailableException    If the Car with the specified ID is not
	 *                                     available in the database.
	 * @throws SomethingWentWrongException If something unexpected happens during
	 *                                     the database operation.
	 */
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

	/**
	 * Deletes a Car entity from the database based on the given carId.
	 *
	 * @param carId The ID of the Car entity to be deleted.
	 * @throws NoRecordFoundException      If no Car entity is found with the given
	 *                                     carId in the database.
	 * @throws SomethingWentWrongException If something unexpected happens during
	 *                                     the database operation.
	 */
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

	/**
	 * Retrieves a list of all Car entities from the database.
	 *
	 * @return A list of all Car entities in the database.
	 * @throws SomethingWentWrongException If something unexpected happens during
	 *                                     the database operation.
	 */
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

	/**
	 * Retrieves a specific Car entity from the database based on the given carId.
	 *
	 * @param carId The ID of the Car entity to be retrieved.
	 * @return The Car entity with the specified carId.
	 * @throws NoRecordFoundException If no Car entity is found with the given carId
	 *                                in the database.
	 */
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

	/**
	 * Searches for Car entities in the database based on the specified brand name.
	 *
	 * @param brand The brand name of the Car entities to be searched for.
	 * @return A list of Car entities matching the specified brand name.
	 * @throws SomethingWentWrongException If something unexpected happens during
	 *                                     the database operation.
	 */
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

	/**
	 * Searches for Car entities in the database based on the specified model name.
	 *
	 * @param model The model name of the Car entities to be searched for.
	 * @return A list of Car entities matching the specified model name.
	 * @throws NoRecordFoundException If no Car entity is found with the given model
	 *                                name in the database.
	 */
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

	/**
	 * Searches for Car entities in the database based on the specified price range.
	 *
	 * @param start The starting price of the Car entities to be searched for.
	 * @param end   The ending price of the Car entities to be searched for.
	 * @return A list of Car entities matching the specified price range.
	 * @throws SomethingWentWrongException If something unexpected happens during
	 *                                     the database operation.
	 */
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
