package ConsoleApplicationForABankSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Bank {
    private String bankName;
    private List<Account> accounts;
    private double totalTransactionFees;
    private double totalTransferAmount;
    private double flatFeeAmount;
    private double percentFeeValue;

    public Bank(String bankName, double flatFeeAmount, double percentFeeValue) {
        this.bankName = bankName;
        this.accounts = new ArrayList<>();
        this.totalTransactionFees = 0;
        this.totalTransferAmount = 0;
        this.flatFeeAmount = flatFeeAmount;
        this.percentFeeValue = percentFeeValue;
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public Optional<Account> getAccountById(String accountId) {
        return accounts.stream().filter(a -> a.getAccountId().equals(accountId)).findFirst();
    }
    public void performTransaction(Transaction transaction) throws InsufficientFundsException, AccountNotFoundException {
        Account fromAccount = getAccountById(transaction.getFromAccountId())
                .orElseThrow(() -> new AccountNotFoundException("From account not found."));
        Account toAccount = getAccountById(transaction.getToAccountId())
                .orElseThrow(() -> new AccountNotFoundException("To account not found."));
    
        if (fromAccount.getAccountId().equals(toAccount.getAccountId())) {
            throw new IllegalArgumentException("Cannot transfer money within the same account.");
        }
    
        double fee = transaction.calculateFee();
        fromAccount.withdraw(transaction.getAmount() + fee);
        toAccount.deposit(transaction.getAmount());
    
        totalTransactionFees += fee;
        totalTransferAmount += transaction.getAmount();
        fromAccount.getTransactions().add(transaction);
        toAccount.getTransactions().add(transaction);
    }    

    public List<Account> getAccounts() {
        return accounts;
    }

    public double getTotalTransactionFees() {
        return totalTransactionFees;
    }

    public double getTotalTransferAmount() {
        return totalTransferAmount;
    }

    public double getFlatFeeAmount() {
        return flatFeeAmount;
    }

    public double getPercentFeeValue() {
        return percentFeeValue;
    }
}
