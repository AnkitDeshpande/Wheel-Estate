package com.wheelEstate.dao;

/**
 * This class implements the FeedbackDAO interface and provides methods to interact with the database
 * to perform CRUD operations for Feedback entities.
 */
import java.util.List;

import com.wheelEstate.Utility.Util;
import com.wheelEstate.entity.Car;
import com.wheelEstate.entity.Customer;
import com.wheelEstate.entity.Feedback;
import com.wheelEstate.exceptions.NoRecordFoundException;
import com.wheelEstate.exceptions.SomethingWentWrongException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

public class FeedbackDAOImpl implements FeedbackDAO {

	/**
	 * Adds a new Feedback entry in the database.
	 *
	 * @param feedback The Feedback entity to be added.
	 * @return The Feedback entity that was added.
	 * @throws SomethingWentWrongException If something unexpected happens during
	 *                                     the database operation.
	 */
	@Override
	public Feedback addFeedback(Feedback feedback) throws SomethingWentWrongException {
		EntityManager em = null;
		EntityTransaction et = null;

		try {
			em = Util.getEm();
			et = em.getTransaction();

			et.begin();
			em.persist(feedback);
			et.commit();
			return feedback;
		} catch (Exception e) {
			et.rollback();
			throw new SomethingWentWrongException("Something went wrong while adding Details. Try again later.");
		} finally {
			em.close();
		}
	}

	/**
	 * Updates an existing Feedback entry in the database based on the given
	 * feedbackId.
	 *
	 * @param feedback The Feedback entity with updated details.
	 * @return The Feedback entity that was updated.
	 * @throws NoRecordFoundException      If no Feedback entry is found with the
	 *                                     given feedbackId in the database.
	 * @throws SomethingWentWrongException If something unexpected happens during
	 *                                     the database operation.
	 */
	@Override
	public Feedback updateFeedback(Feedback feedback) throws NoRecordFoundException, SomethingWentWrongException {
		EntityManager em = null;
		EntityTransaction et = null;

		try {
			em = Util.getEm();
			et = em.getTransaction();

			et.begin();
			Feedback feedback1 = em.find(Feedback.class, feedback.getFeedbackId());
			if (feedback1 != null) {
				em.merge(feedback);
				et.commit();
				return feedback;
			} else {
				throw new NoRecordFoundException("Couldn't edit the feedback, try again later");
			}
		} catch (Exception e) {
			et.rollback();
			throw new SomethingWentWrongException("Something went wrong while adding Details. Try again later.");
		} finally {
			em.close();
		}
	}

	/**
	 * Deletes an existing Feedback entry from the database based on the given
	 * feedbackId.
	 *
	 * @param feedbackId The ID of the Feedback entry to be deleted.
	 * @throws NoRecordFoundException      If no Feedback entry is found with the
	 *                                     given feedbackId in the database.
	 * @throws SomethingWentWrongException If something unexpected happens during
	 *                                     the database operation.
	 */
	@Override
	public void deleteFeedback(Long feedbackId) throws NoRecordFoundException, SomethingWentWrongException {
		EntityManager em = null;
		EntityTransaction et = null;

		try {
			em = Util.getEm();
			et = em.getTransaction();

			et.begin();
			Feedback feedback = em.find(Feedback.class, feedbackId);
			if (feedback != null) {
				em.remove(feedback);
				et.commit();
			} else {
				throw new NoRecordFoundException("Couldn't remove the feedback, try again later");
			}
		} catch (Exception e) {
			et.rollback();
			throw new SomethingWentWrongException("Something went wrong while adding Details. Try again later.");
		} finally {
			em.close();
		}

	}

	/**
	 * Retrieves a list of all Feedback entries from the database.
	 *
	 * @return A list of all Feedback entries in the database.
	 * @throws SomethingWentWrongException If something unexpected happens during
	 *                                     the database operation.
	 */
	@Override
	public List<Feedback> getAllFeedbacks() throws SomethingWentWrongException {
		EntityManager em = null;
		EntityTransaction et = null;

		try {
			em = Util.getEm();
			et = em.getTransaction();

			Query query = em.createQuery("SELECT f FROM Feedback f");
			et.begin();
			List<Feedback> list = (List<Feedback>) query.getResultList();
			if (list != null) {
				et.commit();
				return list;
			} else {
				throw new SomethingWentWrongException("List is empty.");
			}

		} catch (Exception e) {
			et.rollback();
			throw new SomethingWentWrongException("Something went wrong while getting Details. Try again later.");
		} finally {
			em.close();
		}
	}

	/**
	 * Retrieves a specific Feedback entry from the database based on the given
	 * feedbackId.
	 *
	 * @param feedbackId The ID of the Feedback entry to be retrieved.
	 * @return The Feedback entity with the specified feedbackId.
	 * @throws NoRecordFoundException If no Feedback entry is found with the given
	 *                                feedbackId in the database.
	 */
	@Override
	public Feedback getFeedbackById(Long feedbackId) throws NoRecordFoundException {
		EntityManager em = null;
		EntityTransaction et = null;

		try {
			em = Util.getEm();
			et = em.getTransaction();

			et.begin();
			Feedback feedback = em.find(Feedback.class, feedbackId);
			if (feedback != null) {
				et.commit();
				return feedback;
			} else {
				throw new NoRecordFoundException("Couldn't find the feedback, try again later");
			}

		} catch (Exception e) {
			et.rollback();
			throw new NoRecordFoundException("Something went wrong while getting Details. Try again later.");
		} finally {
			em.close();
		}
	}

	/**
	 * Retrieves a list of Feedback entries associated with a specific customer.
	 *
	 * @param customerId The ID of the customer for whom the Feedback entries are to
	 *                   be retrieved.
	 * @return A list of Feedback entries associated with the specified customer.
	 * @throws SomethingWentWrongException If something unexpected happens during
	 *                                     the database operation.
	 */
	@Override
	public List<Feedback> getFeedbacksByCustomer(Long customerId) throws SomethingWentWrongException {
		EntityManager em = null;
		EntityTransaction et = null;

		try {
			em = Util.getEm();
			et = em.getTransaction();

			et.begin();
			Customer customer = em.find(Customer.class, customerId);
			if (customer == null) {
				throw new NoRecordFoundException("No customer found with the given ID: " + customerId);
			}

			Query query = em.createQuery("SELECT f FROM Feedback f WHERE f.customer = :customer", Feedback.class);
			query.setParameter("customer", customer);
			List<Feedback> feedbacks = query.getResultList();
			et.commit();
			return feedbacks;

		} catch (Exception e) {
			et.rollback();
			throw new SomethingWentWrongException("Something went wrong while getting Details. Try again later.");
		} finally {
			em.close();
		}
	}

	/**
	 * Retrieves a list of Feedback entries associated with a specific car.
	 *
	 * @param carId The ID of the car for which the Feedback entries are to be
	 *              retrieved.
	 * @return A list of Feedback entries associated with the specified car.
	 * @throws NoRecordFoundException If no Car entry is found with the given carId
	 *                                in the database.
	 */
	@Override
	public List<Feedback> getFeedbacksByCar(Long carId) throws NoRecordFoundException {
		EntityManager em = null;
		EntityTransaction et = null;

		try {
			em = Util.getEm();
			et = em.getTransaction();

			et.begin();
			Car car = em.find(Car.class, carId);
			if (car == null) {
				throw new NoRecordFoundException("No Car found with the given ID: " + carId);
			}

			Query query = em.createQuery("SELECT f FROM Feedback f WHERE f.car = :car", Feedback.class);
			query.setParameter("car", car);
			List<Feedback> feedbacks = query.getResultList();
			et.commit();
			return feedbacks;

		} catch (Exception e) {
			et.rollback();
			throw new NoRecordFoundException("Something went wrong while getting Details. Try again later.");
		} finally {
			em.close();
		}
	}

}
