package test;

import components.Account;
import components.Client;
import components.CurrentAccount;
import components.SavingsAccount;

import java.util.ArrayList;
import java.util.Arrays;

//1.1.2 Creation of main class for tests
public class MainTest {
	public static void main(String[] args) {
		// Load the table using a method
		ArrayList<Client> clients = loadClients(3);
		// Display the contents of the table
		displayClients(clients);
		// 1.2.3 Creation of the tablea account
		ArrayList<Account> accounts = generateAccounts(clients);
		displayAccounts(accounts);
	}

	// Method to generate a client test set
	private static ArrayList<Client> loadClients(int numberOfClients) {
		ArrayList<Client> clients = new ArrayList<Client>();
		for (int i = 0; i < numberOfClients; i++) {

			clients.add(generateClient("name" + (i + 1), "firstname" + (i + 1)));
		}
		return clients;
	}

	// Helper method to generate an array of accounts
	private static ArrayList<Account> generateAccounts(ArrayList<Client> clients) {
		ArrayList<Account> accounts = new ArrayList<Account>();
		clients.stream().forEach(client -> {
			accounts.add(new SavingsAccount("Savings", client));
			accounts.add(new CurrentAccount("Current", client));
		});

		return accounts;
	}

	// Method to display the contents of the table using a stream
	private static void displayClients(ArrayList<Client> clients) {
		clients.stream().map(Client::toString).forEach(System.out::println);
	}

	// Method to generate a single client
	private static Client generateClient(String firstName, String lastName) {
		return new Client(firstName, lastName);
	}

	// Helper method to display accounts
	private static void displayAccounts(ArrayList<Account> accounts) {
		System.out.println("Accounts:");
		accounts.stream().map(Account::toString).forEach(System.out::println);
		System.out.println();
	}
}
