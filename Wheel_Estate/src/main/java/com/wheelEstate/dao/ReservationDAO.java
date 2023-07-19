package com.wheelEstate.dao;

import java.time.LocalDate;
import java.util.List;

import com.wheelEstate.entity.Reservation;
import com.wheelEstate.exceptions.CarNotAvailableException;
import com.wheelEstate.exceptions.NoRecordFoundException;
import com.wheelEstate.exceptions.SomethingWentWrongException;

public interface ReservationDAO {
    Reservation makeReservation(Reservation reservation) throws CarNotAvailableException, SomethingWentWrongException;

    void cancelReservation(Long reservationId) throws NoRecordFoundException, SomethingWentWrongException;

    List<Reservation> getAllReservations();

    Reservation getReservationById(Long reservationId) throws NoRecordFoundException;

    List<Reservation> getReservationsByCustomer(Long customerId);

    List<Reservation> getReservationsByCar(Long carId);

    List<Reservation> getReservationsBetweenDates(LocalDate startDate, LocalDate endDate);
}
