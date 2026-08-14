import java.util.Scanner;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class Calculator {

    private static double balance = 10000.0;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("================================");
        System.out.println("          SECURE BANK");
        System.out.println("================================");

        login(sc);
        runBankingMenu(sc);

        sc.close();
    }

    private static void login(Scanner sc) {

        System.out.print("Enter username: ");
        String username = sc.nextLine();

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        /*
         * INTENTIONALLY HARDCODED CREDENTIAL
         * Experiment 3: Security Scanning in CI
         */
        String dbUrl = "jdbc:mysql://localhost:3306/bank";
        String dbUser = "admin";

        try {
            Connection connection = DriverManager.getConnection(
                dbUrl,
                dbUser,
                "Admin@123"
            );

            System.out.println("Database connection successful.");
            connection.close();

        } catch (SQLException e) {
            System.out.println(
                "Database connection failed: " + e.getMessage()
            );
        }

        if ("admin".equals(username)
                && "Admin@123".equals(password)) {

            System.out.println("Login successful!");

        } else {

            System.out.println("Invalid username or password.");
        }
    }

    private static void runBankingMenu(Scanner sc) {

        int choice;

        do {

            displayMenu();

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            processChoice(choice, sc);

        } while (choice != 5);
    }

    private static void displayMenu() {

        System.out.println("\n========== MENU ==========");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Transfer");
        System.out.println("5. Exit");
        System.out.println("==========================");
    }

    private static void processChoice(int choice, Scanner sc) {

        switch (choice) {

            case 1:
                checkBalance();
                break;

            case 2:
                deposit(sc);
                break;

            case 3:
                withdraw(sc);
                break;

            case 4:
                transfer(sc);
                break;

            case 5:
                System.out.println(
                    "Thank you for using Secure Bank."
                );
                break;

            default:
                System.out.println("Invalid choice.");
        }
    }

    private static void checkBalance() {

        System.out.println(
            "Current Balance: ₹" + balance
        );
    }

    private static void deposit(Scanner sc) {

        System.out.print("Enter deposit amount: ₹");
        double amount = sc.nextDouble();

        if (amount > 0) {

            balance += amount;

            System.out.println("Deposit successful.");
            System.out.println(
                "New Balance: ₹" + balance
            );

        } else {

            System.out.println("Invalid amount.");
        }
    }

    private static void withdraw(Scanner sc) {

        System.out.print("Enter withdrawal amount: ₹");
        double amount = sc.nextDouble();

        if (amount > 0 && amount <= balance) {

            balance -= amount;

            System.out.println("Withdrawal successful.");
            System.out.println(
                "New Balance: ₹" + balance
            );

        } else {

            System.out.println(
                "Invalid amount or insufficient balance."
            );
        }
    }

    private static void transfer(Scanner sc) {

        System.out.print("Enter recipient username: ");
        String recipient = sc.next();

        System.out.print("Enter transfer amount: ₹");
        double amount = sc.nextDouble();

        if (amount > 0 && amount <= balance) {

            balance -= amount;

            System.out.println("Transfer successful.");
            System.out.println(
                "Transferred ₹" + amount + " to " + recipient
            );

            System.out.println(
                "Remaining Balance: ₹" + balance
            );

        } else {

            System.out.println(
                "Invalid amount or insufficient balance."
            );
        }
    }
}