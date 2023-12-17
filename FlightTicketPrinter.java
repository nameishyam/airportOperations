package airportOperations;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class FlightTicketPrinter {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		try {
			// Get user inputs
			System.out.println("===== Flight Booking =====");
			System.out.print("Enter the number of passengers: ");
			int numberOfPassengers = scanner.nextInt();
			scanner.nextLine(); // Consume the newline character

			for (int i = 0; i < numberOfPassengers; i++) {
				System.out.print("Enter passenger name " + (i + 1) + ": ");
				String passengerName = scanner.nextLine();

				System.out.print("Enter departure city: ");
				String departureCity = scanner.nextLine();

				System.out.print("Enter destination city: ");
				String destinationCity = scanner.nextLine();

				// Get the date for available flights
				System.out.print("Enter the date for available flights (e.g., 2023-12-20): ");
				String inputDate = scanner.nextLine();

				// Validate date format
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				dateFormat.setLenient(false);
				Date date;
				try {
					date = dateFormat.parse(inputDate);
				} catch (ParseException e) {
					System.out.println("Invalid date format. Please use the format yyyy-MM-dd.");
					return;
				}

				// Display available flights for the entered date
				Map<String, String[]> availableFlights = getAvailableFlights(dateFormat.format(date));

				if (availableFlights.isEmpty()) {
					System.out.println("No available flights for the entered date.");
					return;
				}

				System.out.println("\nAvailable Flights:");
				System.out.println("Flight Number   Departure Time        Arrival Time");

				int numberOfAvailableFlights = Math.min(availableFlights.size(), new Random().nextInt(6) + 5);
				List<String> flightNumbers = new ArrayList<>(availableFlights.keySet());
				Collections.shuffle(flightNumbers);

				for (int j = 0; j < numberOfAvailableFlights; j++) {
					String flightNumber = flightNumbers.get(j);
					String departureTime = availableFlights.get(flightNumber)[0];
					String arrivalTime = availableFlights.get(flightNumber)[1];
					System.out.printf("%-15s%-23s%s%n", flightNumber, departureTime, arrivalTime);
				}

				// Prompt user to select a flight
				System.out.print("Enter the flight number you want to book (or type 'exit' to end): ");
				String selectedFlight = scanner.nextLine();

				if (selectedFlight.equalsIgnoreCase("exit")) {
					break;
				}

				// Generate a random seat number
				String seatNumber = generateRandomSeatNumber();

				// Generate and print the boarding pass with selected flight information
				String boardingPass = generateBoardingPass(passengerName, selectedFlight, seatNumber, departureCity,
						destinationCity, availableFlights);

				// Save the boarding pass to the file named after the passenger's name
				String fileName = "BoardingPass_" + passengerName.replaceAll("\\s+", "") + ".txt";
				saveBoardingPassToFile(boardingPass, fileName);

				// Print the boarding pass to the console
				System.out.println(boardingPass);
				System.out.println("Boarding pass saved to file: " + fileName);
			}
		} catch (Exception e) {
			System.err.println("An error occurred: " + e.getMessage());
		} finally {
			// Close the scanner
			if (scanner != null) {
				scanner.close();
			}
		}
	}

	private static Map<String, String[]> getAvailableFlights(String date) {
		// Simulated data for available flights (replace with an actual data source)
		Map<String, String[]> availableFlights = new HashMap<>();

		// Generate a random number of flights between 5 and 10
		int numberOfFlights = new Random().nextInt(6) + 5;

		// Generate flights
		for (int i = 0; i < numberOfFlights; i++) {
			String flightNumber = generateFlightNumber();
			String departureTime = generateRandomTime();
			String arrivalTime = generateRandomTime();
			availableFlights.put(flightNumber, new String[] { departureTime, arrivalTime });
		}

		return availableFlights;
	}

	private static String generateFlightNumber() {
		// Generate a random flight number (for illustration purposes)
		return "FL" + (100 + new Random().nextInt(900));
	}

	private static String generateRandomTime() {
		// Generate a random time in HH:mm format (for illustration purposes)
		int hours = new Random().nextInt(24);
		int minutes = new Random().nextInt(60);
		return String.format("%02d:%02d", hours, minutes);
	}

	private static String generateRandomSeatNumber() {
		// Generate a random seat number (for illustration purposes)
		return "Seat" + (1 + new Random().nextInt(50));
	}

	private static String generateBoardingPass(String passengerName, String selectedFlight, String seatNumber,
			String departureCity, String destinationCity, Map<String, String[]> availableFlights) {
		StringBuilder boardingPass = new StringBuilder();
		boardingPass.append("\n************************************************************\n");
		boardingPass.append("                 Boarding Pass                  \n");
		boardingPass.append("************************************************************\n");
		boardingPass.append(String.format("%-20s%-25s%n", "Passenger:", passengerName));
		boardingPass.append(String.format("%-20s%-25s%n", "Flight:", selectedFlight));
		boardingPass.append(String.format("%-20s%-25s%n", "Seat:", seatNumber));
		boardingPass.append(String.format("%-20s%-25s%n", "From:", departureCity));
		boardingPass.append(String.format("%-20s%-25s%n", "To:", destinationCity));
		boardingPass.append(String.format("%-20s%-25s%n", "Departure:", availableFlights.get(selectedFlight)[0]));
		boardingPass.append(String.format("%-20s%-25s%n", "Arrival:", availableFlights.get(selectedFlight)[1]));
		boardingPass.append("************************************************************\n");
		boardingPass.append("Thank You! Have a Happy Journey!\n");
		boardingPass.append("************************************************************\n");

		return boardingPass.toString();
	}

	private static void saveBoardingPassToFile(String boardingPass, String fileName) {
		try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, true))) {
			writer.println(boardingPass);
		} catch (IOException e) {
			System.err.println("Error saving boarding pass to file: " + e.getMessage());
		}
	}
}