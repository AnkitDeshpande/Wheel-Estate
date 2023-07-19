package com.wheelEstate.dao;

import java.util.List;

import com.wheelEstate.entity.Payment;
import com.wheelEstate.exceptions.NoRecordFoundException;
import com.wheelEstate.exceptions.SomethingWentWrongException;

public class PaymentDAOImpl implements PaymentDAO {

	@Override
	public Payment makePayment(Payment payment) throws SomethingWentWrongException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void cancelPayment(Long paymentId) throws NoRecordFoundException, SomethingWentWrongException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Payment> getAllPayments() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Payment getPaymentById(Long paymentId) throws NoRecordFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Payment> getPaymentsByReservation(Long reservationId) {
		// TODO Auto-generated method stub
		return null;
	}

}
