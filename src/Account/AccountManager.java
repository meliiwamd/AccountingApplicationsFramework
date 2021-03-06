package Account;

import Customer.Customer;

import java.time.*;
import java.util.*;

public class AccountManager {

    public List<Account> Accounts = new ArrayList<Account>();

    public void addAccount(Float balance, Customer customer) {
        Accounts.add(new Account(balance, LocalDateTime.now(), customer));
    }

    public void closeAccount(Account account) {
        Accounts.remove(account);
    }

    public boolean withdrawMoney(Account account, float amount) {
        if (amount > account.getBalance())
            return false;

        account.setBalance(account.getBalance() - amount);
        return true;
    }

    public void depositMoney(Account account, float amount) {
        account.setBalance(account.getBalance() + amount);
    }

    public Account FindById(int accountId) {
        for (Account account : Accounts)
            if (account.getAccountId() == accountId)
                return account;
        return null;
    }

    public List<Account> GetAllAccountsOfCustomer(Customer customer) {
        List<Account> results = new LinkedList<Account>();

        for (Account account : Accounts)
            if (account.getCustomer().getCustomerId() == customer.getCustomerId())
                results.add(account);
        return results;
    }
}
