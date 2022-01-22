package Transaction;

import java.time.LocalDateTime;

import Account.Account;

import java.util.*;


public class TransactionsList {
    public List<Transaction> transactions = new ArrayList<Transaction>();

    public void addTransaction(Account sender, Account receiver, float amount) {
        float senderCurrentDeposit = sender.getBalance();
        float receiverCurrentDeposit = receiver.getBalance();

        if (senderCurrentDeposit > amount + 5000) {
            sender.setBalance(senderCurrentDeposit - amount);
            receiver.setBalance(receiverCurrentDeposit + amount);

            Transaction transaction = new Transaction(amount, sender, receiver, LocalDateTime.now());
            transactions.add(transaction);
        } else {
            System.out.println("Not enough money");
        }
    }

    public List<Transaction> search(LocalDateTime startPeriod, LocalDateTime endPeriod) {
        List<Transaction> filtered = new ArrayList<Transaction>();

        for (Transaction trans : transactions)
            if (trans.getDateOfTransaction().isBefore(endPeriod) && trans.getDateOfTransaction().isAfter(startPeriod))
                filtered.add(trans);
        return filtered;
    }
}
