package com.wheelEstate.ui;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

import com.wheelEstate.dao.FeedbackDAO;
import com.wheelEstate.entity.Car;
import com.wheelEstate.entity.Customer;
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

public class AdminUI {

	static void displayAdminMenu() {
		System.out.println("╔═══════════════ Admin Menu ═══════════════╗");
		System.out.println("║ 1. Add Car                               ║");
		System.out.println("║ 2. Update Car                            ║");
		System.out.println("║ 3. Delete Car                            ║");
		System.out.println("║ 4. Get All Cars                          ║");
		System.out.println("║ 5. Add Customer                         ║");
		System.out.println("║ 6. Update Customer                      ║");
		System.out.println("║ 7. Delete Customer                      ║");
		System.out.println("║ 8. Get All Customers                     ║");
		System.out.println("║ 9. Get Customer By ID                   ║");
		System.out.println("║ 10. Get Customer By Username            ║");
		System.out.println("║ 11. Get All Feedbacks                   ║");
		System.out.println("║ 12. Get Feedback By ID                  ║");
		System.out.println("║ 13. Get Feedbacks By Customer           ║");
		System.out.println("║ 14. Get Feedbacks By Car                ║");
		System.out.println("║ 15. Get All Payments                    ║");
		System.out.println("║ 16. Get All Reservations                ║");
		System.out.println("║ 17. Get Reservations Between Dates      ║");
		System.out.println("║ 0. Logout                                ║");
		System.out.println("╚═════════════════════════════════════════╝");
	}

	static void adminMenu(Scanner sc)
			throws SomethingWentWrongException, CarNotAvailableException, NoRecordFoundException {
		int choice = 0;
		do {
			displayAdminMenu();
			System.out.print("Enter selection: ");
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				addCar(sc);
				break;
			case 2:
				updateCar(sc);
				break;
			case 3:
				deleteCar(sc);
				break;
			case 4:
				getAllCars();
				break;
			case 5:
				addCustomer(sc);
				break;
			case 6:
				updateCustomer(sc);
				break;
			case 7:
				deleteCustomer(sc);
				break;
			case 8:
				getAllCustomers();
				break;
			case 9:
				getCustomerById(sc);
				break;
			case 10:
				getCustomerByUsername(sc);
				break;
			case 11:
				getAllFeedbacks();
				break;
			case 12:
				getFeedbackById(sc);
				break;
			case 13:
				getFeedbacksByCustomer(sc);
				break;
			case 14:
				getFeedbacksByCar(sc);
				break;
			case 15:
				getAllPayments();
				break;
			case 16:
				getAllReservations();
				break;
			case 17:
				getReservationsBetweenDates(sc);
				break;
			case 0:
				System.out.println("Logout Successfully");
				break;
			default:
				System.out.println("Invalid Selection, try again");
			}
		} while (choice != 0);
	}

	private static void addCar(Scanner sc) throws SomethingWentWrongException {
		System.out.print("Enter Car Brand: ");
		String brand = sc.nextLine();

		System.out.print("Enter Car Model: ");
		String model = sc.nextLine();

		System.out.print("Enter Car Price: ");
		double price = sc.nextDouble();

		System.out.print("Enter Car Mileage: ");
		BigDecimal mileage = sc.nextBigDecimal();

		System.out.print("Is the Car Available (true/false): ");
		boolean availability = sc.nextBoolean();

		System.out.print("Is the Car Deleted (true/false): ");
		boolean isDeleted = sc.nextBoolean();

		Car car = new Car(brand, model, price, mileage, availability, isDeleted);
		CarService carService = new CarServiceImpl();
		try {
			carService.addCar(car);
		} catch (SomethingWentWrongException e) {
			throw new SomethingWentWrongException("Something went wrong while adding the car inside the System.");
		}
	}

	private static void updateCar(Scanner sc) throws SomethingWentWrongException, CarNotAvailableException {
		System.out.print("Enter Car ID to update: ");
		long carId = sc.nextLong();

		System.out.print("Enter Car Brand: ");
		String brand = sc.nextLine();

		System.out.print("Enter Car Model: ");
		String model = sc.nextLine();

		System.out.print("Enter Car Price: ");
		double price = sc.nextDouble();

		System.out.print("Enter Car Mileage: ");
		BigDecimal mileage = sc.nextBigDecimal();

		System.out.print("Is the Car Available (true/false): ");
		boolean availability = sc.nextBoolean();

		System.out.print("Is the Car Deleted (true/false): ");
		boolean isDeleted = sc.nextBoolean();

		Car car = new Car(brand, model, price, mileage, availability, isDeleted);
		car.setCarId(carId);
		CarService carService = new CarServiceImpl();
		try {
			carService.updateCar(car);
		} catch (SomethingWentWrongException e) {
			throw new CarNotAvailableException("Car was not fount in the System.");
		}
	}

	private static void deleteCar(Scanner sc) throws NoRecordFoundException, CarNotAvailableException {
		System.out.print("Enter Car ID to delete: ");
		long carId = sc.nextLong();
		CarService carService = new CarServiceImpl();
		try {
			carService.deleteCar(carId);
		} catch (SomethingWentWrongException e) {
			throw new CarNotAvailableException("Car was not fount in the System.");
		}
	}

	private static void getAllCars() throws SomethingWentWrongException {
		CarService carService = new CarServiceImpl();
		try {
			carService.getAllCars();
		} catch (Exception e) {
			throw new SomethingWentWrongException("Some thing went wrong, Try again later.");
		}
	}

	private static void addCustomer(Scanner sc) {

		System.out.print("Enter First Name: ");
		String firstName = sc.nextLine();

		System.out.print("Enter Last Name: ");
		String lastName = sc.nextLine();

		System.out.print("Enter Email: ");
		String email = sc.nextLine();

		System.out.print("Enter Phone Number: ");
		String phoneNumber = sc.nextLine();

		System.out.print("Enter Username: ");
		String username = sc.nextLine();

		System.out.print("Enter Password: ");
		String password = sc.nextLine();

		Customer customer = new Customer(firstName, lastName, email, phoneNumber, username, password);

		CustomerService cs = new CustomerServiceImpl();
		try {
			cs.addCustomer(customer);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void updateCustomer(Scanner sc) {
		System.out.print("Enter Customer ID to update: ");
		long customerId = sc.nextLong();

		System.out.print("Enter First Name: ");
		String firstName = sc.nextLine();

		System.out.print("Enter Last Name: ");
		String lastName = sc.nextLine();

		System.out.print("Enter Email: ");
		String email = sc.nextLine();

		System.out.print("Enter Phone Number: ");
		String phoneNumber = sc.nextLine();

		System.out.print("Enter Username: ");
		String username = sc.nextLine();

		System.out.print("Enter Password: ");
		String password = sc.nextLine();

		Customer customer = new Customer(firstName, lastName, email, phoneNumber, username, password);

		CustomerService cs = new CustomerServiceImpl();
		try {
			cs.updateCustomer(customer);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	private static void deleteCustomer(Scanner sc) {
		System.out.print("Enter Customer ID to delete: ");
		long customerId = sc.nextLong();

		CustomerService cs = new CustomerServiceImpl();
		try {
			cs.deleteCustomer(customerId);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void getAllCustomers() {
		CustomerService cs = new CustomerServiceImpl();
		try {
			cs.getAllCustomers();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void getCustomerById(Scanner sc) {
		System.out.print("Enter Customer ID to get details: ");
		long customerId = sc.nextLong();

		CustomerService cs = new CustomerServiceImpl();
		try {
			cs.getCustomerById(customerId);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void getCustomerByUsername(Scanner sc) {
		System.out.print("Enter Customer Username to get details: ");
		String username = sc.next();

		CustomerService cs = new CustomerServiceImpl();
		try {
			cs.getCustomerByUsername(username);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void getAllFeedbacks() {
		FeedbackService fs = new FeedbackServiceImpl();
		try {
			fs.getAllFeedbacks();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void getFeedbackById(Scanner sc) {
		System.out.print("Enter Feedback ID to get details: ");
		long feedbackId = sc.nextLong();

		FeedbackService fs = new FeedbackServiceImpl();
		try {
			fs.getFeedbackById(feedbackId);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void getFeedbacksByCustomer(Scanner sc) {
		System.out.print("Enter Customer ID to get feedbacks: ");
		long customerId = sc.nextLong();

		FeedbackService fs = new FeedbackServiceImpl();
		try {
			fs.getFeedbacksByCustomer(customerId);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void getFeedbacksByCar(Scanner sc) {
		System.out.print("Enter Car ID to get feedbacks: ");
		long carId = sc.nextLong();

		FeedbackService fs = new FeedbackServiceImpl();
		try {
			fs.getFeedbacksByCar(carId);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void getAllPayments() {
		PaymentService ps = new PaymentServiceImpl();
		try {
			ps.getAllPayments();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void getAllReservations() {
		ReservationService rs = new ReservationServiceImpl();
		try {
			rs.getAllReservations();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void getReservationsBetweenDates(Scanner sc) {
		System.out.print("Enter start date (yyyy-MM-dd): ");
		String startDate = sc.next();

		System.out.print("Enter end date (yyyy-MM-dd): ");
		String endDate = sc.next();

		ReservationService rs = new ReservationServiceImpl();
		try {
			rs.getReservationsBetweenDates(LocalDate.parse(startDate), LocalDate.parse(endDate));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
}
