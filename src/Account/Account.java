package Account;

import Customer.Customer;

import java.time.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Account {
    private static final AtomicInteger counter = new AtomicInteger(0);
    private int accountId;
    private float balance;
    private LocalDateTime dateCreated;
    private Customer customer;

    public Account(float balance, LocalDateTime dateCreated, Customer customer) {
        this.accountId = counter.incrementAndGet();
        this.balance = balance;
        this.dateCreated = dateCreated;
        this.customer = customer;
    }

    public int getAccountId() {
        return accountId;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public Customer getCustomer() {
        return customer;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }
}