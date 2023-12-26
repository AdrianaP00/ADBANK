package components;

import java.time.LocalDate;

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
