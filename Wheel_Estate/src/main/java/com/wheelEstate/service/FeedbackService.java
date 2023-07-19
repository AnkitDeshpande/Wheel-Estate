package com.wheelEstate.service;

import java.util.List;

import com.wheelEstate.entity.Feedback;
import com.wheelEstate.exceptions.NoRecordFoundException;
import com.wheelEstate.exceptions.SomethingWentWrongException;

public interface FeedbackService {
	Feedback addFeedback(Feedback feedback) throws SomethingWentWrongException;

	Feedback updateFeedback(Feedback feedback) throws NoRecordFoundException, SomethingWentWrongException;

	void deleteFeedback(Long feedbackId) throws NoRecordFoundException, SomethingWentWrongException;

	List<Feedback> getAllFeedbacks();

	Feedback getFeedbackById(Long feedbackId) throws NoRecordFoundException;

	List<Feedback> getFeedbacksByCustomer(Long customerId);

	List<Feedback> getFeedbacksByCar(Long carId);
}
