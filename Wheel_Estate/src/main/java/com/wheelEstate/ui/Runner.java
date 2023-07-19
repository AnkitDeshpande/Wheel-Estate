package com.wheelEstate.ui;

import java.util.Scanner;

import com.wheelEstate.color.Console;

public class Runner {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int choice = 0;
		do {
			System.out.println("╔═══════════════ Main Menu ═══════════════╗");
			System.out.println("║ 1. Admin Login                          ║");
			System.out.println("║ 2. Customer Login                       ║");
			System.out.println("║ 3. Customer Registration                ║");
			System.out.println("║ 0. Exit                                 ║");
			System.out.println("╚═════════════════════════════════════════╝");
			System.out.print("Enter Selection: ");

			choice = sc.nextInt();
			switch (choice) {
			case 1:
				AdminUI.login(sc);
				AdminUI.adminMenu(sc); // After admin login, display the admin menu
				break;
			case 2:
				CustomerUI.login(sc);
				break;
			case 3:
				CustomerUI.customerRegister(sc);
				break;
			case 0:
				System.out.println("Thanks for using the services");
				break;
			default:
				System.out.println("Invalid Selection, try again");
			}
		} while (choice != 0);
		sc.close();
	}
}
