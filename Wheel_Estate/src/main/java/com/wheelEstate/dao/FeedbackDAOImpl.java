package com.wheelEstate.dao;

import java.util.List;

import com.wheelEstate.entity.Feedback;
import com.wheelEstate.exceptions.NoRecordFoundException;
import com.wheelEstate.exceptions.SomethingWentWrongException;

public class FeedbackDAOImpl implements FeedbackDAO {

	@Override
	public Feedback addFeedback(Feedback feedback) throws SomethingWentWrongException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Feedback updateFeedback(Feedback feedback) throws NoRecordFoundException, SomethingWentWrongException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteFeedback(Long feedbackId) throws NoRecordFoundException, SomethingWentWrongException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Feedback> getAllFeedbacks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Feedback getFeedbackById(Long feedbackId) throws NoRecordFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Feedback> getFeedbacksByCustomer(Long customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Feedback> getFeedbacksByCar(Long carId) {
		// TODO Auto-generated method stub
		return null;
	}

}
