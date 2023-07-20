package com.wheelEstate.service;

import java.time.LocalDate;
import java.util.List;

import com.wheelEstate.dao.ReservartionDAOImpl;
import com.wheelEstate.dao.ReservationDAO;
import com.wheelEstate.entity.Reservation;
import com.wheelEstate.exceptions.CarNotAvailableException;
import com.wheelEstate.exceptions.NoRecordFoundException;
import com.wheelEstate.exceptions.SomethingWentWrongException;

public class ReservationServiceImpl implements ReservationService {

	@Override
	public Reservation makeReservation(Reservation reservation)
			throws CarNotAvailableException, SomethingWentWrongException {
		ReservationDAO rd = new ReservartionDAOImpl();
		return rd.makeReservation(reservation);
	}

	@Override
	public void cancelReservation(Long reservationId) throws NoRecordFoundException, SomethingWentWrongException {
		ReservationDAO rd = new ReservartionDAOImpl();
		rd.cancelReservation(reservationId);
	}

	@Override
	public List<Reservation> getAllReservations() throws SomethingWentWrongException {
		ReservationDAO rd = new ReservartionDAOImpl();
		return rd.getAllReservations();
	}

	@Override
	public List<Reservation> getReservationsByCustomer(Long customerId) throws NoRecordFoundException {
		ReservationDAO rd = new ReservartionDAOImpl();
		return rd.getReservationsByCustomer(customerId);
	}

	@Override
	public List<Reservation> getReservationsByCar(Long carId) throws NoRecordFoundException {
		ReservationDAO rd = new ReservartionDAOImpl();
		return rd.getReservationsByCar(carId);
	}

	@Override
	public List<Reservation> getReservationsBetweenDates(LocalDate startDate, LocalDate endDate)
			throws SomethingWentWrongException {
		ReservationDAO rd = new ReservartionDAOImpl();
		return rd.getReservationsBetweenDates(startDate, endDate);
	}

	@Override
	public Reservation getReservationById(Long reservationId) throws NoRecordFoundException {
		ReservationDAO rd = new ReservartionDAOImpl();
		return rd.getReservationById(reservationId);
	}

}
