package components;

import java.time.LocalDate;

public class Credit extends Flow {
    public Credit(String comment, double amount, int targetAccountNumber, LocalDate date) {
        super(comment, amount, targetAccountNumber, true, date);
    }
}
