import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
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
                "2. Filter customers" + "\n" +
                "3. Edit customer" + "\n" +
                "4. Get all accounts of customer" + "\n" +
                "5. Add an account" + "\n" +
                "6. Close an account" + "\n" +
                "7. Withdraw money" + "\n" +
                "8. Deposit money" + "\n" +
                "9. Add transaction" + "\n" +
                "10. Filter transaction" + "\n" +
                "0. Exit" + "\n" + 
                "-1. Show Informations";

        while(!isQuit){
            System.out.println(menu);
            
                int command = Integer.parseInt(input.nextLine());
                if (command == 0){
                    break;
                }
                exec(command);
                System.out.println("************************************************");
             
                // System.out.println("Enter a valid command!");

            
            

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
                FilterCustomers();
                break;
            case 3:
                EditCustomer();
                break;
            case 4:
                GetAllAccountsOfCustomer();
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
            case 10:
                FilterTransaction();
                break;
            default:
                System.out.println("undefined command! try again");

       }
    }

    public static void ShowInformation(){
        System.out.println("Showing informations...");
        System.out.println("Customers:");
        for (Customer customer : customersList.customers) {
            System.out.println("Customer ID: " + customer.getCustomerId());
            System.out.println("Address : " + customer.getAddress());
            System.out.println("Salary : " + customer.getSalary());
            
            HashMap<String, String> props = customer.getSpec().getProperties();
            for (String key : props.keySet()) {
                System.out.println(key + ": " + props.get(key));
            }
            System.out.println("###########");
        }
        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
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
        System.out.println("0: NEWYORK | 1: LOSANGELES | 2: WASHINGTON | 3: TORONTO | 4: MONTREAL | 5: TEHRAN");
        System.out.println("6: MASHHAD | 7: ISFAHAN | 8: PARIS | 9: MOSCOW | 10: SAINTPETERSBURG | 11: LONDON");
        System.out.println("12: MANCHESTER ");
        
        Integer integer_City = Integer.parseInt(input.nextLine());
        City city = City.values()[integer_City];

        CustomerSpec CS = new CustomerSpec(firstName, lastName, email, nationalID, gender, city, country);
        customersList.addCustomer(address, salary, CS);
        System.out.println("Customer added!");
        return null;
    }

    public static void FilterCustomers(){
        System.out.println("Filtering Customers...");
        System.out.println("FirstName: ");
        String firstName = input.nextLine();
        if(firstName == ""){firstName = null;}
        System.out.println("LastName: ");
        String lastName = input.nextLine();
        if(lastName == ""){lastName = null;}

        System.out.println("Email: ");
        String email = input.nextLine();
        if(email == ""){email = null;}

        System.out.println("NationalId: ");
        String nationalId = input.nextLine();
        if(nationalId == ""){nationalId = null;}

        System.out.println("Gender: ");
        System.out.println("0: Male | 1: Female ");
        Gender gender = null;
        try {
            Integer integer_gender = Integer.parseInt(input.nextLine());
            gender = Gender.values()[integer_gender];
        } catch (Exception e) {
            
        }
        
        System.out.println("Country: ");
        System.out.println("0: USA | 1: FRANCE | 2: ENGLAND | 3: IRAN | 4: RUSSIA | 5: CANADA");
        Country country = null;
        try {
            Integer integer_County = Integer.parseInt(input.nextLine());
            country = Country.values()[integer_County];
        } catch (Exception e) {
        }
        

        System.out.println("City: ");
        System.out.println("0: NEWYORK | 1: LOSANGELES | 2: WASHINGTON | 3: TORONTO | 4: MONTREAL | 5: TEHRAN");
        System.out.println("6: MASHHAD | 7: ISFAHAN | 8: PARIS | 9: MOSCOW | 10: SAINTPETERSBURG | 11: LONDON");
        System.out.println("12: MANCHESTER ");
        City city = null;
        try {
            Integer integer_City = Integer.parseInt(input.nextLine());
            city = City.values()[integer_City];
        } catch (Exception e) {
        }
        
        CustomerSpec searchSpec = new CustomerSpec(firstName, lastName, email, nationalId, gender,city, country);
        List<Customer> Results = customersList.search(searchSpec);

        System.out.println("Results :"); 
        for (Customer customer : Results) {
            System.out.println("Customer ID: " + customer.getCustomerId());
            HashMap<String, String> props = customer.getSpec().getProperties();
            for (String key : props.keySet()) {
                System.out.println(key + ": " + props.get(key));
            }
            System.out.println("###########");
        }

        return;
    }

    public static void EditCustomer(){
        System.out.println("Edit Customer...");
        System.out.println("Customer ID: ");
        Integer customerId = Integer.parseInt(input.nextLine());
        Customer customer = customersList.FindById(customerId);

        System.out.println("FirstName: ");
        String firstName = input.nextLine();
        if(firstName == ""){firstName = null;}

        System.out.println("LastName: ");
        String lastName = input.nextLine();
        if(lastName == ""){lastName = null;}

        System.out.println("Email: ");
        String email = input.nextLine();
        if(email == ""){email = null;}

        System.out.println("NationalId: ");
        String nationalId = input.nextLine();
        if(nationalId == ""){nationalId = null;}

        System.out.println("Gender: ");
        System.out.println("0: Male | 1: Female ");
        Gender gender = null;
        try {
            Integer integer_gender = Integer.parseInt(input.nextLine());
            gender = Gender.values()[integer_gender];
        } catch (Exception e) {}

        System.out.println("Country: ");
        System.out.println("0: USA | 1: FRANCE | 2: ENGLAND | 3: IRAN | 4: RUSSIA | 5: CANADA");
        Country country = null;
        try {
            Integer integer_County = Integer.parseInt(input.nextLine());
            country = Country.values()[integer_County];
        } catch (Exception e) {
        }

        System.out.println("City: ");
        System.out.println("0: NEWYORK | 1: LOSANGELES | 2: WASHINGTON | 3: TORONTO | 4: MONTREAL | 5: TEHRAN");
        System.out.println("6: MASHHAD | 7: ISFAHAN | 8: PARIS | 9: MOSCOW | 10: SAINTPETERSBURG | 11: LONDON");
        System.out.println("12: MANCHESTER ");
        City city = null;
        try {
            Integer integer_City = Integer.parseInt(input.nextLine());
            city = City.values()[integer_City];
        } catch (Exception e) {
        }
        HashMap<String, String> properties = new HashMap<>();
        properties.put("firstName", firstName);
        properties.put("lastName", lastName);
        properties.put("email", email);
        properties.put("nationalId", nationalId);
        properties.put("gender", gender != null ? gender.toString() : null);
        properties.put("city", city != null ? city.toString() : null);
        properties.put("country", country != null ? country.toString() : null);

        customersList.editCustomer(customer, properties);
        System.out.println("Edited!");

        return;
    }

    public static void GetAllAccountsOfCustomer(){
        System.out.println("Get all accounts of Customer...");
        System.out.println("Customer ID: ");
        Integer customerId = Integer.parseInt(input.nextLine());
        Customer customer = customersList.FindById(customerId);

        List<Account> Results = accountManager.GetAllAccountsOfCustomer(customer);
        System.out.println("Results:");

        for (Account account : Results) {
            System.out.println("Account Id: " + account.getAccountId());
            System.out.println("Balance: " + account.getBalance());
            System.out.println("###########");
        }
    }

    public static Account AddAccount(){
        System.out.println("Adding new Account...");
        System.out.println("Customer ID:");
        Integer customerId = Integer.parseInt(input.nextLine());
        Customer customer = customersList.FindById(customerId);

        System.out.println("Balance:");
        Float balance = Float.parseFloat(input.nextLine());

        Account newAccount = accountManager.addAccount(balance, customer);
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

    public static void FilterTransaction(){
        System.out.println("Filter Transactions...");

        System.out.println("Start DateTime: (example: 31-10-1998 23:37");
        String startDateTime_string = input.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime startDateTime = LocalDateTime.parse(startDateTime_string, formatter);

        System.out.println("End DateTime: (example: 31-10-1999 23:37");
        String EndDateTime_string = input.nextLine();
        LocalDateTime endDateTime = LocalDateTime.parse(EndDateTime_string, formatter);

        List<Transaction> results = transactionsList.search(startDateTime, endDateTime);

        System.out.println("results:");
        for (Transaction transaction : results) {
            System.out.println("Transaction Id: " + transaction.getTransactionId());
            System.out.println("Sender Id: " + transaction.getSender().getAccountId());
            System.out.println("Receiver Id: " + transaction.getReceiver().getAccountId());
            System.out.println("Amount: " + transaction.getAmount());
            System.out.println("DateTime: " + transaction.getDateOfTransaction());
            System.out.println("###########");
        }
    }

    
}