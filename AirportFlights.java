package airportOperations;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Flight {
	private String flightNumber;
	private String status;

	public Flight(String flightNumber, String status) {
		this.flightNumber = flightNumber;
		this.status = status;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public String getStatus() {
		return status;
	}
}

public class AirportFlights {
	public static void main(String[] args) {
		List<Flight> flights = initializeFlights();

		System.out.println("Current Flights in the Airport:");
		System.out.println("==================================");
		System.out.printf("%-15s%s%n", "Flight Number", "Status");
		System.out.println("==================================");

		for (Flight flight : flights) {
			System.out.printf("%-15s%s%n", flight.getFlightNumber(), flight.getStatus());
		}
	}

	private static List<Flight> initializeFlights() {
		List<Flight> flights = new ArrayList<>();
		Random random = new Random();

		// Simulate 20 flights with random 3-digit flight numbers and different statuses
		for (int i = 1; i <= 20; i++) {
			String flightNumber = "FL" + String.format("%03d", random.nextInt(1000)); // "FL" + Random 3-digit flight number
			String status;
			if (i % 3 == 0) {
				status = "Standby";
			} else if (i % 3 == 1) {
				status = "Incoming";
			} else {
				status = "Ready to Leave";
			}
			flights.add(new Flight(flightNumber, status));
		}

		return flights;
	}
}
