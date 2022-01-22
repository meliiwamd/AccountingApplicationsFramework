package Transaction;

import java.time.LocalDate;
import java.util.List;

public class TransactionsList {
    private List<Transaction> transactions = new List<Transaction>();

    public void addTransaction(Account sender, Account receiver, float amount) {
        float senderCurrentDeposit = sender.getBalance();
        float receiverCurrentDeposit = receiver.getBalance();

        if(senderCurrentDeposit > amount + 5000)
        {
            sender.setBalance(senderCurrentDeposit - amount);
            receiver.setBalance(receiverCurrentDeposit + amount);

            Transaction transaction = new Transaction(amount, sender, receiver, LocalDateTime.now());
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
