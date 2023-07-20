package com.wheelEstate.service;

import java.util.List;

import com.wheelEstate.dao.FeedbackDAO;
import com.wheelEstate.dao.FeedbackDAOImpl;
import com.wheelEstate.entity.Feedback;
import com.wheelEstate.exceptions.NoRecordFoundException;
import com.wheelEstate.exceptions.SomethingWentWrongException;

public class FeedbackServiceImpl implements FeedbackService {

	@Override
	public Feedback addFeedback(Feedback feedback) throws SomethingWentWrongException {
		FeedbackDAO fd = new FeedbackDAOImpl();
		return fd.addFeedback(feedback);
	}

	@Override
	public Feedback updateFeedback(Feedback feedback) throws NoRecordFoundException, SomethingWentWrongException {
		FeedbackDAO fd = new FeedbackDAOImpl();
		return fd.updateFeedback(feedback);
	}

	@Override
	public void deleteFeedback(Long feedbackId) throws NoRecordFoundException, SomethingWentWrongException {
		FeedbackDAO fd = new FeedbackDAOImpl();
		fd.deleteFeedback(feedbackId);
	}

	@Override
	public List<Feedback> getAllFeedbacks() throws SomethingWentWrongException {
		FeedbackDAO fd = new FeedbackDAOImpl();
		return fd.getAllFeedbacks();
	}

	@Override
	public Feedback getFeedbackById(Long feedbackId) throws NoRecordFoundException {
		FeedbackDAO fd = new FeedbackDAOImpl();
		return fd.getFeedbackById(feedbackId);
	}

	@Override
	public List<Feedback> getFeedbacksByCustomer(Long customerId) throws SomethingWentWrongException {
		FeedbackDAO fd = new FeedbackDAOImpl();
		return fd.getFeedbacksByCustomer(customerId);
	}

	@Override
	public List<Feedback> getFeedbacksByCar(Long carId) throws NoRecordFoundException {
		FeedbackDAO fd = new FeedbackDAOImpl();
		return fd.getFeedbacksByCar(carId);
	}

}
