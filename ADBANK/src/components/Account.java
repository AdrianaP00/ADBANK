package components;

import java.util.Hashtable;
import java.util.Map;

//1.2.1 Creation of the account class
public abstract class Account {
	protected static int accountCounter = 0;

	protected int accountNumber;
	protected String label;
	protected double balance;
	protected Client client;

	public Account(String label, Client client) {

		this.label = label;
		this.client = client;
		this.accountNumber = ++accountCounter;
		this.balance = 0.0;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {

		this.label = label;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double amount) {

		setBalance(amount);
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {

		this.client = client;
	}

	@Override
	public String toString() {
		return "Account{" + "accountNumber=" + accountNumber + ", label='" + label + '\'' + ", balance=" + balance
				+ ", client=" + client + '}';
	}
}
