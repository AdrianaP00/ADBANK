package components;

import java.time.LocalDate;

//1.3.1 Adaptation of the table of accounts
public abstract class Flow {
	private static int flowCounter = 0;

	private int flowId;
	private String comment;
	private double amount;
	private int targetAccountNumber;
	private boolean effect;
	private LocalDate date;

	public Flow(String comment, double amount, int targetAccountNumber, boolean effect, LocalDate date) {
		this.flowId = ++flowCounter;
		this.comment = comment;
		this.amount = amount;
		this.targetAccountNumber = targetAccountNumber;
		this.effect = effect;
		this.date = date;
	}

	public int getFlowId() {
		return flowId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getTargetAccountNumber() {
		return targetAccountNumber;
	}

	public void setTargetAccountNumber(int targetAccountNumber) {
		this.targetAccountNumber = targetAccountNumber;
	}

	public boolean isEffect() {
		return effect;
	}

	public void setEffect(boolean effect) {
		this.effect = effect;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Flow{" + "flowId=" + flowId + ", comment='" + comment + '\'' + ", amount=" + amount
				+ ", targetAccountNumber=" + targetAccountNumber + ", effect=" + effect + ", date=" + date + '}';
	}
}
