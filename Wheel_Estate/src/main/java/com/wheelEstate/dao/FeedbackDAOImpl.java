package com.wheelEstate.dao;

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
