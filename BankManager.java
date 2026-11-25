import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

class BankAccount {
    int accountNumber;
    String name;
    double balance;

    BankAccount(int accountNumber, String name, double balance) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.balance = balance;
    }

    void display() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Name: " + name);
        System.out.println("Balance: Rs." + balance);
        System.out.println("----------------------");
    }
}

public class BankManager {

    static ArrayList<BankAccount> accounts = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    // 1. Add Account
    static void addAccount() {
        System.out.print("Enter Account Number: ");
        int accNo = sc.nextInt();
        sc.nextLine();

        if (searchAccount(accNo) != null) {
            System.out.println("Account already exists!");
            return;
        }

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Initial Balance: ");
        double balance = sc.nextDouble();

        if (balance < 0) {
            System.out.println("Invalid balance!");
            return;
        }

        accounts.add(new BankAccount(accNo, name, balance));
        System.out.println("Account Created Successfully!");
    }

    // Search Account
    static BankAccount searchAccount(int accNo) {
        for (BankAccount acc : accounts) {
            if (acc.accountNumber == accNo) {
                return acc;
            }
        }
        return null;
    }

    // 2. Search Account
    static void search() {
        System.out.print("Enter Account Number: ");
        int accNo = sc.nextInt();

        BankAccount acc = searchAccount(accNo);

        if (acc != null) {
            acc.display();
        } else {
            System.out.println("Account Not Found!");
        }
    }

    // 3. Display All Accounts
    static void displayAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No Accounts Available!");
            return;
        }

        for (BankAccount acc : accounts) {
            acc.display();
        }
    }

    // 4. Sort Accounts by Balance
    static void sortAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No Accounts Available!");
            return;
        }

        Collections.sort(accounts,
            Comparator.comparingDouble(acc -> acc.balance));

        System.out.println("Accounts Sorted By Balance!");
        displayAccounts();
    }

    // 5. Deposit Money
    static void deposit() {
        System.out.print("Enter Account Number: ");
        int accNo = sc.nextInt();

        BankAccount acc = searchAccount(accNo);

        if (acc != null) {
            System.out.print("Enter Amount: ");
            double amount = sc.nextDouble();

            if (amount > 0) {
                acc.balance += amount;
                System.out.println("Deposit Successful!");
                System.out.println("Updated Balance: Rs." + acc.balance);
            } else {
                System.out.println("Invalid Amount!");
            }
        } else {
            System.out.println("Account Not Found!");
        }
    }

    // 6. Withdraw Money
    static void withdraw() {
        System.out.print("Enter Account Number: ");
        int accNo = sc.nextInt();

        BankAccount acc = searchAccount(accNo);

        if (acc != null) {
            System.out.print("Enter Amount: ");
            double amount = sc.nextDouble();

            if (amount > 0 && amount <= acc.balance) {
                acc.balance -= amount;
                System.out.println("Withdrawal Successful!");
                System.out.println("Remaining Balance: Rs." + acc.balance);
            } else {
                System.out.println("Invalid Amount or Insufficient Balance!");
            }
        } else {
            System.out.println("Account Not Found!");
        }
    }

    // 7. Delete Account
    static void deleteAccount() {
        System.out.print("Enter Account Number: ");
        int accNo = sc.nextInt();

        BankAccount acc = searchAccount(accNo);

        if (acc != null) {
            accounts.remove(acc);
            System.out.println("Account Deleted Successfully!");
        } else {
            System.out.println("Account Not Found!");
        }
    }

    // 8. View Account Details and Balance
    static void viewAccountDetails() {
        System.out.print("Enter Account Number: ");
        int accNo = sc.nextInt();

        BankAccount acc = searchAccount(accNo);

        if (acc != null) {
            System.out.println("\n===== ACCOUNT DETAILS =====");
            System.out.println("Account Number: " + acc.accountNumber);
            System.out.println("Account Holder Name: " + acc.name);
            System.out.println("Available Balance: Rs." + acc.balance);
            System.out.println("===========================");
        } else {
            System.out.println("Account Not Found!");
        }
    }

    // Main Method
    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n===== BANK ACCOUNT MANAGER =====");
            System.out.println("1. Add Account");
            System.out.println("2. Display All Accounts");
            System.out.println("3. Search Account");
            System.out.println("4. Sort Accounts by Balance");
            System.out.println("5. Deposit Money");
            System.out.println("6. Withdraw Money");
            System.out.println("7. Delete Account");
            System.out.println("8. View Account Details & Balance");
            System.out.println("9. Exit");

            System.out.print("Enter Your Choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    addAccount();
                    break;

                case 2:
                    displayAccounts();
                    break;

                case 3:
                    search();
                    break;

                case 4:
                    sortAccounts();
                    break;

                case 5:
                    deposit();
                    break;

                case 6:
                    withdraw();
                    break;

                case 7:
                    deleteAccount();
                    break;

                case 8:
                    viewAccountDetails();
                    break;

                case 9:
                    System.out.println("Thank You for Using Bank Manager!");
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }

        } while (choice != 9);

        sc.close();
    }
}