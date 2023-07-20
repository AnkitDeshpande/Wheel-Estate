package com.wheelEstate.service;

import java.time.LocalDate;
import java.util.List;

import com.wheelEstate.entity.Reservation;
import com.wheelEstate.exceptions.CarNotAvailableException;
import com.wheelEstate.exceptions.NoRecordFoundException;
import com.wheelEstate.exceptions.SomethingWentWrongException;

public interface ReservationService {
	Reservation makeReservation(Reservation reservation) throws CarNotAvailableException, SomethingWentWrongException;

	void cancelReservation(Long reservationId) throws NoRecordFoundException, SomethingWentWrongException;

	List<Reservation> getAllReservations() throws SomethingWentWrongException;

	Reservation getReservationById(Long reservationId) throws NoRecordFoundException;

	List<Reservation> getReservationsByCustomer(Long customerId) throws NoRecordFoundException;

	List<Reservation> getReservationsByCar(Long carId) throws NoRecordFoundException;

	List<Reservation> getReservationsBetweenDates(LocalDate startDate, LocalDate endDate) throws SomethingWentWrongException;
}
