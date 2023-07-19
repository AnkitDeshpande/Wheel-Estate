package com.wheelEstate.dao;

import java.time.LocalDate;
import java.util.List;

import com.wheelEstate.entity.Reservation;
import com.wheelEstate.exceptions.CarNotAvailableException;
import com.wheelEstate.exceptions.NoRecordFoundException;
import com.wheelEstate.exceptions.SomethingWentWrongException;

public class ReservartionDAOImpl implements ReservationDAO {

	@Override
	public Reservation makeReservation(Reservation reservation)
			throws CarNotAvailableException, SomethingWentWrongException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void cancelReservation(Long reservationId) throws NoRecordFoundException, SomethingWentWrongException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Reservation> getAllReservations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reservation getReservationById(Long reservationId) throws NoRecordFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reservation> getReservationsByCustomer(Long customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reservation> getReservationsByCar(Long carId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reservation> getReservationsBetweenDates(LocalDate startDate, LocalDate endDate) {
		// TODO Auto-generated method stub
		return null;
	}

}
