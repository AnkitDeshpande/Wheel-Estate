package com.wheelEstate.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long paymentId;

	@OneToOne
	@JoinColumn(name = "reservation_id", nullable = false)
	private Reservation reservation;

	@Column(nullable = false)
	private LocalDate paymentDate;

	@Column(nullable = false)
	private BigDecimal amount;

	@Column(nullable = false)
	private String paymentMethod;

	// Default constructor
	public Payment() {
	}

	// Parameterized constructor
	public Payment(Reservation reservation, LocalDate paymentDate, BigDecimal amount, String paymentMethod) {
		this.reservation = reservation;
		this.paymentDate = paymentDate;
		this.amount = amount;
		this.paymentMethod = paymentMethod;
	}

	public Long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

}
