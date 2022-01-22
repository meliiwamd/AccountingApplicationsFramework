package Transaction;

import Account.Account;
import Customer.Customer;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

public class Transaction {
    private static final AtomicInteger counter = new AtomicInteger(0);
    private int transactionId;
    private float amount;
    private Account sender;
    private Account receiver;
    private LocalDateTime dateOfTransaction;

    public Transaction(float amount, Account sender, Account receiver, LocalDateTime dateOfTransaction) {
        this.transactionId = counter.incrementAndGet();
        this.amount = amount;
        this.sender = sender;
        this.receiver = receiver;
        this.dateOfTransaction = dateOfTransaction;
    }

    public float getAmount() {
        return amount;
    }

    public Account getSender() {
        return sender;
    }

    public Account getReceiver() {
        return receiver;
    }

    public LocalDateTime getDateOfTransaction(){
        return dateOfTransaction;
    }

}
