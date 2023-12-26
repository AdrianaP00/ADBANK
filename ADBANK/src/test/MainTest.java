package test;

import components.Client;

import java.util.ArrayList;
import java.util.Arrays;

//1.1.2 Creation of main class for tests
public class MainTest {
	public static void main(String[] args) {
		// Load the table using a method
		ArrayList<Client> clients = loadClients(3);

		// Display the contents of the table
		displayClients(clients);
	}

	// Method to generate a client test set
	private static ArrayList<Client> loadClients(int numberOfClients) {
		ArrayList<Client> clients = new ArrayList();
		for (int i = 0; i < numberOfClients; i++) {

			clients.add(generateClient("name" + (i + 1), "firstname" + (i + 1)));
		}
		return clients;
	}

	// Method to display the contents of the table using a stream
	private static void displayClients(ArrayList<Client> clients) {
		clients.stream().map(Client::toString).forEach(System.out::println);
	}

	// Method to generate a single client
	private static Client generateClient(String firstName, String lastName) {
		return new Client(firstName, lastName);
	}
}
