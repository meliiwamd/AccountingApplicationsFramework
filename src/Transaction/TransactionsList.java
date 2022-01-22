package Transaction;

import java.time.LocalDate;
import java.util.List;

public class TransactionsList {
    private List<Transaction> transactions = new List<Transaction>();

    public void addTransaction(Transaction transaction) {
        Account sender = transaction.getSender();
        float senderCurrentDeposit = sender.getBalance();

        Account receiver = transaction.getReceiver();
        float receiverCurrentDeposit = receiver.getBalance();

        float amount = transaction.getAmount();

        if(senderCurrentDeposit > amount + 5)
        {
            sender.setBalance(senderCurrentDeposit - amount);
            receiver.setBalance(receiverCurrentDeposit + amount);
            transactions.add(transaction);
        }
        else
        {
            // Not enough money
            System.out.println("Not enough money");
        }
    }

   public List<Transaction> search(LocalDate startPeriod, LocalDate endPeriod) {
       List<Transaction> filtered = new List<Transaction>();

       for (Transaction trans : transactions)
       {
           if(trans.getDateOfTransaction().isBefore(endPeriod) && trans.getDateOfTransaction().isAfter(startPeriod))
           {
               filtered.add(trans);
           }
       }
       return filtered;
   }
}
