import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private static Scanner input = new Scanner(System.in);
    private static CustomersList customersList = new CustomersList();
    private static AccountManager accountManager = new AccountManager();
    private static TransactionsList transactionsList = new TransactionsList();
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public static void main(String[] args) {
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
                "11. Show balance" + "\n" +
                "0. Exit" + "\n" +
                "-1. Show Information";

        while (true) {
            System.out.println(menu);

            try {
                int command = Integer.parseInt(input.nextLine());
                if (command == 0)
                    break;
                exec(command);
                System.out.println("************************************************");
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid command!");
            }
        }
    }

    private static void exec(int command) {
        switch (command) {
            case -1:
                showInformation();
                break;
            case 1:
                addCustomer();
                break;
            case 2:
                filterCustomers();
                break;
            case 3:
                editCustomer();
                break;
            case 4:
                getAllAccountsOfCustomer();
                break;
            case 5:
                addAccount();
                break;
            case 6:
                closeAccount();
                break;
            case 7:
                withdrawMoney();
                break;
            case 8:
                depositMoney();
                break;
            case 9:
                addTransaction();
                break;
            case 10:
                filterTransactions();
                break;
            case 11:
                showBalance();
                break;
            default:
                System.out.println("Undefined command! try again");
                break;
        }
    }

    private static HashMap<String, String> getNonEnumerateFieldsForEditOrFilterCustomer() {
        HashMap<String, String> results = new HashMap<>();

        System.out.println("FirstName: ");
        String firstName = input.nextLine();
        if (firstName.equals(""))
            firstName = null;

        System.out.println("LastName: ");
        String lastName = input.nextLine();
        if (lastName.equals(""))
            lastName = null;

        System.out.println("Email: ");
        String email = input.nextLine();
        if (email.equals(""))
            email = null;

        System.out.println("NationalId: ");
        String nationalId = input.nextLine();
        if (nationalId.equals(""))
            nationalId = null;

        results.put("firstName", firstName);
        results.put("lastName", lastName);
        results.put("email", email);
        results.put("nationalId", nationalId);

        return results;
    }

    private static void showTransactionInfo(List<Transaction> transactions) {
        System.out.println("\nTransactions:");
        for (Transaction transaction : transactions) {
            System.out.println("Transaction Id: " + transaction.getTransactionId());
            System.out.println("Sender Id: " + transaction.getSender().getAccountId());
            System.out.println("Receiver Id: " + transaction.getReceiver().getAccountId());
            System.out.println("Amount: " + transaction.getAmount());
            System.out.println("DateTime: " + transaction.getDateOfTransaction().format(formatter));
            System.out.println("###########");
        }
    }

    private static void showInformation() {
        System.out.println("Showing information...");
        System.out.println("\nCustomers:");

        for (Customer customer : customersList.customers) {
            System.out.println("Customer ID: " + customer.getCustomerId());
            System.out.println("Address: " + customer.getAddress());
            System.out.println("Salary: " + customer.getSalary());

            HashMap<String, String> props = customer.getSpec().getProperties();
            for (String key : props.keySet())
                System.out.println(key + ": " + props.get(key));
            System.out.println("###########");
        }

        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        System.out.println("\nAccounts:");
        for (Account account : accountManager.Accounts) {
            System.out.println("Account Id: " + account.getAccountId());
            System.out.println("Customer Id: " + account.getCustomer().getCustomerId());
            System.out.println("Balance: " + account.getBalance());
            System.out.println("Date Created: " + account.getDateCreated().format(formatter));
            System.out.println("###########");
        }
        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");

        showTransactionInfo(transactionsList.transactions);
        System.out.println("Finished!");
    }

    private static void addCustomer() {
        System.out.println("Adding new customer...");

        System.out.println("FirstName: ");
        String firstName = input.nextLine();

        System.out.println("LastName: ");
        String lastName = input.nextLine();

        System.out.println("National ID: ");
        String nationalID = input.nextLine();
        Pattern pattern = Pattern.compile("^\\d{10}$");
        Matcher matcher = pattern.matcher(nationalID);
        while (!matcher.matches()) {
            System.out.println("Invalid nationalId format! (must be 10-digit number) Please try again!");
            nationalID = input.nextLine();
            matcher = pattern.matcher(nationalID);
        }

        System.out.println("Email: ");
        String email = input.nextLine();
        pattern = Pattern.compile("^(.+)@(.+)$");
        matcher = pattern.matcher(email);
        while (!matcher.matches()) {
            System.out.println("Invalid email format! Please try again!");
            email = input.nextLine();
            matcher = pattern.matcher(email);
        }

        System.out.println("Address: ");
        String address = input.nextLine();

        System.out.println("Salary: ");
        float salary = Float.parseFloat(input.nextLine());

        System.out.println("Gender: ");
        System.out.println("0: Male | 1: Female ");
        Gender gender = null;

        while (gender == null) {
            try {
                int genderInteger = Integer.parseInt(input.nextLine());
                gender = Gender.values()[genderInteger];
            } catch (Exception e) {
                System.out.println("Invalid gender! Please try again!");
                System.out.println("Gender: ");
                System.out.println("0: Male | 1: Female ");
            }
        }

        System.out.println("Country: ");
        System.out.println("0: USA | 1: FRANCE | 2: ENGLAND | 3: IRAN | 4: RUSSIA | 5: CANADA");
        Country country = null;

        while (country == null) {
            try {
                int countryInteger = Integer.parseInt(input.nextLine());
                country = Country.values()[countryInteger];
            } catch (Exception e) {
                System.out.println("Invalid country! Please try again!");
                System.out.println("Country: ");
                System.out.println("0: USA | 1: FRANCE | 2: ENGLAND | 3: IRAN | 4: RUSSIA | 5: CANADA");
            }
        }

        System.out.println("City: ");
        System.out.println("0: NEWYORK | 1: LOSANGELES | 2: WASHINGTON | 3: TORONTO | 4: MONTREAL | 5: TEHRAN");
        System.out.println("6: MASHHAD | 7: ISFAHAN | 8: PARIS | 9: MOSCOW | 10: SAINTPETERSBURG | 11: LONDON");
        System.out.println("12: MANCHESTER ");
        City city = null;

        while (city == null) {
            try {
                int cityInteger = Integer.parseInt(input.nextLine());
                city = City.values()[cityInteger];
            } catch (Exception e) {
                System.out.println("Invalid city! Please try again!");
                System.out.println("City: ");
                System.out.println("0: NEWYORK | 1: LOSANGELES | 2: WASHINGTON | 3: TORONTO | 4: MONTREAL | 5: TEHRAN");
                System.out.println("6: MASHHAD | 7: ISFAHAN | 8: PARIS | 9: MOSCOW | 10: SAINTPETERSBURG | 11: LONDON");
                System.out.println("12: MANCHESTER ");
            }
        }

        CustomerSpec CS = new CustomerSpec(firstName, lastName, email, nationalID, gender, city, country);
        customersList.addCustomer(address, salary, CS);
        System.out.println("Customer successfully added!");
    }

    private static void filterCustomers() {
        System.out.println("Filtering Customers...");
        HashMap<String, String> nonEnumerateFields = getNonEnumerateFieldsForEditOrFilterCustomer();

        System.out.println("Gender: ");
        System.out.println("0: Male | 1: Female ");
        Gender gender = null;
        try {
            int integer_gender = Integer.parseInt(input.nextLine());
            gender = Gender.values()[integer_gender];
        } catch (Exception ignored) {
        }

        System.out.println("Country: ");
        System.out.println("0: USA | 1: FRANCE | 2: ENGLAND | 3: IRAN | 4: RUSSIA | 5: CANADA");
        Country country = null;
        try {
            int integer_County = Integer.parseInt(input.nextLine());
            country = Country.values()[integer_County];
        } catch (Exception ignored) {
        }

        System.out.println("City: ");
        System.out.println("0: NEWYORK | 1: LOSANGELES | 2: WASHINGTON | 3: TORONTO | 4: MONTREAL | 5: TEHRAN");
        System.out.println("6: MASHHAD | 7: ISFAHAN | 8: PARIS | 9: MOSCOW | 10: SAINTPETERSBURG | 11: LONDON");
        System.out.println("12: MANCHESTER ");
        City city = null;
        try {
            int integer_City = Integer.parseInt(input.nextLine());
            city = City.values()[integer_City];
        } catch (Exception ignored) {
        }

        CustomerSpec searchSpec = new CustomerSpec(
                nonEnumerateFields.get("firstName"),
                nonEnumerateFields.get("lastName"),
                nonEnumerateFields.get("email"),
                nonEnumerateFields.get("nationalId"),
                gender, city, country
        );
        List<Customer> Results = customersList.search(searchSpec);

        System.out.println("Results:");
        for (Customer customer : Results) {
            System.out.println("Customer ID: " + customer.getCustomerId());

            HashMap<String, String> props = customer.getSpec().getProperties();
            for (String key : props.keySet())
                System.out.println(key + ": " + props.get(key));
            System.out.println("###########");
        }
    }

    private static void editCustomer() {
        System.out.println("Edit Customer...");
        System.out.println("Customer ID: ");
        int customerId = Integer.parseInt(input.nextLine());

        try {
            Customer customer = customersList.FindById(customerId);
            HashMap<String, String> nonEnumerateFields = getNonEnumerateFieldsForEditOrFilterCustomer();

            System.out.println("Gender: ");
            System.out.println("0: Male | 1: Female ");
            Gender gender = null;
            try {
                int integer_gender = Integer.parseInt(input.nextLine());
                gender = Gender.values()[integer_gender];
            } catch (Exception ignored) {
            }

            System.out.println("Country: ");
            System.out.println("0: USA | 1: FRANCE | 2: ENGLAND | 3: IRAN | 4: RUSSIA | 5: CANADA");
            Country country = null;
            try {
                int integer_County = Integer.parseInt(input.nextLine());
                country = Country.values()[integer_County];
            } catch (Exception ignored) {
            }

            System.out.println("City: ");
            System.out.println("0: NEWYORK | 1: LOSANGELES | 2: WASHINGTON | 3: TORONTO | 4: MONTREAL | 5: TEHRAN");
            System.out.println("6: MASHHAD | 7: ISFAHAN | 8: PARIS | 9: MOSCOW | 10: SAINTPETERSBURG | 11: LONDON");
            System.out.println("12: MANCHESTER ");
            City city = null;
            try {
                int integer_City = Integer.parseInt(input.nextLine());
                city = City.values()[integer_City];
            } catch (Exception ignored) {
            }

            HashMap<String, String> properties = new HashMap<>();
            properties.put("firstName", nonEnumerateFields.get("firstName"));
            properties.put("lastName", nonEnumerateFields.get("lastName"));
            properties.put("email", nonEnumerateFields.get("email"));
            properties.put("nationalId", nonEnumerateFields.get("nationalId"));
            properties.put("gender", gender != null ? gender.toString() : null);
            properties.put("city", city != null ? city.toString() : null);
            properties.put("country", country != null ? country.toString() : null);

            customersList.editCustomer(customer, properties);
            System.out.println("Edit customer successfully done!");
        } catch (NullPointerException e) {
            System.out.println("Customer with this ID doesn't exist!");
        }
    }

    private static void getAllAccountsOfCustomer() {
        System.out.println("Get all accounts of Customer...");
        System.out.println("Customer ID: ");
        int customerId = -1;

        while (customerId == -1) {
            try {
                customerId = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid customer ID! Please try again!");
            }
        }

        try {
            Customer customer = customersList.FindById(customerId);

            List<Account> Results = accountManager.GetAllAccountsOfCustomer(customer);
            System.out.println("\nResults:");

            for (Account account : Results) {
                System.out.println("Account Id: " + account.getAccountId());
                System.out.println("Balance: " + account.getBalance());
                System.out.println("###########");
            }
        } catch (NullPointerException e) {
            System.out.println("Customer with this ID doesn't exist!");
        }
    }

    private static void addAccount() {
        System.out.println("Adding new Account...");
        System.out.println("Customer ID:");
        int customerId = -1;

        while (customerId == -1) {
            try {
                customerId = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid customer ID! Please try again!");
            }
        }

        try {
            Customer customer = customersList.FindById(customerId);

            System.out.println("Balance:");
            float balance = Float.parseFloat(input.nextLine());

            accountManager.addAccount(balance, customer);
            System.out.println("Account added!");
        } catch (NullPointerException e) {
            System.out.println("Customer with this ID doesn't exist!");
        }
    }

    private static void closeAccount() {
        System.out.println("Closing new Account...");
        System.out.println("Account ID:");
        int accountId = -1;

        while (accountId == -1) {
            try {
                accountId = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid account ID! Please try again!");
            }
        }

        try {
            Account account = accountManager.FindById(accountId);
            accountManager.closeAccount(account);
            System.out.println("Account successfully closed!");
        } catch (NullPointerException e) {
            System.out.println("Account with this ID doesn't exist!");
        }
    }

    private static void withdrawMoney() {
        System.out.println("Withdraw Money...");
        System.out.println("Account ID:");
        int accountId = -1;

        while (accountId == -1) {
            try {
                accountId = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid account ID! Please try again!");
            }
        }

        try {
            Account account = accountManager.FindById(accountId);

            System.out.println("Amount:");
            float amount = Float.parseFloat(input.nextLine());
            if (accountManager.withdrawMoney(account, amount))
                System.out.println("Done!");
            else
                System.out.println("Not Enough Money in Account!");
        } catch (NullPointerException e) {
            System.out.println("Account with this ID doesn't exist!");
        }
    }

    private static void depositMoney() {
        System.out.println("deposit Money...");
        System.out.println("Account ID:");
        int accountId = -1;

        while (accountId == -1) {
            try {
                accountId = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid account ID! Please try again!");
            }
        }

        try {
            Account account = accountManager.FindById(accountId);

            System.out.println("Amount:");
            float amount = Float.parseFloat(input.nextLine());
            accountManager.depositMoney(account, amount);
            System.out.println("Done!");
        } catch (NullPointerException e) {
            System.out.println("Account with this ID doesn't exist!");
        }
    }

    private static void addTransaction() {
        System.out.println("Add Transaction...");

        System.out.println("Sender Account ID:");
        int senderAccountId = -1;

        while (senderAccountId == -1) {
            try {
                senderAccountId = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid account ID! Please try again!");
            }
        }
        System.out.println("Receiver Account ID:");
        int receiverAccountId = -1;

        while (receiverAccountId == -1) {
            try {
                receiverAccountId = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid account ID! Please try again!");
            }
        }

        try {
            Account senderAccount = accountManager.FindById(senderAccountId);
            try {
                Account receiverAccount = accountManager.FindById(receiverAccountId);

                System.out.println("Amount:");
                float amount = Float.parseFloat(input.nextLine());

                transactionsList.addTransaction(senderAccount, receiverAccount, amount);
                System.out.println("Transaction successfully done!");
            } catch (NullPointerException e) {
                System.out.println("Account with ID " + receiverAccountId + " doesn't exist!");
            }
        } catch (NullPointerException e) {
            System.out.println("Account with ID " + senderAccountId + " doesn't exist!");
        }
    }

    private static void filterTransactions() {
        System.out.println("Filter Transactions...");

        System.out.println("Start DateTime: (Correct format: 31-10-1998 23:37");
        LocalDateTime startDateTime = null;
        while (startDateTime == null) {
            try {
                String startDateTimeStr = input.nextLine();
                startDateTime = LocalDateTime.parse(startDateTimeStr, formatter);
            } catch (Exception e) {
                System.out.println("Invalid datetime format! Please try again!");
            }
        }

        System.out.println("End DateTime: (Correct format: 31-10-1998 23:37");
        LocalDateTime endDateTime = null;
        while (endDateTime == null) {
            try {
                String endDateTimeStr = input.nextLine();
                endDateTime = LocalDateTime.parse(endDateTimeStr, formatter);
            } catch (Exception e) {
                System.out.println("Invalid datetime format! Please try again!");
            }
        }

        if (!startDateTime.isBefore(endDateTime)) {
            System.out.println("Start datetime can't be larger than end datetime!");
            return;
        }

        List<Transaction> results = transactionsList.search(startDateTime, endDateTime);

        System.out.println("Results:");
        showTransactionInfo(results);
    }

    private static void showBalance() {
        System.out.println("Show balance...");

        System.out.println("Account ID:");
        int accountId = -1;

        while (accountId == -1) {
            try {
                accountId = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid account ID! Please try again!");
            }
        }

        try {
            Account account = accountManager.FindById(accountId);
            System.out.println("Balance: " + account.getBalance());
        } catch (NullPointerException e) {
            System.out.println("Account with this ID doesn't exist!");
        }
    }
}
