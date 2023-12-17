package airportOperations;

import java.util.Scanner;
import java.util.Random;

public class SecurityCheck {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		try {
			System.out.println("Welcome to the Security Check!");

			System.out.print("Enter your name: ");
			String passengerName = scanner.nextLine();

			System.out.print("Enter the number of bags you have: ");
			int numberOfBags = scanner.nextInt();
			scanner.nextLine(); // Consume the newline character

			// Simulate a security check (random success or failure)
			boolean securityCheckSuccess = performSecurityCheck(passengerName, numberOfBags);

			// Print the result of the security check
			if (securityCheckSuccess) {
				System.out.println("Security Check Successful! You may proceed to the boarding area.");
			} else {
				System.out.println("Security Check Failed! Additional screening may be required.");
			}
		} finally {
			// Close the scanner
			if (scanner != null) {
				scanner.close();
			}
		}
	}

	private static boolean performSecurityCheck(String passengerName, int numberOfBags) {
		System.out.println("\nPerforming Security Check for " + passengerName + "...");
		System.out.println("Number of bags: " + numberOfBags);

		// Simulate a random result for the security check
		Random random = new Random();
		return random.nextBoolean(); // Randomly return true or false
	}
}
