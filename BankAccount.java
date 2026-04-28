package bankaccount;

public class BankAccount {

    private double balance;

    // Constructor
    public BankAccount(double initialBalance) {
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative");
        }
        this.balance = initialBalance;
    }

    // Method to deposit an amount
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be greater than zero");
        }
        balance += amount;
    }

    // Method to withdraw an amount
    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdraw amount must be greater than zero");
        }
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        balance -= amount;
    }

    // Method to get the current balance
    public double getBalance() {
        return balance;
    }

    // ----- Part 1c: Method to transfer money to another account (no fees) -----
    public void transfer(BankAccount other, double amount) {
        if (other == null) {
            throw new IllegalArgumentException("Destination account cannot be null");
        }
        if (other == this) {
            throw new IllegalArgumentException("Cannot transfer to the same account");
        }
        // The withdraw and deposit calls handle the rest of the validation
        // (positive amount, sufficient funds). If withdraw throws, deposit
        // never runs, so no money is lost.
        this.withdraw(amount);
        other.deposit(amount);
    }
}
