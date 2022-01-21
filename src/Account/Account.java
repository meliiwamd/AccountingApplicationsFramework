package Account;

import Customer.Customer;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

public class Account {
    private static final AtomicInteger counter = new AtomicInteger(0);
    private int accountId;
    private float balance;
    private LocalDate dateCreated;
    private Customer customer;

    public Account(float balance, LocalDate dateCreated, Customer customer) {
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

    public LocalDate getDateCreated() {
        return dateCreated;
    }
}