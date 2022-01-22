import java.util.Scanner;

import Account.Account;
import Customer.Customer;

public class Main {
    public static Scanner input = new Scanner(System.in);

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
                "9. Add transaction";
                // and some other functionalities
        System.out.println(menu);
        while(!isQuit){
            int command = Integer.parseInt(input.nextLine());
            exec(command);
        }
    }

    public static void exec(int command){

        switch(command) {
            case 1: //add customer
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
              // code block
              break;
            case 6:
              // code block
              break;
            case 7:
              // code block
              break;
            case 8:
              // code block
              break;
            case 9:
              // code block
              break;
            default:
                System.out.println("undefined command! try again");

       }
    }

    public static Customer AddCustomer(){
        System.out.println("Adding new costumer, please enter costumer informations: ");
        System.out.println("Address: ");
        String address = input.nextLine();
        System.out.println("Salary: ");
        Float salary = Float.parseFloat(input.nextLine());
        //todo: create customer
        System.out.println("Customer added!");
        return null;
    }
}