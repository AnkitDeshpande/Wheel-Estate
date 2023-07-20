package com.wheelEstate.dao;

import java.util.List;

import com.wheelEstate.Utility.Util;
import com.wheelEstate.entity.Payment;
import com.wheelEstate.exceptions.NoRecordFoundException;
import com.wheelEstate.exceptions.SomethingWentWrongException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class PaymentDAOImpl implements PaymentDAO {

	@Override
	public Payment makePayment(Payment payment) throws SomethingWentWrongException {
		EntityManager em = null;
		EntityTransaction et = null;

		try {
			em = Util.getEm();
			et = em.getTransaction();

			et.begin();

			et.commit();

		} catch (Exception e) {
			et.rollback();
			throw new SomethingWentWrongException("Something went wrong while adding Details. Try again later.");
		} finally {
			em.close();
		}
		return null;
	}

	@Override
	public void cancelPayment(Long paymentId) throws NoRecordFoundException, SomethingWentWrongException {
		EntityManager em = null;
		EntityTransaction et = null;

		try {
			em = Util.getEm();
			et = em.getTransaction();

			et.begin();

			et.commit();

		} catch (Exception e) {
			et.rollback();
			throw new SomethingWentWrongException("Something went wrong while adding Details. Try again later.");
		} finally {
			em.close();
		}

	}

	@Override
	public List<Payment> getAllPayments() throws SomethingWentWrongException {
		EntityManager em = null;
		EntityTransaction et = null;

		try {
			em = Util.getEm();
			et = em.getTransaction();

			et.begin();

			et.commit();

		} catch (Exception e) {
			et.rollback();
			throw new SomethingWentWrongException("Something went wrong while adding Details. Try again later.");
		} finally {
			em.close();
		}
		return null;
	}

	@Override
	public Payment getPaymentById(Long paymentId) throws NoRecordFoundException {
		EntityManager em = null;
		EntityTransaction et = null;

		try {
			em = Util.getEm();
			et = em.getTransaction();

			et.begin();

			et.commit();

		} catch (Exception e) {
			et.rollback();
			throw new NoRecordFoundException("Something went wrong while adding Details. Try again later.");
		} finally {
			em.close();
		}
		return null;
	}

	@Override
	public List<Payment> getPaymentsByReservation(Long reservationId) throws SomethingWentWrongException {
		EntityManager em = null;
		EntityTransaction et = null;

		try {
			em = Util.getEm();
			et = em.getTransaction();

			et.begin();

			et.commit();

		} catch (Exception e) {
			et.rollback();
			throw new SomethingWentWrongException("Something went wrong while adding Details. Try again later.");
		} finally {
			em.close();
		}
		return null;
	}

}
