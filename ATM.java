import java.util.ArrayList;
import java.util.Scanner;

public class ATM {
    private String userId;
    private int pin;
    private double balance;
    private ArrayList<Transaction> transactionHistory = new ArrayList<>();

    public ATM(String userId, int pin, double initialBalance) {
        this.userId = userId;
        this.pin = pin;
        this.balance = initialBalance;
    }

    public void displayTransactionHistory() {
        System.out.println("Transaction History for User: " + userId);
        System.out.println("----------------------------------");
        for (Transaction transaction : transactionHistory) {
            System.out.println(transaction.toString());
        }
    }

    public void makeWithdrawal(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add(new Transaction("Withdrawal", amount));
            System.out.println("Withdrawal of $" + amount + " successful.");
        } else {
            System.out.println("Invalid withdrawal amount or insufficient balance.");
        }
    }

    public void makeDeposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add(new Transaction("Deposit", amount));
            System.out.println("Deposit of $" + amount + " successful.");
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void makeTransfer(String recipientUserId, double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add(new Transaction("Transfer to " + recipientUserId, amount));
            System.out.println("Transfer of $" + amount + " to " + recipientUserId + " successful.");
        } else {
            System.out.println("Invalid transfer amount or insufficient balance.");
        }
    }

    public void displayBalance() {
        System.out.println("Account Balance: $" + balance);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter PIN: ");
        int pin = scanner.nextInt();
        ATM atm = new ATM(userId, pin, 1000.0); // Initial balance of $1000

        while (true) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Display Balance");
            System.out.println("2. Display Transaction History");
            System.out.println("3. Make a Withdrawal");
            System.out.println("4. Make a Deposit");
            System.out.println("5. Make a Transfer");
            System.out.println("6. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    atm.displayBalance();
                    break;
                case 2:
                    atm.displayTransactionHistory();
                    break;
                case 3:
                    System.out.print("Enter withdrawal amount: $");
                    double withdrawalAmount = scanner.nextDouble();
                    atm.makeWithdrawal(withdrawalAmount);
                    break;
                case 4:
                    System.out.print("Enter deposit amount: $");
                    double depositAmount = scanner.nextDouble();
                    atm.makeDeposit(depositAmount);
                    break;
                case 5:
                    System.out.print("Enter recipient's User ID: ");
                    String recipientUserId = scanner.next();
                    System.out.print("Enter transfer amount: $");
                    double transferAmount = scanner.nextDouble();
                    atm.makeTransfer(recipientUserId, transferAmount);
                    break;
                case 6:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Define a Transaction class to store transaction details
    private class Transaction {
        private String description;
        private double amount;

        public Transaction(String description, double amount) {
            this.description = description;
            this.amount = amount;
        }

        @Override
        public String toString() {
            return description + ": $" + amount;
        }
    }
}
