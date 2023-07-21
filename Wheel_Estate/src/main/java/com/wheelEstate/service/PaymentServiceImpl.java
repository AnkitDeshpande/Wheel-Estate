package com.wheelEstate.service;

import java.util.List;

import com.wheelEstate.dao.PaymentDAO;
import com.wheelEstate.dao.PaymentDAOImpl;
import com.wheelEstate.entity.Payment;
import com.wheelEstate.exceptions.NoRecordFoundException;
import com.wheelEstate.exceptions.SomethingWentWrongException;

public class PaymentServiceImpl implements PaymentService {

	@Override
	public Payment makePayment(Payment payment) throws SomethingWentWrongException {
		PaymentDAO pd = new PaymentDAOImpl();
		return pd.makePayment(payment);
	}

	@Override
	public void cancelPayment(Long paymentId) throws NoRecordFoundException, SomethingWentWrongException {
		PaymentDAO pd = new PaymentDAOImpl();
		pd.cancelPayment(paymentId);
	}

	@Override
	public List<Payment> getAllPayments() throws SomethingWentWrongException {
		PaymentDAO pd = new PaymentDAOImpl();
		return pd.getAllPayments();
	}

	@Override
	public Payment getPaymentById(Long paymentId) throws NoRecordFoundException {
		PaymentDAO pd = new PaymentDAOImpl();
		return pd.getPaymentById(paymentId);
	}

	@Override
	public List<Payment> getPaymentsByReservation(Long customerId) throws SomethingWentWrongException {
		PaymentDAO pd = new PaymentDAOImpl();
		return pd.getPaymentsByReservation(customerId);
	}

}
