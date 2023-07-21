package com.wheelEstate.ui;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.wheelEstate.entity.Car;
import com.wheelEstate.entity.Customer;
import com.wheelEstate.entity.Feedback;
import com.wheelEstate.entity.Payment;
import com.wheelEstate.entity.Reservation;
import com.wheelEstate.enums.Admin;
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
		System.out.println("╔═══════════════ Admin Menu ══════════════╗");
		System.out.println("║ 1. Add Car                              ║");
		System.out.println("║ 2. Update Car                           ║");
		System.out.println("║ 3. Delete Car                           ║");
		System.out.println("║ 4. Get All Cars                         ║");
		System.out.println("║ 5. Add Customer                         ║");
		System.out.println("║ 6. Update Customer                      ║");
		System.out.println("║ 7. Delete Customer                      ║");
		System.out.println("║ 8. Get All Customers                    ║");
		System.out.println("║ 9. Get Customer By ID                   ║");
		System.out.println("║ 10. Get Customer By Username            ║");
		System.out.println("║ 11. Get All Feedbacks                   ║");
		System.out.println("║ 12. Get Feedback By ID                  ║");
		System.out.println("║ 13. Get Feedbacks By Customer           ║");
		System.out.println("║ 14. Get Feedbacks By Car                ║");
		System.out.println("║ 15. Get All Payments                    ║");
		System.out.println("║ 16. Get All Reservations                ║");
		System.out.println("║ 17. Get Reservations Between Dates      ║");
		System.out.println("║ 0. Logout                               ║");
		System.out.println("╚═════════════════════════════════════════╝");
	}

	static void adminMenu(Scanner sc)
			throws SomethingWentWrongException, CarNotAvailableException, NoRecordFoundException {
		int choice = 0;
		do {
			displayAdminMenu();
			System.out.print("Enter selection: ");
			choice = sc.nextInt();
			sc.nextLine();
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

	public static void printCarDetails(Car car) {
		System.out.println("--------------------------------------------------");
		System.out.println("Car Details:");
		System.out.println("ID: " + car.getCarId());
		System.out.println("Brand: " + car.getBrand());
		System.out.println("Model: " + car.getModel());
		System.out.println("Price: " + car.getPrice());
		System.out.println("Mileage: " + car.getMileage());
		System.out.println("Availability: " + (car.isAvailability() ? "Available" : "Not Available"));
		System.out.println("Deleted: " + (car.isDeleted() ? "Yes" : "No"));
		System.out.println("--------------------------------------------------");
	}

	public static void printCustomerDetails(Customer customer) {
		if (customer != null) {
			System.out.println("----------------------------");
			System.out.println("Customer Details:");
			System.out.println("ID: " + customer.getCustomerId());
			System.out.println("First Name: " + customer.getFirstName());
			System.out.println("Last Name: " + customer.getLastName());
			System.out.println("Email: " + customer.getEmail());
			System.out.println("Phone Number: " + customer.getPhoneNumber());
			System.out.println("Username: " + customer.getUsername());
			// Do not print the password for security reasons
			System.out.println("----------------------------");
		} else {
			System.out.println("Customer not found.");
		}
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
			Car c2 = carService.addCar(car);
			if (c2 != null) {
				System.out.println("Car details added successfully.");
				printCarDetails(car);
			}
		} catch (SomethingWentWrongException e) {
			throw new SomethingWentWrongException("Something went wrong while adding the car inside the System.");
		}
	}

	private static void updateCar(Scanner sc) throws SomethingWentWrongException, CarNotAvailableException {
		System.out.print("Enter Car ID to update: ");
		long carId = sc.nextLong();
		sc.nextLine();

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
			Car c2 = carService.updateCar(car);
			if (c2 != null) {
				System.out.println("Car details Updated successfully.");
				printCarDetails(car);
			}
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
			System.out.println("------------------------------------------");
			System.out.println("Car has been deleted from the system.");
			System.out.println("------------------------------------------");
		} catch (SomethingWentWrongException e) {
			throw new CarNotAvailableException("Car was not fount in the System.");
		}
	}

	public static void getAllCars() throws SomethingWentWrongException {
		CarService carService = new CarServiceImpl();
		try {
			List<Car> list = carService.getAllCars();
			for (Car car : list) {
				printCarDetails(car);
			}
		} catch (Exception e) {
			throw new SomethingWentWrongException("Some thing went wrong, Try again later.");
		}
	}

	public static void addCustomer(Scanner sc) {

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
			Customer c1 = cs.addCustomer(customer);
			System.out.println("Customer Added successfully.");
			System.out.println();
			printCustomerDetails(c1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void updateCustomer(Scanner sc) {
		System.out.print("Enter Customer ID to update: ");
		long customerId = sc.nextLong();
		sc.nextLine();

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
		customer.setCustomerId(customerId);

		CustomerService cs = new CustomerServiceImpl();
		try {
			Customer c1 = cs.updateCustomer(customer);
			System.out.println("Customer updated successfully.");
			printCustomerDetails(c1);
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
			System.out.println("Customer Deleted Successfully.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void getAllCustomers() {
		CustomerService cs = new CustomerServiceImpl();
		try {
			List<Customer> list = cs.getAllCustomers();
			for (Customer c1 : list) {
				printCustomerDetails(c1);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void getCustomerById(Scanner sc) {
		System.out.print("Enter Customer ID to get details: ");
		long customerId = sc.nextLong();

		CustomerService cs = new CustomerServiceImpl();
		try {
			Customer c1 = cs.getCustomerById(customerId);
			printCustomerDetails(c1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void getCustomerByUsername(Scanner sc) {
		System.out.print("Enter Customer Username to get details: ");
		String username = sc.next();

		CustomerService cs = new CustomerServiceImpl();
		try {
			Customer c1 = cs.getCustomerByUsername(username);
			printCustomerDetails(c1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void getAllFeedbacks() {
		FeedbackService fs = new FeedbackServiceImpl();
		try {
			List<Feedback> feedbacks = fs.getAllFeedbacks();
			feedbacks.forEach(f -> System.out.println(f.getFeedbackDetails()));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void getFeedbackById(Scanner sc) {
		System.out.print("Enter Feedback ID to get details: ");
		long feedbackId = sc.nextLong();

		FeedbackService fs = new FeedbackServiceImpl();
		try {
			Feedback f = fs.getFeedbackById(feedbackId);
			System.out.println(f.getFeedbackDetails());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void getFeedbacksByCustomer(Scanner sc) {
		System.out.print("Enter Customer ID to get feedbacks: ");
		long customerId = sc.nextLong();

		FeedbackService fs = new FeedbackServiceImpl();
		try {
			List<Feedback> feedbacks = fs.getFeedbacksByCustomer(customerId);
			feedbacks.forEach(f -> System.out.println(f.getFeedbackDetails()));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void getFeedbacksByCar(Scanner sc) {
		System.out.print("Enter Car ID to get feedbacks: ");
		long carId = sc.nextLong();

		FeedbackService fs = new FeedbackServiceImpl();
		try {
			List<Feedback> feedbacks = fs.getFeedbacksByCar(carId);
			feedbacks.forEach(f -> System.out.println(f.getFeedbackDetails()));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void getAllPayments() {
		PaymentService ps = new PaymentServiceImpl();
		try {
			List<Payment> payments = ps.getAllPayments();
			payments.forEach(p -> CustomerUI.generateReceipt(p));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void getAllReservations() {
		ReservationService rs = new ReservationServiceImpl();
		try {
			List<Reservation> revs = rs.getAllReservations();
			revs.forEach(r -> r.printReservationDetails());
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
			List<Reservation> revs = rs.getReservationsBetweenDates(LocalDate.parse(startDate),
					LocalDate.parse(endDate));
			revs.forEach(r -> r.printReservationDetails());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public static void login(Scanner sc)
			throws SomethingWentWrongException, CarNotAvailableException, NoRecordFoundException {
		System.out.print("Enter admin username: ");
		String username = sc.next();

		System.out.print("Enter admin password: ");

		String password = sc.next();

		if (username.equals(Admin.USERNAME.getValue()) && password.equals(Admin.PASSWORD.getValue())) {
			System.out.println("Admin login successfull !");
			System.out.println("Welcome Ankit !");
			System.out.println("-----------------------------------------------------------------------------");
		} else {
			System.out.println("Invalid username or password.");
		}
	}
}
