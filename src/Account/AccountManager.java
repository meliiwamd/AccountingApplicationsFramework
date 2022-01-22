package Account;

import Customer.Customer;
import java.time.*;
import java.util.*;  

public class AccountManager {

    private static List<Account> Accounts = new ArrayList<Account>();  

    public Account addAccount(Float balance, Customer customer) {
        Account NewAccount = new Account(balance, LocalDate.now(), customer);
        Accounts.add(NewAccount);
        return NewAccount;
    }

    public void closeAccount(Account account) {
        Accounts.remove(account);
    }

    public void withdrawMoney(Account account, float amount) {
        if(amount > account.getBalance()){
            return;
        }

        account.setBalance(account.getBalance() - amount);
    }

    public void depositMoney(Account account, float amount) {
        account.setBalance(account.getBalance() + amount);
    }
}
