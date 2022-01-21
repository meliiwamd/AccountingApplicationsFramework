import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
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
        int command = Integer.parseInt(input.nextLine());
    }
}