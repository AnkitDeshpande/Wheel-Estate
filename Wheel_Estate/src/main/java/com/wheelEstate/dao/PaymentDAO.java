package com.wheelEstate.dao;

import java.util.List;

import com.wheelEstate.entity.Payment;
import com.wheelEstate.exceptions.NoRecordFoundException;
import com.wheelEstate.exceptions.SomethingWentWrongException;

public interface PaymentDAO {
	Payment makePayment(Payment payment) throws SomethingWentWrongException;

	void cancelPayment(Long paymentId) throws NoRecordFoundException, SomethingWentWrongException;

	List<Payment> getAllPayments();

	Payment getPaymentById(Long paymentId) throws NoRecordFoundException;

	List<Payment> getPaymentsByReservation(Long reservationId);
}
