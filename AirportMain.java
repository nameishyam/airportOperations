package mainFunctions;

import airportOperations.*;

import java.util.Scanner;

public class AirportMain {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		try {
			System.out.println("Welcome to the Airport Management System!");

			int iterations = 0;
			while (iterations < 5) { // Change the condition as needed
				System.out.println("\nSelect an operation:");
				System.out.println("1. View Current Flights");
				System.out.println("2. Flight Booking");
				System.out.println("3. Security Check");
				System.out.println("4. Check-in");
				System.out.println("5. Exit");

				System.out.print("Enter your choice (1-5): ");

				if (!scanner.hasNextLine()) {
					// No more input, break out of the loop
					break;
				}

				// Read the next line of input
				String input = scanner.nextLine();

				if (input.isEmpty()) {
					// No input provided, inform the user and continue to the next iteration
					System.out.println("Please enter a choice.");
					continue;
				}

				int choice;
				try {
					choice = Integer.parseInt(input);
				} catch (NumberFormatException e) {
					// Handle non-integer input
					System.out.println("Invalid input. Please enter a number between 1 and 5.");
					continue;
				}

				switch (choice) {
				case 1:
					// Checking current flights
					AirportFlights.main(null);
					break;

				case 2:
					// Ticket booking
					FlightTicketPrinter.main(null);
					break;

				case 3:
					// Security check
					SecurityCheck.main(null);
					break;

				case 4:
					// Check in
					System.out.println("\nPerforming check in procedure");
					System.out.println("Enter your flight name: ");
					String flight = scanner.nextLine();
					System.out.println(
							"You have passed check in successfully, You can proceed to get on board\nHappy Journey!!!");
					break;

				case 5:
					// Exiting the program
					System.out.println("Exiting the Airport online database");
					break;

				default:
					System.out.println("Invalid choice. Please enter a number between 1 and 4.");
					break;
				}

				iterations++;
			}
		} finally {
			// Close the scanner
			if (scanner != null) {
				scanner.close();
			}
		}
	}
}
