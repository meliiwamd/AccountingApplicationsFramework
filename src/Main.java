import java.util.Scanner;

import Account.Account;
import Account.AccountManager;
import Customer.Country;
import Customer.Customer;
import Customer.CustomerSpec;
import Customer.CustomersList;
import Customer.Gender;
import Transaction.Transaction;
import Transaction.TransactionsList;
import Customer.City;

public class Main {
    public static Scanner input = new Scanner(System.in);
    public static CustomersList customersList = new CustomersList();
    public static AccountManager accountManager = new AccountManager();
    public static TransactionsList transactionsList = new TransactionsList();

    public static void main(String[] args) {
        boolean isQuit = false;
        System.out.println("***Welcome to Accounting Application Framework***");

        String menu = "" +
                "1. Add customer" + "\n" +
                "2. Search in customers" + "\n" +
                "3. Edit customer" + "\n" +
                "4. Get all accounts of customer" + "\n" +
                "5. Add an account" + "\n" +
                "6. Close an account" + "\n" +
                "7. Withdraw money" + "\n" +
                "8. Deposit money" + "\n" +
                "9. Add transaction" + "\n" +
                "0. Exit" + "\n" + 
                "-1. Show Informations";

        System.out.println(menu);
        while(!isQuit){
            int command = Integer.parseInt(input.nextLine());
            if (command == 0){
                break;
            }
            exec(command);
            System.out.println("************************************************");

        }
    }

    public static void exec(int command){

        switch(command) {
            case -1: //add customer
                ShowInformation();
                break;
            case 1:
                AddCustomer();
                break;
            case 2:
              // code block
              break;
            case 3:
              // code block
              break;
            case 4:
              // code block
              break;
            case 5:
                AddAccount();
                break;
            case 6:
                CloseAccount();
                break;
            case 7:
                Withdraw();
                break;
            case 8:
                Deposit();
                break;
            case 9:
                AddTransaction();
                break;
            default:
                System.out.println("undefined command! try again");

       }
    }

    public static void ShowInformation(){
        System.out.println("Showing informations...");
        System.out.println("Customers:");
        
        System.out.println("Accounts:");
        for (Account account : accountManager.Accounts) {
            System.out.println("Account Id: " + account.getAccountId());
            System.out.println("Customer Id: " + account.getCustomer().getCustomerId());
            System.out.println("Balance: " + account.getBalance());
            System.out.println("###########");
        }
        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");

        System.out.println("Transactions:");
        for (Transaction transaction : transactionsList.transactions) {
            System.out.println("Transaction Id: " + transaction.getTransactionId());
            System.out.println("Sender Id: " + transaction.getSender().getAccountId());
            System.out.println("Receiver Id: " + transaction.getReceiver().getAccountId());
            System.out.println("Amount: " + transaction.getAmount());
            System.out.println("DateTime: " + transaction.getDateOfTransaction());
            System.out.println("###########");
        }

        
        System.out.println("Finished!");

        return;
    }

    public static Customer AddCustomer(){
        System.out.println("Adding new costumer...");

        System.out.println("FirstName: ");
        String firstName = input.nextLine();
       
        System.out.println("LastName: ");
        String lastName = input.nextLine();

        System.out.println("National ID: ");
        String nationalID = input.nextLine();

        System.out.println("Email: ");
        String email = input.nextLine();

        System.out.println("Address: ");
        String address = input.nextLine();

        System.out.println("Salary: ");
        Float salary = Float.parseFloat(input.nextLine());

        System.out.println("Gender: ");
        System.out.println("0: Male | 1: Female ");
        Integer integer_gender = Integer.parseInt(input.nextLine());
        Gender gender = Gender.values()[integer_gender];

        System.out.println("Country: ");
        System.out.println("0: USA | 1: FRANCE | 2: ENGLAND | 3: IRAN | 4: RUSSIA | 5: CANADA");
        Integer integer_County = Integer.parseInt(input.nextLine());
        Country country = Country.values()[integer_County];

        System.out.println("City: ");
        System.out.println("0: NEWYORK | 1: LOSANGELES | 2: WASHINGTON | 3: SANDIEGO | 4: TORONTO | 5: CALGARY");
        System.out.println("6: MONTREAL | 7: TEHRAN | 8: MASHHAD | 9: ISFAHAN | 10: TABRIZ | 11: PARIS");
        System.out.println("12: LILLE | 13: MARSEILLE | 14: MOSCOW | 15: SAINTPETERSBURG | 16: KAZAN | 17: LONDON");
        System.out.println("18: LIVERPOOL | 19: MANCHESTER");
        Integer integer_City = Integer.parseInt(input.nextLine());
        City city = City.values()[integer_City];

        CustomerSpec CS = new CustomerSpec(firstName, lastName, email, nationalID, gender, city, country);
        customersList.addCustomer(address, salary, CS);
        System.out.println("Customer added!");
        return null;
    }

    public static Account AddAccount(){
        System.out.println("Adding new Account...");
        System.out.println("Customer ID:");
        Integer customerId = Integer.parseInt(input.nextLine());
        
        Customer test = new Customer("", 0, null);

        System.out.println("Balance:");
        Float balance = Float.parseFloat(input.nextLine());

        Account newAccount = accountManager.addAccount(balance, test);
        System.out.println("Account added!");

        return newAccount;
    }

    public static void CloseAccount(){
        System.out.println("Closing new Account...");
        System.out.println("Account ID:");
        Integer accountId = Integer.parseInt(input.nextLine());
        
        Account account = accountManager.FindById(accountId);
        accountManager.closeAccount(account);

        System.out.println("Account closed!");

        return;
    }

    public static void Withdraw(){
        System.out.println("Withdraw...");
        System.out.println("Account ID:");
        Integer accountId = Integer.parseInt(input.nextLine());
        
        Account account = accountManager.FindById(accountId);

        System.out.println("Amount:");
        Float amount = Float.parseFloat(input.nextLine());
        boolean isOk = accountManager.withdrawMoney(account, amount);
        if(isOk){
            System.out.println("Done!");
        }else
        {
            System.out.println("Not Enough Money in Account!");
        }

        return;
    }

    public static void Deposit(){
        System.out.println("Deposit...");
        System.out.println("Account ID:");
        Integer accountId = Integer.parseInt(input.nextLine());
        
        Account account = accountManager.FindById(accountId);

        System.out.println("Amount:");
        Float amount = Float.parseFloat(input.nextLine());
        accountManager.depositMoney(account, amount);
        
        System.out.println("Done!");
    }

    public static void AddTransaction(){
        System.out.println("AddTransaction...");
        
        System.out.println("Sender Account ID:");
        Integer SenderAccountId = Integer.parseInt(input.nextLine());
        
        System.out.println("Receiver Account ID:");
        Integer ReceiverAccountId = Integer.parseInt(input.nextLine());
        
        Account SenderAccount = accountManager.FindById(SenderAccountId);
        Account ReveiverAccount = accountManager.FindById(ReceiverAccountId);
        
        System.out.println("Amount:");
        Float amount = Float.parseFloat(input.nextLine());

        transactionsList.addTransaction(SenderAccount, ReveiverAccount, amount);
        
        System.out.println("Transaction Done!");
    }

    
}