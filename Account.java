package ConsoleApplicationForABankSystem;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private static int accountIdCounter = 1;
    private String accountId;
    private String userName;
    private double balance;
    private List<Transaction> transactions;

    public Account(String userName, double initialBalance) {
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative.");
        }
        this.accountId = "ACC" + accountIdCounter++;
        this.userName = userName;
        this.balance = initialBalance;
        this.transactions = new ArrayList<>();
    }

    public String getAccountId() {
        return accountId;
    }

    public String getUserName() {
        return userName;
    }

    public double getBalance() {
        return balance;
    }

    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount > 0 && this.balance >= amount) {
            double fee = 10.0;
            this.balance -= (amount + fee);
            transactions.add(new Transaction(amount, accountId, null, "Withdrawal") {
                @Override
                public double calculateFee() {
                    return fee;
                }
            });
        } else {
            throw new InsufficientFundsException("Not enough funds for withdrawal.");
        }
    }
    
    public void deposit(double amount) {
        if (amount > 0) {
            double fee = 10.0;
            this.balance += (amount - fee);
            transactions.add(new Transaction(amount, null, accountId, "Deposit") {
                @Override
                public double calculateFee() {
                    return fee;
                }
            });
        }
    }      
    
    public void transfer(double amount, Account toAccount, double fee) throws InsufficientFundsException {
        double totalAmount = amount + fee;
        if (totalAmount > balance) {
            throw new InsufficientFundsException("Not enough funds");
        }
        balance -= totalAmount;
        toAccount.balance += amount;
        transactions.add(new Transaction(amount, accountId, toAccount.getAccountId(), "Transfer") {
            @Override
            public double calculateFee() {
                return fee;
            }
        });
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void displayAccountInfo() {
        System.out.printf("Account ID: %s, User Name: %s, Balance: %.2f%n", accountId, userName, balance);
    }
}
