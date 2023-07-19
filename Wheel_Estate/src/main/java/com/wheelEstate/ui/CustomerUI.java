package com.wheelEstate.ui;

import java.util.Scanner;

public class CustomerUI {

	static void displayCustomerMenu() {
		System.out.println("╔═════════════ Customer Menu ══════════════╗");
		System.out.println("║ 1. Search and Filter Cars                ║");
		System.out.println("║ 2. View Rental Policies                  ║");
		System.out.println("║ 3. Check Car Availability                ║");
		System.out.println("║ 4. View Rental Charges                   ║");
		System.out.println("║ 5. Add Payment Method                    ║");
		System.out.println("║ 6. View Rewards and Discounts            ║");
		System.out.println("║ 7. Provide Feedback and Ratings          ║");
		System.out.println("║ 8. Contact Customer Support              ║");
		System.out.println("║ 9. Make Reservation                      ║");
		System.out.println("║ 10. Cancel Reservation                   ║");
		System.out.println("║ 0. Logout                                ║");
		System.out.println("╚═════════════════════════════════════════=╝");
	}

	static void customerMenu(Scanner sc) {
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
				viewRewardsAndDiscounts();
				break;
			case 7:
				provideFeedbackAndRatings(sc);
				break;
			case 8:
				contactCustomerSupport(sc);
				break;
			case 9:
				makeReservation(sc);
				break;
			case 10:
				cancelReservation(sc);
				break;
			case 0:
				System.out.println("Logout Successfully");
				break;
			default:
				System.out.println("Invalid Selection, try again");
			}
		} while (choice != 0);
	}

	private static void contactCustomerSupport(Scanner sc) {
		// TODO: Implement the method
	}

	private static void provideFeedbackAndRatings(Scanner sc) {
		// TODO: Implement the method
	}

	private static void viewRewardsAndDiscounts() {
		// TODO: Implement the method
	}

	private static void addPaymentMethod(Scanner sc) {
		// TODO: Implement the method
	}

	private static void viewRentalCharges(Scanner sc) {
		// TODO: Implement the method
	}

	private static void checkCarAvailability(Scanner sc) {
		// TODO: Implement the method
	}

	private static void viewRentalPolicies() {
		// TODO: Implement the method
	}

	private static void searchAndFilterCars(Scanner sc) {
		// TODO: Implement the method
	}

	private static void makeReservation(Scanner sc) {
		// TODO: Implement the method
	}

	private static void cancelReservation(Scanner sc) {
		// TODO: Implement the method
	}

	public static void login(Scanner sc) {
		// TODO: Implement the method
	}

	public static void customerRegister(Scanner sc) {
		// TODO: Implement the method
	}

}
