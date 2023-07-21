package com.wheelEstate.dao;

import java.time.LocalDate;
import java.util.List;

import com.wheelEstate.Utility.Util;
import com.wheelEstate.entity.Car;
import com.wheelEstate.entity.Reservation;
import com.wheelEstate.exceptions.CarNotAvailableException;
import com.wheelEstate.exceptions.NoRecordFoundException;
import com.wheelEstate.exceptions.SomethingWentWrongException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

public class ReservartionDAOImpl implements ReservationDAO {

	@Override
	public Reservation makeReservation(Reservation reservation)
			throws CarNotAvailableException, SomethingWentWrongException {
		EntityManager em = null;
		EntityTransaction et = null;

		try {
			em = Util.getEm();
			et = em.getTransaction();
			et.begin();

			// Step 1: Check if the car is available for the reservation
			Long carId = reservation.getCar().getCarId();
			Car car = em.find(Car.class, carId);
			if (car == null) {
				throw new NoRecordFoundException("No car found with the given ID: " + carId);
			}

			if (!car.isAvailability()) {
				throw new CarNotAvailableException("Car is not available for reservation.");
			}

			// Step 2: Update car's availability to false
			car.setAvailability(false);

			em.merge(car);
			reservation.setPayment(null);

			// Step 3: Persist the reservation in the database
			em.persist(reservation);

			et.commit();
			return reservation;
		} catch (Exception e) {
			et.rollback();
			throw new SomethingWentWrongException(
					"Something went wrong while making the reservation. Try again later.");
		} finally {
			em.close();
		}
	}

	@Override
	public void cancelReservation(Long reservationId) throws NoRecordFoundException, SomethingWentWrongException {
		EntityManager em = null;
		EntityTransaction et = null;

		try {
			em = Util.getEm();
			et = em.getTransaction();
			et.begin();

			// Find the reservation in the database
			Reservation canceledReservation = em.find(Reservation.class, reservationId);

			if (canceledReservation != null) {
				// Mark the reservation as cancelled
				canceledReservation.setCancelled(true);

				// Retrieve the car associated with the reservation
				Car car = canceledReservation.getCar();

				// Update the car's availability to true
				car.setAvailability(true);

				// Save the changes to the reservation and car
				em.merge(canceledReservation);
				em.merge(car);

				// Commit the transaction
				et.commit();

			} else {
				throw new NoRecordFoundException("No reservation found with the given ID: " + reservationId);
			}

		} catch (Exception e) {
			et.rollback();
			throw new SomethingWentWrongException(
					"Something went wrong while cancelling the reservation. Try again later.");
		} finally {
			em.close();
		}

	}

	@Override
	public List<Reservation> getAllReservations() throws SomethingWentWrongException {
		EntityManager em = null;
		try {
			em = Util.getEm();
			String jpql = "SELECT r FROM Reservation r";
			Query query = em.createQuery(jpql);
			List<Reservation> reservations = query.getResultList();
			return reservations;
		} catch (Exception e) {
			throw new SomethingWentWrongException(
					"Something went wrong while retrieving all reservations. Try again later.");
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	@Override
	public Reservation getReservationById(Long reservationId) throws NoRecordFoundException {
		EntityManager em = null;
		EntityTransaction et = null;

		try {
			em = Util.getEm();
			et = em.getTransaction();
			Reservation rev = em.find(Reservation.class, reservationId);
			et.begin();
			if (rev != null) {
				et.commit();
				return rev;
			} else {
				throw new NoRecordFoundException("Invalid Reservation ID.");
			}

		} catch (Exception e) {
			et.rollback();
			throw new NoRecordFoundException("Something went wrong while adding Details. Try again later.");
		} finally {
			em.close();
		}
	}

	@Override
	public List<Reservation> getReservationsByCustomer(Long customerId) throws NoRecordFoundException {
		EntityManager em = null;
		try {
			em = Util.getEm();
			String jpql = "SELECT r FROM Reservation r WHERE r.customer.customerId = :customerId";
			Query query = em.createQuery(jpql);
			query.setParameter("customerId", customerId);
			List<Reservation> reservations = query.getResultList();
			if (reservations.isEmpty()) {
				throw new NoRecordFoundException("No reservations found for customer with ID: " + customerId);
			}
			return reservations;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	@Override
	public List<Reservation> getReservationsByCar(Long carId) throws NoRecordFoundException {
		EntityManager em = null;
		try {
			em = Util.getEm();
			String jpql = "SELECT r FROM Reservation r WHERE r.car.carId = :carId";
			Query query = em.createQuery(jpql);
			query.setParameter("carId", carId);
			List<Reservation> reservations = query.getResultList();
			if (reservations.isEmpty()) {
				throw new NoRecordFoundException("No reservations found for car with ID: " + carId);
			}
			return reservations;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	@Override
	public List<Reservation> getReservationsBetweenDates(LocalDate startDate, LocalDate endDate)
			throws SomethingWentWrongException {
		EntityManager em = null;
		try {
			em = Util.getEm();
			String jpql = "SELECT r FROM Reservation r WHERE r.startDate >= :startDate AND r.endDate <= :endDate";
			Query query = em.createQuery(jpql);
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
			List<Reservation> reservations = query.getResultList();
			return reservations;
		} catch (Exception e) {
			throw new SomethingWentWrongException(
					"Something went wrong while retrieving reservations between dates. Try again later.");
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

}
