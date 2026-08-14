import java.util.Scanner;

public class Calculator {

    private static double balance = 10000.0;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("================================");
        System.out.println("        SECURE BANK");
        System.out.println("================================");

        System.out.print("Enter username: ");
        String username = sc.nextLine();

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        // Temporary login for the application
        if (!username.equals("admin") || !password.equals("Admin@123")) {
            System.out.println("Invalid username or password.");
            sc.close();
            return;
        }

        System.out.println("\nLogin successful!");

        int choice;

        do {
            System.out.println("\n========== MENU ==========");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Exit");
            System.out.println("==========================");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.println("Current Balance: ₹" + balance);
                    break;

                case 2:
                    System.out.print("Enter deposit amount: ₹");
                    double deposit = sc.nextDouble();

                    if (deposit > 0) {
                        balance += deposit;
                        System.out.println("Deposit successful.");
                        System.out.println("New Balance: ₹" + balance);
                    } else {
                        System.out.println("Invalid amount.");
                    }
                    break;

                case 3:
                    System.out.print("Enter withdrawal amount: ₹");
                    double withdraw = sc.nextDouble();

                    if (withdraw > 0 && withdraw <= balance) {
                        balance -= withdraw;
                        System.out.println("Withdrawal successful.");
                        System.out.println("New Balance: ₹" + balance);
                    } else {
                        System.out.println("Invalid amount or insufficient balance.");
                    }
                    break;

                case 4:
                    System.out.print("Enter recipient username: ");
                    String recipient = sc.next();

                    System.out.print("Enter transfer amount: ₹");
                    double transfer = sc.nextDouble();

                    if (transfer > 0 && transfer <= balance) {
                        balance -= transfer;

                        System.out.println("Transfer successful.");
                        System.out.println("Transferred ₹" + transfer +
                                " to " + recipient);
                        System.out.println("Remaining Balance: ₹" + balance);
                    } else {
                        System.out.println("Invalid amount or insufficient balance.");
                    }
                    break;

                case 5:
                    System.out.println("Thank you for using Secure Bank.");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 5);

        sc.close();
    }
}