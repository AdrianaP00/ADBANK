package components;

import java.time.LocalDate;

//1.3.3 Creation of the Transfert, Credit, Debit classes
public class Debit extends Flow {
    public Debit(String comment, double amount, int targetAccountNumber, LocalDate date) {
        super(comment, amount, targetAccountNumber, false, date);
    }
}
