package test;

import components.Account;
import components.Client;
import components.Credit;
import components.CurrentAccount;
import components.Debit;
import components.Flow;
import components.SavingsAccount;
import components.Transfer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Map;

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
		// 1.3.1 Adaptation of the table of accounts
		Hashtable<Integer, Account> accountMap = createAccountMap(accounts);
		displaySortedAccountMap(accountMap);
		//1.3.4
		ArrayList<Flow> flows = generateFlows(accounts);
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

	public static Hashtable<Integer, Account> createAccountMap(ArrayList<Account> accounts) {
		Hashtable<Integer, Account> accountTable = new Hashtable<>();
		for (Account account : accounts) {
			accountTable.put(account.getAccountNumber(), account);
		}
		return accountTable;
	}

	// Helper method to display sorted account map
	public static void displaySortedAccountMap(Hashtable<Integer, Account> accountTable) {
		accountTable.entrySet().stream()
				.sorted(Map.Entry.comparingByValue((a, b) -> Double.compare(a.getBalance(), b.getBalance())))
				.forEach(entry -> System.out.println(entry.getValue()));
	}
	private static ArrayList<Flow> generateFlows(ArrayList<Account>  accounts) {
		LocalDate currentDate = LocalDate.now().plusDays(2);
		ArrayList<Flow> flows = new ArrayList<Flow>();

		// 1.3.4 a debit of 50€ from account n°1
		flows.add(new Debit("Debit operation", 50, 1, currentDate));

		// A credit of 100.50€ on all current accounts in the array of accounts
		accounts.stream().forEach(
				account -> flows.add(new Credit("Credit operation", 100.50, account.getAccountNumber(), currentDate)));

		// A credit of 1500€ on all savings accounts in this same array
		accounts.stream().forEach(
				account -> flows.add(new Credit("Credit operation", 1500, account.getAccountNumber(), currentDate)));

		// A transfer of 50 € from account n ° 1 to account n ° 2
		flows.add(new Transfer("Transfer operation", 50, 2, 1, currentDate));

		return flows;
	}
}
