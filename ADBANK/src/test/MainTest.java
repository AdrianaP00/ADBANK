package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import components.Account;
import components.Client;
import components.Credit;
import components.CurrentAccount;
import components.Debit;
import components.Flow;
import components.FlowType;
import components.SavingsAccount;
import components.Transfer;

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
		// 1.3.4
		ArrayList<Flow> flows = generateFlows(accounts);
		updateBalances(flows, accountMap);
		displaySortedAccountMap(accountMap);
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

	private static ArrayList<Flow> generateFlows(ArrayList<Account> accounts) {
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

	// Helper method to update balances based on flows
	private static void updateBalances(ArrayList<Flow> flows, Map<Integer, Account> accountMap) {
		for (Flow flow : flows) {
			Account account = accountMap.get(flow.getTargetAccountNumber());
			if (account != null) {
				FlowType actualFlowType = FlowType.valueOf(flow.getClass().getSimpleName().toUpperCase());
				account.setBalance(flow, actualFlowType);
			}
		}

		// 1.3.5
		// Create a method accepting this array of Flows, and the Map of accounts
		// (paragraph 1.3.1), to
		// update the balances of the accounts concerned. For each Flow, it will update
		// the corresponding
		// (Transfer) accounts
		updateIssuerBalance(flows, accountMap);

		// Display message for accounts with negative balance
		accountMap.values().stream().filter(acc -> acc.getBalance() < 0).findFirst()
				.ifPresent(acc -> System.out.println("Warning: Account with negative balance found."));
	}

	private static void updateIssuerBalance(ArrayList<Flow> flows, Map<Integer, Account> accountMap) {
		for (Flow flow : flows) {
			FlowType actualFlowType = FlowType.valueOf(flow.getClass().getSimpleName().toUpperCase());
			if (FlowType.TRANSFER.equals(actualFlowType)) {
				Transfer transferFlow = (Transfer) flow;
				Account account = accountMap.get(transferFlow.getIssuingAccountNumber());

				account.setBalance(transferFlow, actualFlowType);
			}
		}
	}

	/*
	 * 2.1 JSON file of flows(30mn – 4 pts)
	 * 
	 * I'm not able to archive this point only using Java SE
	 * 
	 * Because JSONObject is an EE type
	 * 
	 * https://docs.oracle.com/javaee%2F7%2Fapi%2F%2F/javax/json/JsonObject.html
	 */

	/*
	 * 2.2 XML file of account (30mn – 4 pts)
	 * 
	 * As junior I'm not able to archive this point in only 30min
	 * 
	 * Because to map Account type I need to manually map Client and Flow types
	 * 
	 * Probably this possible using something like jackson lib (as google
	 * worries)... but I don't know about that... I'm sorry
	 */

	/*
	 * Here an example of how to read from a file using java.nio.file.Path
	 * 
	 * ... readFromAFile (String filePath){
	 * 		try (BufferedReader reader =
	 * 			Files.newBufferedReader(Paths.get(filePath))){ ... }
	 * 		catch....
	 */

}
