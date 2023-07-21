package com.wheelEstate.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Feedback {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long feedbackId;

	@ManyToOne
	@JoinColumn(name = "car_id", nullable = false)
	private Car car;

	@ManyToOne
	@JoinColumn(name = "customer_id", nullable = false)
	private Customer customer;

	@Column(nullable = false)
	private int rating;

	@Column
	private String comments;

	// Default constructor
	public Feedback() {
	}

	// Parameterized constructor
	public Feedback(Car car, Customer customer, int rating, String comments) {
		this.car = car;
		this.customer = customer;
		this.rating = rating;
		this.comments = comments;
	}

	public Long getFeedbackId() {
		return feedbackId;
	}

	public void setFeedbackId(Long feedbackId) {
		this.feedbackId = feedbackId;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getFeedbackDetails() {
		StringBuilder sb = new StringBuilder();
		sb.append("Feedback ID: ").append(this.feedbackId).append("\n");
		sb.append("Car Id: ").append(this.car.getCarId()).append(" ").append("\n").append("Car: ")
				.append(this.car.getBrand()).append(" ").append(this.car.getModel()).append("\n");
		sb.append("Customer: ").append(this.customer.getFirstName()).append(" ").append(this.customer.getLastName())
				.append("\n");
		sb.append("Rating: ").append(this.rating).append("\n");
		sb.append("Comments: ").append(this.comments != null ? this.comments : "N/A").append("\n");
		sb.append("-------------------------------------------");
		return sb.toString();
	}
}
