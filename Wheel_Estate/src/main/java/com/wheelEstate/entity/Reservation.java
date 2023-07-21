package com.wheelEstate.entity;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Reservation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long reservationId;

	@ManyToOne
	@JoinColumn(name = "car_id", nullable = false)
	private Car car;

	@ManyToOne
	@JoinColumn(name = "customer_id", nullable = false)
	private Customer customer;

	@Column(nullable = false)
	private LocalDate startDate;

	@Column(nullable = false)
	private LocalDate endDate;

	@Column(nullable = false)
	private boolean isCancelled;

	@OneToOne(mappedBy = "reservation", cascade = CascadeType.ALL)
	private Payment payment;

	// Default constructor
	public Reservation() {
	}

	// Parameterized constructor
	public Reservation(Car car, Customer customer, LocalDate startDate, LocalDate endDate, boolean isCancelled) {
		this.car = car;
		this.customer = customer;
		this.startDate = startDate;
		this.endDate = endDate;
		this.isCancelled = isCancelled;
	}

	public Long getReservationId() {
		return reservationId;
	}

	public void setReservationId(Long reservationId) {
		this.reservationId = reservationId;
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

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public boolean isCancelled() {
		return isCancelled;
	}

	public void setCancelled(boolean isCancelled) {
		this.isCancelled = isCancelled;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public void printReservationDetails() {
		System.out.println("---------------------------------------------------------");
		System.out.println("Reservation ID: " + this.getReservationId());
		System.out.println("Car ID: " + this.getCar().getCarId());
		System.out.println("Customer ID: " + this.getCustomer().getCustomerId());
		System.out.println("Start Date: " + this.getStartDate());
		System.out.println("End Date: " + this.getEndDate());
		System.out.println("Is Cancelled: " + this.isCancelled());
		if (this.getPayment() != null) {
			System.out.println("Payment ID: " + this.getPayment().getPaymentId());
		} else {
			System.out.println("Payment: Pending");
		}
		System.out.println("---------------------------------------------------------");
	}

}
