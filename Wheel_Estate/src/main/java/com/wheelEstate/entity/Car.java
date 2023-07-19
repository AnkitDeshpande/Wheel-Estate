package com.wheelEstate.entity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Car {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long carId;

	@Column(nullable = false)
	private String brand;

	@Column(nullable = false)
	private String model;

	@Column(nullable = false)
	private double price;

	@Column(nullable = false)
	private BigDecimal mileage;

	@Column(nullable = false)
	private boolean availability;

	@Column(nullable = false)
	private boolean isDeleted;

	@OneToMany(mappedBy = "car")
	private Set<Reservation> reservations = new HashSet<>();

	@OneToMany(mappedBy = "car")
	private Set<Feedback> feedbacks = new HashSet<>();

	// Default constructor
	public Car() {
	}

	// Parameterized constructor
	public Car(String brand, String model, double price, BigDecimal mileage, boolean availability, boolean isDeleted) {
		this.brand = brand;
		this.model = model;
		this.price = price;
		this.mileage = mileage;
		this.availability = availability;
		this.isDeleted = isDeleted;
	}

	public Long getCarId() {
		return carId;
	}

	public void setCarId(Long carId) {
		this.carId = carId;
	}

	public String getBrand() {
		return brand;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public BigDecimal getMileage() {
		return mileage;
	}

	public void setMileage(BigDecimal mileage) {
		this.mileage = mileage;
	}

	public boolean isAvailability() {
		return availability;
	}

	public void setAvailability(boolean availability) {
		this.availability = availability;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Set<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(Set<Reservation> reservations) {
		this.reservations = reservations;
	}

	public Set<Feedback> getFeedbacks() {
		return feedbacks;
	}

	public void setFeedbacks(Set<Feedback> feedbacks) {
		this.feedbacks = feedbacks;
	}

	// Getters and setters

}
