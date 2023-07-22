package com.wheelEstate.dao;

import java.util.List;

import com.wheelEstate.Utility.Util;
import com.wheelEstate.entity.Payment;
import com.wheelEstate.entity.Reservation;
import com.wheelEstate.exceptions.NoRecordFoundException;
import com.wheelEstate.exceptions.SomethingWentWrongException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

/**
 * This class implements the PaymentDAO interface and provides methods to
 * interact with the database to perform CRUD operations for Payment entities.
 */
public class PaymentDAOImpl implements PaymentDAO {

	/**
	 * Makes a new Payment entry in the database for a Reservation.
	 *
	 * @param payment The Payment entity to be made.
	 * @return The Payment entity that was made.
	 * @throws SomethingWentWrongException If something unexpected happens during
	 *                                     the database operation.
	 */
	@Override
	public Payment makePayment(Payment payment) throws SomethingWentWrongException {
		EntityManager em = null;
		EntityTransaction et = null;

		try {
			em = Util.getEm();
			et = em.getTransaction();

			Reservation rev = em.find(Reservation.class, payment.getReservation().getReservationId());
			if (rev.getPayment() != null) {
				throw new SomethingWentWrongException("Payment is already done for this reservation.");
			}
			et.begin();
			rev.setPayment(payment);
			em.persist(payment);
			et.commit();
			return payment;
		} catch (Exception e) {
			et.rollback();
			throw new SomethingWentWrongException("Something went wrong while adding Details. Try again later.");
		} finally {
			em.close();
		}
	}

	/**
	 * Cancels an existing Payment entry in the database based on the given
	 * paymentId.
	 *
	 * @param paymentId The ID of the Payment entry to be cancelled.
	 * @throws NoRecordFoundException      If no Payment entry is found with the
	 *                                     given paymentId in the database.
	 * @throws SomethingWentWrongException If something unexpected happens during
	 *                                     the database operation.
	 */
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
			throw new SomethingWentWrongException("Something went wrong while cancelling. Try again later.");
		} finally {
			em.close();
		}

	}

	/**
	 * Retrieves a list of all Payment entries from the database.
	 *
	 * @return A list of all Payment entries in the database.
	 * @throws SomethingWentWrongException If something unexpected happens during
	 *                                     the database operation.
	 */
	@Override
	public List<Payment> getAllPayments() throws SomethingWentWrongException {
		EntityManager em = null;
		EntityTransaction et = null;

		try {
			em = Util.getEm();
			et = em.getTransaction();

			et.begin();
			String jpql = "SELECT p FROM Payment p";
			Query query = em.createQuery(jpql);
			List<Payment> payments = query.getResultList();
			et.commit();
			return payments;

		} catch (Exception e) {
			et.rollback();
			throw new SomethingWentWrongException("Something went wrong getting adding Details. Try again later.");
		} finally {
			em.close();
		}

	}

	/**
	 * Retrieves a specific Payment entry from the database based on the given
	 * paymentId.
	 *
	 * @param paymentId The ID of the Payment entry to be retrieved.
	 * @return The Payment entity with the specified paymentId.
	 * @throws NoRecordFoundException If no Payment entry is found with the given
	 *                                paymentId in the database.
	 */
	@Override
	public Payment getPaymentById(Long paymentId) throws NoRecordFoundException {
		EntityManager em = null;
		EntityTransaction et = null;

		try {
			em = Util.getEm();
			et = em.getTransaction();

			et.begin();
			if (em.find(Payment.class, paymentId) != null) {
				et.commit();
				return em.find(Payment.class, paymentId);
			} else {
				throw new NoRecordFoundException("Invalid Payment Id.");
			}

		} catch (Exception e) {
			et.rollback();
			throw new NoRecordFoundException("Something went wrong while getting Details. Try again later.");
		} finally {
			em.close();
		}

	}

	/**
	 * Retrieves a list of Payment entries associated with a specific customer's
	 * Reservation.
	 *
	 * @param customerId The ID of the customer for whom the Payment entries are to
	 *                   be retrieved.
	 * @return A list of Payment entries associated with the specified customer's
	 *         Reservation.
	 * @throws SomethingWentWrongException If something unexpected happens during
	 *                                     the database operation.
	 */
	@Override
	public List<Payment> getPaymentsByReservation(Long customerId) throws SomethingWentWrongException {
		EntityManager em = null;
		EntityTransaction et = null;

		try {
			em = Util.getEm();
			et = em.getTransaction();

			et.begin();
			String jpql = "SELECT p FROM Payment p WHERE p.reservation.customer.customerId = :customerId";
			Query query = em.createQuery(jpql);
			query.setParameter("customerId", customerId);
			et.commit();
			return query.getResultList();

		} catch (Exception e) {
			et.rollback();
			throw new SomethingWentWrongException("Something went wrong while adding Details. Try again later.");
		} finally {
			em.close();
		}
	}

}
