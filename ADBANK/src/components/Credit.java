package components;

import java.time.LocalDate;

//1.3.3 Creation of the Transfert, Credit, Debit classes
public class Credit extends Flow {
    public Credit(String comment, double amount, int targetAccountNumber, LocalDate date) {
        super(comment, amount, targetAccountNumber, true, date);
    }
}
