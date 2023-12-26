package components;

import java.time.LocalDate;

public class Debit extends Flow {
    public Debit(String comment, double amount, int targetAccountNumber, LocalDate date) {
        super(comment, amount, targetAccountNumber, false, date);
    }
}
