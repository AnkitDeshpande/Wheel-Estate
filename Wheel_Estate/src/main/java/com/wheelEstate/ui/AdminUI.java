package com.wheelEstate.ui;

import java.util.Scanner;

public class AdminUI {
	static void displayAdminMenu() {
		System.out.println("╔══════════════ Admin Menu ══════════════╗");
		System.out.println("║ 1. Add new car                        ║");
		System.out.println("║ 2. Update car                         ║");
		System.out.println("║ 3. Delete car                         ║");
		System.out.println("║ 4. View list of cars in inventory     ║");
		System.out.println("║ 5. View stats                         ║");
		System.out.println("║ 6. Manage Customer Accounts           ║");
		System.out.println("║ 7. Manage Employee Accounts           ║");
		System.out.println("║ 8. Generate Reports                   ║");
		System.out.println("║ 9. Approve/Deny Customer Registrations║");
		System.out.println("║ 10. View Rental History               ║");
		System.out.println("║ 0. Logout                             ║");
		System.out.println("╚═══════════════════════════════════════╝");
	}

	static void adminMenu(Scanner sc) {
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
				 viewCarList();
				break;
			case 5:
				 viewStats();
				break;
			case 6:
				 manageCustomerAccounts(sc);
				break;
			case 7:
				 manageEmployeeAccounts(sc);
				break;
			case 8:
				 generateReports();
				break;
			case 9:
				 approveDenyCustomerRegistrations(sc);
				break;
			case 10:
				viewRentalHistory();
				break;
			case 0:
				System.out.println("Logout Successfully");
				break;
			default:
				System.out.println("Invalid Selection, try again");
			}
		} while (choice != 0);
	}

	private static void viewRentalHistory() {
		// TODO Auto-generated method stub
		
	}

	private static void approveDenyCustomerRegistrations(Scanner sc) {
		// TODO Auto-generated method stub
		
	}

	private static void generateReports() {
		// TODO Auto-generated method stub
		
	}

	private static void manageEmployeeAccounts(Scanner sc) {
		// TODO Auto-generated method stub
		
	}

	private static void manageCustomerAccounts(Scanner sc) {
		// TODO Auto-generated method stub
		
	}

	public static void login(Scanner sc) {
		// TODO Auto-generated method stub

	}

	public static void addCar(Scanner sc) {
		// TODO Auto-generated method stub

	}

	public static void updateCar(Scanner sc) {
		// TODO Auto-generated method stub

	}

	public static void deleteCar(Scanner sc) {
		// TODO Auto-generated method stub

	}

	public static void viewCarList() {
		// TODO Auto-generated method stub

	}

	public static void viewStats() {
		// TODO Auto-generated method stub

	}

}
