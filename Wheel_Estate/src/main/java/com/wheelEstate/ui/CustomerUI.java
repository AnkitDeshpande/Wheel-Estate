package com.wheelEstate.ui;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

import com.wheelEstate.entity.Car;
import com.wheelEstate.entity.Customer;
import com.wheelEstate.entity.Feedback;
import com.wheelEstate.entity.Payment;
import com.wheelEstate.entity.Reservation;
import com.wheelEstate.exceptions.CarNotAvailableException;
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

public class CustomerUI {

	static void displayCustomerMenu() {
		System.out.println("╔═════════════ Customer Menu ══════════════╗");
		System.out.println("║ 1. Search and Filter Cars                ║");
		System.out.println("║ 2. View Rental Policies                  ║");
		System.out.println("║ 3. Check Car Availability                ║");
		System.out.println("║ 4. View Rental Charges                   ║");
		System.out.println("║ 5. Add Payment Method                    ║");
		System.out.println("║ 6. Filter Cars                           ║");
		System.out.println("║ 7. Provide Feedback and Ratings          ║");
		System.out.println("║ 8. Make Reservation                      ║");
		System.out.println("║ 9. Cancel Reservation                    ║");
		System.out.println("║ 0. Logout                                ║");
		System.out.println("╚═════════════════════════════════════════=╝");
	}

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
				viewRentalPolicies();
				break;
			case 3:
				checkCarAvailability(sc);
				break;
			case 4:
				viewRentalCharges(sc);
				break;
			case 5:
				addPaymentMethod(sc);
				break;
			case 6:
				searchAndFilterCars(sc);
				break;
			case 7:
				provideFeedbackAndRatings(sc);
				break;
			case 8:
				makeReservation(sc);
				break;
			case 9:
				cancelReservation(sc);
				break;
			case 10:
				// contactCustomerSupport(sc);
				break;
			case 0:
				System.out.println("Logout Successfully");
				break;
			default:
				System.out.println("Invalid Selection, try again");
			}
		} while (choice != 0);
	}

	private static void generateReceipt(Payment payment) {
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
			fs.addFeedback(feedback);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

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
			System.out.println("Enter Payment Date (YYYY-MM-DD): ");
			String paymentDateStr = sc.next();
			LocalDate paymentDate = LocalDate.parse(paymentDateStr);

			System.out.println("Enter Payment Amount: ");
			BigDecimal amount = sc.nextBigDecimal();

			sc.nextLine(); // Consume the newline character after reading the BigDecimal

			System.out.println("Enter Payment Method (e.g., Paytm, PhonePe, GPay, Credit Card, etc.): ");
			String paymentMethod = sc.nextLine();

			// Create a new Payment object with the provided data
			Payment payment = new Payment(reservation, paymentDate, amount, paymentMethod);
			PaymentService ps = new PaymentServiceImpl();
			ps.makePayment(payment);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	private static void viewRentalCharges(Scanner sc) {
		CarService cs = new CarServiceImpl();
		try {
			cs.getAllCars();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void checkCarAvailability(Scanner sc) {
		CarService cs = new CarServiceImpl();
		System.out.println("Enter Car ID to check Availabilty: ");
		Long carId = sc.nextLong();
		try {
			cs.getCarById(carId);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void viewRentalPolicies() {
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
	}

	private static void searchAndFilterCars(Scanner sc) throws SomethingWentWrongException {
		System.out.println("Search and Filter Cars:");
		System.out.println("1. Search by Brand");
		System.out.println("2. Search by Model");
		System.out.println("3. Search by Price Range");
		System.out.println("4. Show All Cars");

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

	private static void searchCarsByBrand(Scanner sc) {
		System.out.print("Enter Brand: ");
		String brand = sc.next();
		CarService cs = new CarServiceImpl();
		try {
			cs.searchCarsByBrand(brand);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void searchCarsByModel(Scanner sc) {
		System.out.print("Enter Model: ");
		String model = sc.next();
		CarService cs = new CarServiceImpl();
		try {
			cs.searchCarsByModel(model);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void searchCarsByPrice(Scanner sc) {
		System.out.print("Enter Start Price: ");
		double start = sc.nextDouble();
		System.out.print("Enter End Price: ");
		double end = sc.nextDouble();
		CarService cs = new CarServiceImpl();
		try {
			cs.searchCarsByPrice(start, end);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void makeReservation(Scanner sc) throws CarNotAvailableException, SomethingWentWrongException {

		try {
			System.out.print("Enter Car ID for Reservation: ");
			Long carId = sc.nextLong();
			System.out.println("Enter Customer ID for Feedback: ");
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
			rs.makeReservation(null);
		} catch (NoRecordFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void cancelReservation(Scanner sc) throws NoRecordFoundException {
		System.out.println("Enter Reservation ID for cancellation: ");
		Long reservationId = sc.nextLong();
		ReservationService rs = new ReservationServiceImpl();
		Reservation reservation = rs.getReservationById(reservationId);
		if (reservation == null) {
			System.out.println("Reservation not found with the given ID");
			return;
		} else {
			try {
				rs.cancelReservation(reservationId);
			} catch (NoRecordFoundException | SomethingWentWrongException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public static void login(Scanner sc) {
		System.out.println("Enter Username : ");
		String username = sc.next();
		System.out.println("Enter Password : ");
		String password = sc.next();

	}

	public static void customerRegister(Scanner sc) {
		AdminUI.addCustomer(sc);
	}
}
