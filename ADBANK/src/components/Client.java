package components;

import java.util.concurrent.atomic.AtomicInteger;

//1.1.1 Creation of the client class
public class Client {
    private static final AtomicInteger clientCounter = new AtomicInteger(0);

    private final int clientNumber;
    private String name;
    private String firstName;

    // Constructor
    public Client(String name, String firstName) {
        this.name = name;
        this.firstName = firstName;
        this.clientNumber = clientCounter.incrementAndGet();
    }

    // Accessors
    public int getClientNumber() {
        return clientNumber;
    }

    public String getFirstName() {
        return name;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // toString() method
    @Override
    public String toString() {
        return "Client{" +
                "clientNumber=" + clientNumber +
                ", name='" + name + '\'' +
                ", firstName='" + firstName + '\'' +
                '}';
    }
}
