package ConsoleApplicationForABankSystem;

import java.util.Scanner;

public class Main {
    private static Bank bank;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter bank name:");
        String bankName = scanner.nextLine();
        System.out.println("Enter flat fee amount:");
        double flatFeeAmount = scanner.nextDouble();
        System.out.println("Enter percent fee value:");
        double percentFeeValue = scanner.nextDouble();
        bank = new Bank(bankName, flatFeeAmount, percentFeeValue);

        boolean continueOperating = true;
        while (continueOperating) {
            System.out.println("\n--- Bank System Menu ---");
            System.out.println("1. Create account");
            System.out.println("2. Perform transaction");
            System.out.println("3. Withdraw money");
            System.out.println("4. Deposit money");
            System.out.println("5. Check account balance");
            System.out.println("6. List accounts");
            System.out.println("7. Check total transaction fees");
            System.out.println("8. Check total transfer amount");
            System.out.println("9. List account transactions");
            System.out.println("0. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (choice) {
                    case 1:
                        createAccount(scanner);
                        break;
                    case 2:
                        performTransaction(scanner);
                        break;
                    case 3:
                        withdrawMoney(scanner);
                        break;
                    case 4:
                        depositMoney(scanner);
                        break;
                    case 5:
                        checkBalance(scanner);
                        break;
                    case 6:
                        listAccounts();
                        break;
                    case 7:
                        checkTotalTransactionFees();
                        break;
                    case 8:
                        checkTotalTransferAmount();
                        break;
                    case 9:
                        listAccountTransactions(scanner);
                        break;
                    case 0:
                        System.out.println("Exiting the application.");
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice, please try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

            System.out.println("Do you want to continue operating with the bank system? (yes/no)");
            String input = scanner.nextLine().toLowerCase();
            continueOperating = input.equals("yes");
        }

        System.out.println("Thank you for using the Demhasaj Bank System! We are exiting the application.");
    }

    private static void createAccount(Scanner scanner) {
        System.out.println("Enter user name:");
        String userName = scanner.nextLine();
        System.out.println("Enter initial balance:");
        double initialBalance = scanner.nextDouble();
        scanner.nextLine();

        Account account = new Account(userName, initialBalance);
        bank.addAccount(account);
        System.out.println("Account created successfully. Account ID: " + account.getAccountId());
    }

    private static void performTransaction(Scanner scanner) throws AccountNotFoundException, InsufficientFundsException {
        System.out.println("Enter from account ID:");
        String fromAccountId = scanner.nextLine();
        System.out.println("Enter to account ID:");
        String toAccountId = scanner.nextLine();
        System.out.println("Enter amount:");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Enter transaction reason:");
        String reason = scanner.nextLine();
        System.out.println("Enter transaction type (1 for flat fee, 2 for percent fee):");
        int type = scanner.nextInt();
        scanner.nextLine();

        Transaction transaction;
        if (type == 1) {
            transaction = new FlatFeeTransaction(amount, fromAccountId, toAccountId, reason, bank.getFlatFeeAmount());
        } else if (type == 2) {
            transaction = new PercentFeeTransaction(amount, fromAccountId, toAccountId, reason, bank.getPercentFeeValue());
        } else {
            throw new IllegalArgumentException("Invalid transaction type.");
        }

        bank.performTransaction(transaction);
        System.out.println("Transaction performed successfully.");
    }

    private static void withdrawMoney(Scanner scanner) throws AccountNotFoundException, InsufficientFundsException {
        System.out.println("Enter account ID:");
        String accountId = scanner.nextLine();
        System.out.println("Enter amount to withdraw:");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        Account account = bank.getAccountById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Account not found."));
        account.withdraw(amount);
        System.out.println("Withdrawal successful.");
    }

    private static void depositMoney(Scanner scanner) throws AccountNotFoundException {
        System.out.println("Enter account ID:");
        String accountId = scanner.nextLine();
        System.out.println("Enter amount to deposit:");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        Account account = bank.getAccountById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Account not found."));
        account.deposit(amount);
        System.out.println("Deposit successful.");
    }

    private static void checkBalance(Scanner scanner) throws AccountNotFoundException {
        System.out.println("Enter account ID:");
        String accountId = scanner.nextLine();

        Account account = bank.getAccountById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Account not found."));
        System.out.println("Current balance: " + account.getBalance());
    }

    private static void listAccounts() {
        bank.getAccounts().forEach(Account::displayAccountInfo);
    }

    private static void checkTotalTransactionFees() {
        System.out.println("Total transaction fees: " + bank.getTotalTransactionFees());
    }

    private static void checkTotalTransferAmount() {
        System.out.println("Total transfer amount: " + bank.getTotalTransferAmount());
    }

    private static void listAccountTransactions(Scanner scanner) throws AccountNotFoundException {
        System.out.println("Enter account ID:");
        String accountId = scanner.nextLine();

        Account account = bank.getAccountById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Account not found."));
        System.out.println("Transactions for account ID: " + accountId);
        account.getTransactions().forEach(System.out::println);
    }
}
