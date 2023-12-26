package components;

import java.time.LocalDate;

//1.3.3 Creation of the Transfert, Credit, Debit classes
public class Transfer extends Flow {
	private int issuingAccountNumber;

	public Transfer(String comment, double amount, int targetAccountNumber, int issuingAccountNumber, LocalDate date) {
		super(comment, amount, targetAccountNumber, true, date);
		this.issuingAccountNumber = issuingAccountNumber;
	}

	public int getIssuingAccountNumber() {
		return issuingAccountNumber;
	}

	public void setIssuingAccountNumber(int issuingAccountNumber) {
		this.issuingAccountNumber = issuingAccountNumber;
	}

	@Override
	public String toString() {
		return "Transfer{" + "issuingAccountNumber=" + issuingAccountNumber + "} " + super.toString();
	}
}
