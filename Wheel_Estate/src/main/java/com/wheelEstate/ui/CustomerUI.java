package com.wheelEstate.ui;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Scanner;

import com.wheelEstate.entity.Car;
import com.wheelEstate.entity.Customer;
import com.wheelEstate.entity.Feedback;
import com.wheelEstate.entity.Payment;
import com.wheelEstate.entity.Reservation;
import com.wheelEstate.exceptions.CarNotAvailableException;
import com.wheelEstate.exceptions.LoginException;
import com.wheelEstate.exceptions.NoRecordFoundException;
import com.wheelEstate.exceptions.SomethingWentWrongException;
import com.wheelEstate.service.CarService;
import com.wheelEstate.service.CarServiceImpl;
import com.wheelEstate.service.CustomerService;
import com.wheelEstate.service.CustomerServiceImpl;
import com.wheelEstate.service.FeedbackService;
import com.wheelEstate.service.FeedbackServiceImpl;
import com.wheelEstate.service.PaymentService;
import com.wheelEstate.service.PaymentServiceImpl;
import com.wheelEstate.service.ReservationService;
import com.wheelEstate.service.ReservationServiceImpl;


/**
 * The CustomerUI class provides a user interface for interacting with the car rental system as a customer.
 * It allows customers to search for cars, make reservations, view reservations, make payments, provide feedback, and more.
 */
public class CustomerUI {

	
	/**
     * Displays the customer menu with various options for the customer to choose from.
     */
	static void displayCustomerMenu() {
		System.out.println("╔═════════════ Customer Menu ══════════════╗");
		System.out.println("║ 1. Search and Filter Cars                ║");
		System.out.println("║ 2. Make Reservation                      ║");
		System.out.println("║ 3. Cancel Reservation                    ║");
		System.out.println("║ 4. view Reservations                     ║");
		System.out.println("║ 5. Check Car Availability                ║");
		System.out.println("║ 6. View Rental Charges                   ║");
		System.out.println("║ 7. Make Payments                         ║");
		System.out.println("║ 8. view Payment History                  ║");
		System.out.println("║ 9. Provide Feedback and Ratings          ║");
		System.out.println("║ 10. Edit Your Feedbacks and Rating       ║");
		System.out.println("║ 11. View all Your Feedbacks and Rating   ║");
		System.out.println("║ 12. View Rental Policies                 ║");
		System.out.println("║ 0. Logout                                ║");
		System.out.println("╚═════════════════════════════════════════=╝");
	}

	
	/**
     * Handles the customer menu interactions based on the user's selected choice.
     *
     * @param sc Scanner object to read user input.
     * @throws SomethingWentWrongException When an unexpected error occurs during the operation.
     * @throws CarNotAvailableException When a requested car is not available for reservation.
     * @throws NoRecordFoundException When no record is found for the given input.
     */
	static void customerMenu(Scanner sc)
			throws SomethingWentWrongException, CarNotAvailableException, NoRecordFoundException {
		int choice = 0;
		do {
			displayCustomerMenu();
			System.out.print("Enter selection: ");
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				searchAndFilterCars(sc);
				break;
			case 2:
				makeReservation(sc);
				break;
			case 3:
				cancelReservation(sc);
				break;
			case 4:
				viewReservation(sc);
				break;
			case 5:
				checkCarAvailability(sc);
				break;
			case 6:
				viewRentalCharges(sc);
				break;
			case 7:
				addPaymentMethod(sc);
				break;
			case 8:
				viewPaymentHistory(sc);
				break;
			case 9:
				provideFeedbackAndRatings(sc);
				break;
			case 10:
				editFeedback(sc);
				break;
			case 11:
				viewAllFeedback(sc);
				break;
			case 12:
				viewRentalPolicies();
				break;
			case 0:
				System.out.println("Logout Successfully");
				break;
			default:
				System.out.println("Invalid Selection, try again");
			}
		} while (choice != 0);
	}

	
	/**
     * Displays all feedbacks and ratings given by the customer.
     *
     * @param sc Scanner object to read user input.
     */
	private static void viewAllFeedback(Scanner sc) {
		System.out.println("Enter your Customer Id :");
		Long customerId = sc.nextLong();
		try {
			FeedbackService fs = new FeedbackServiceImpl();
			List<Feedback> feeds = fs.getFeedbacksByCustomer(customerId);
			feeds.forEach(f -> System.out.println(f.getFeedbackDetails()));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	
	/**
     * Generates a receipt for a payment made by the customer.
     *
     * @param payment The payment object for which the receipt needs to be generated.
     */
	public static void generateReceipt(Payment payment) {
		System.out.println("----------------------------");
		System.out.println("Reservation Receipt:");
		Reservation reservation = payment.getReservation();
		System.out.println("Reservation ID: " + reservation.getReservationId());
		System.out.println("Car Details:");
		System.out.println("Brand: " + reservation.getCar().getBrand());
		System.out.println("Model: " + reservation.getCar().getModel());
		System.out.println("Start Date: " + reservation.getStartDate());
		System.out.println("End Date: " + reservation.getEndDate());
		System.out.println("Payment ID: " + payment.getPaymentId());
		System.out.println("Payment Date: " + payment.getPaymentDate());
		System.out.println("Amount Paid: " + payment.getAmount());
		System.out.println("Payment Method: " + payment.getPaymentMethod());
		System.out.println("----------------------------");
	}

	
	/**
     * Allows the customer to provide feedback and ratings for a specific car and customer.
     *
     * @param sc Scanner object to read user input.
     */
	private static void provideFeedbackAndRatings(Scanner sc) {
		System.out.println("Enter Car ID for Feedback: ");
		Long carId = sc.nextLong();
		System.out.println("Enter Customer ID for Feedback: ");
		Long customerId = sc.nextLong();
		System.out.println("Enter Rating (1-5): ");
		int rating = sc.nextInt();
		sc.nextLine();
		System.out.println("Enter Comments: ");
		String comments = sc.nextLine();

		try {
			CarService cs = new CarServiceImpl();
			CustomerService ct = new CustomerServiceImpl();

			Car car = cs.getCarById(carId);
			Customer cust = ct.getCustomerById(customerId);
			if (car == null || cust == null) {
				throw new CarNotAvailableException("Car Not Found.");
			}
			Feedback feedback = new Feedback(car, cust, rating, comments);
			FeedbackService fs = new FeedbackServiceImpl();
			if (fs.addFeedback(feedback) != null) {
				System.out.println("feedback added successfully.");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	
	/**
     * Allows the customer to edit their previously provided feedback and ratings.
     *
     * @param sc Scanner object to read user input.
     */
	private static void editFeedback(Scanner sc) {
		System.out.println("Enter feedback ID: ");
		Long fId = sc.nextLong();
		System.out.println("Enter Car ID for Feedback: ");
		Long carId = sc.nextLong();
		System.out.println("Enter Customer ID for Feedback: ");
		Long customerId = sc.nextLong();
		System.out.println("Enter Rating (1-5): ");
		int rating = sc.nextInt();
		sc.nextLine();
		System.out.println("Enter Comments: ");
		String comments = sc.nextLine();

		try {
			CarService cs = new CarServiceImpl();
			CustomerService ct = new CustomerServiceImpl();

			Car car = cs.getCarById(carId);
			Customer cust = ct.getCustomerById(customerId);
			if (car == null || cust == null) {
				throw new CarNotAvailableException("Car Not Found.");
			}
			Feedback feedback = new Feedback(car, cust, rating, comments);
			feedback.setFeedbackId(fId);
			FeedbackService fs = new FeedbackServiceImpl();
			if (fs.updateFeedback(feedback) != null) {
				System.out.println("feedback Edited successfully.");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	
	 /**
     * Allows the customer to add a payment method and make payments for reservations.
     *
     * @param sc Scanner object to read user input.
     */
	private static void addPaymentMethod(Scanner sc) {

		try {
			System.out.println("Enter Reservation ID for Payment: ");
			Long reservationId = sc.nextLong();
			ReservationService rs = new ReservationServiceImpl();
			Reservation reservation = rs.getReservationById(reservationId);
			if (reservation == null) {
				System.out.println("Reservation not found with the given ID");
				return;
			}
			long durationInDays = ChronoUnit.DAYS.between(reservation.getStartDate(), reservation.getEndDate());
			double totalAmount = reservation.getCar().getPrice() * durationInDays;

			System.out.println("You have to pay : " + totalAmount + " Rupees.");
			System.out.println("Enter Payment Amount to be paid below: ");
			double amount = sc.nextDouble();

			if (amount < totalAmount) {
				System.out.println("Please Enter the right amount.");
				return;
			}

			sc.nextLine(); // Consume the newline character after reading the BigDecimal

			System.out.println("Enter Payment Method (e.g., Paytm, PhonePe, GPay, Credit Card, etc.): ");
			String paymentMethod = sc.nextLine();

			// Create a new Payment object with the provided data
			Payment payment = new Payment(reservation, LocalDate.now(), BigDecimal.valueOf(totalAmount), paymentMethod);
			PaymentService ps = new PaymentServiceImpl();
			Payment payment1 = ps.makePayment(payment);
			generateReceipt(payment1);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	
	/**
     * Displays the rental charges for all available cars.
     *
     * @param sc Scanner object to read user input.
     */
	private static void viewRentalCharges(Scanner sc) {
		CarService cs = new CarServiceImpl();
		try {
			cs.getAllCars();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	
	 /**
     * Checks the availability of a specific car.
     *
     * @param sc Scanner object to read user input.
     */
	private static void checkCarAvailability(Scanner sc) {
		CarService cs = new CarServiceImpl();
		System.out.println("Enter Car ID to check Availabilty: ");
		Long carId = sc.nextLong();
		try {
			Car car = cs.getCarById(carId);
			if (car != null && car.isAvailability()) {
				AdminUI.printCarDetails(car);
			} else {
				System.out.println("Car is not available.");
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	
	/**
     * Displays the rental policies for the car rental system.
     */
	private static void viewRentalPolicies() {
		System.out
				.println("-------------------------------------------------------------------------------------------");
		System.out.println("Rental Policies:");
		System.out.println("1. Minimum rental duration: 3 days");
		System.out.println("2. Maximum rental duration: 30 days");
		System.out.println("3. Base price per day: 500rs");
		System.out.println("4. Additional mileage charge: $0.10 per mile");
		System.out.println("5. Security deposit: 200rs");
		System.out.println("6. Late return penalty: 25rs per day");
		System.out.println("7. Age requirement: Minimum age of the renter should be 21");
		System.out.println("8. Additional driver fee: 100rs per day per additional driver");
		System.out.println("9. Fuel policy: Full-to-full fuel policy");
		System.out.println("10. Smoking policy: Strictly no smoking inside the vehicle");
		System.out.println("11. Cleaning fee: 250rs for excessively dirty vehicles");
		System.out.println("12. Toll charges: Customer responsible for toll charges");
		System.out.println("13. Vehicle damage policy: Customer responsible for damages");
		System.out.println("14. Insurance options: Various insurance coverage options available");
		System.out.println("15. Early return policy: No refunds for early returns");
		System.out.println("16. Reservation cancellation policy: Full refund if canceled 48 hours before pickup");
		System.out
				.println("-------------------------------------------------------------------------------------------");
	}

	
	 /**
     * Allows the customer to search and filter cars based on various criteria.
     *
     * @param sc Scanner object to read user input.
     * @throws SomethingWentWrongException When an unexpected error occurs during the operation.
     */
	private static void searchAndFilterCars(Scanner sc) throws SomethingWentWrongException {
		System.out.println("╔═════════════════════════════╗");
		System.out.println("║  Search and Filter Cars:    ║");
		System.out.println("╠═════════════════════════════╣");
		System.out.println("║ 1. Search by Brand          ║");
		System.out.println("║ 2. Search by Model          ║");
		System.out.println("║ 3. Search by Price Range    ║");
		System.out.println("║ 4. Show All Cars            ║");
		System.out.println("╚═════════════════════════════╝");

		System.out.print("Enter selection: ");
		int option = sc.nextInt();

		switch (option) {
		case 1:
			searchCarsByBrand(sc);
			break;
		case 2:
			searchCarsByModel(sc);
			break;
		case 3:
			searchCarsByPrice(sc);
			break;
		case 4:
			AdminUI.getAllCars();
			break;
		default:
			System.out.println("Invalid Selection, try again");
		}
	}

	
	/**
     * Searches for cars based on the provided brand.
     *
     * @param sc Scanner object to read user input.
     */
	private static void searchCarsByBrand(Scanner sc) {
		System.out.print("Enter Brand: ");
		String brand = sc.next();
		CarService cs = new CarServiceImpl();
		try {
			List<Car> cars = cs.searchCarsByBrand(brand);
			cars.forEach(c -> AdminUI.printCarDetails(c));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	
	/**
     * Searches for cars based on the provided model.
     *
     * @param sc Scanner object to read user input.
     */
	private static void searchCarsByModel(Scanner sc) {
		System.out.print("Enter Model: ");
		String model = sc.next();
		CarService cs = new CarServiceImpl();
		try {
			List<Car> cars = cs.searchCarsByModel(model);
			cars.forEach(c -> AdminUI.printCarDetails(c));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	
	/**
     * Searches for cars based on the provided price range.
     *
     * @param sc Scanner object to read user input.
     */
	private static void searchCarsByPrice(Scanner sc) {
		System.out.print("Enter Start Price: ");
		double start = sc.nextDouble();
		System.out.print("Enter End Price: ");
		double end = sc.nextDouble();
		CarService cs = new CarServiceImpl();
		try {
			List<Car> cars = cs.searchCarsByPrice(start, end);
			cars.forEach(c -> AdminUI.printCarDetails(c));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	
	/**
     * Allows the customer to make a reservation for a car.
     *
     * @param sc Scanner object to read user input.
     * @throws CarNotAvailableException When the requested car is not available for reservation.
     * @throws SomethingWentWrongException When an unexpected error occurs during the operation.
     */
	private static void makeReservation(Scanner sc) throws CarNotAvailableException, SomethingWentWrongException {

		try {
			System.out.print("Enter Car ID for Reservation: ");
			Long carId = sc.nextLong();
			System.out.println("Enter Customer ID for reservation: ");
			Long customerId = sc.nextLong();

			CarService cs = new CarServiceImpl();
			CustomerService ct = new CustomerServiceImpl();
			Car car = cs.getCarById(carId);
			Customer cust = ct.getCustomerById(customerId);

			if (car == null) {
				throw new CarNotAvailableException("Car Not Found.");
			} else if (cust == null) {
				throw new NoRecordFoundException("Customer ID is Invalid.");
			} else if (!car.isAvailability()) {
				throw new CarNotAvailableException("Car is reserved. Please choose another Car.");
			}

			System.out.print("Enter Start Date (YYYY-MM-DD): ");
			String startDateStr = sc.next();
			LocalDate startDate = LocalDate.parse(startDateStr);

			System.out.print("Enter End Date (YYYY-MM-DD): ");
			String endDateStr = sc.next();
			LocalDate endDate = LocalDate.parse(endDateStr);
			Reservation reserve = new Reservation(car, cust, startDate, endDate, false);
			ReservationService rs = new ReservationServiceImpl();
			Reservation reservation = rs.makeReservation(reserve);
			reservation.printReservationDetails();
		} catch (NoRecordFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	
	/**
     * Allows the customer to cancel an existing reservation.
     *
     * @param sc Scanner object to read user input.
     * @throws NoRecordFoundException When no reservation is found with the given ID.
     */
	private static void cancelReservation(Scanner sc) throws NoRecordFoundException {
		System.out.println("Enter Reservation ID for cancellation: ");
		Long reservationId = sc.nextLong();
		ReservationService rs = new ReservationServiceImpl();
		try {
			rs.cancelReservation(reservationId);
			System.out.println("Reservation Cancelled.");
		} catch (NoRecordFoundException | SomethingWentWrongException e) {
			System.out.println(e.getMessage());
		}
	}

	
	/**
     * Displays all reservations made by the customer.
     *
     * @param sc Scanner object to read user input.
     */
	private static void viewReservation(Scanner sc) {
		System.out.print("Enter Customer ID: ");
		Long customerId = sc.nextLong();
		ReservationService rs = new ReservationServiceImpl();
		try {
			List<Reservation> revs = rs.getReservationsByCustomer(customerId);
			revs.forEach(r -> r.printReservationDetails());
		} catch (NoRecordFoundException e) {
			System.out.println(e.getMessage());
		}

	}

	
	 /**
     * Displays the payment history for the customer.
     *
     * @param sc Scanner object to read user input.
     */
	private static void viewPaymentHistory(Scanner sc) {
		System.out.print("Enter Customer ID: ");
		Long customerId = sc.nextLong();
		PaymentService ps = new PaymentServiceImpl();

		try {
			List<Payment> payments = ps.getPaymentsByReservation(customerId);
			payments.forEach(p -> generateReceipt(p));
		} catch (SomethingWentWrongException e) {
			System.out.println(e.getMessage());
		}
	}

	
	/**
     * Allows the customer to log in to the car rental system.
     *
     * @param sc Scanner object to read user input.
     */
	public static void login(Scanner sc) {
		System.out.println("Enter Username : ");
		String username = sc.next();
		System.out.println("Enter Password : ");
		String password = sc.next();
		CustomerService cs = new CustomerServiceImpl();
		try {
			cs.login(username, password);
			try {
				CustomerUI.customerMenu(sc);
			} catch (CarNotAvailableException | NoRecordFoundException e) {
				e.printStackTrace();
			}
		} catch (SomethingWentWrongException | LoginException e) {
			System.out.println(e.getMessage());
		}
	}

	
	/**
     * Allows the customer to register in the car rental system.
     *
     * @param sc Scanner object to read user input.
     */
	public static void customerRegister(Scanner sc) {
		AdminUI.addCustomer(sc);
	}
}
