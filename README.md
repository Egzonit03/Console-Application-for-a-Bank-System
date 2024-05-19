# Console-Application-for-a-Bank-System
## Overview

This project is a Java-based console application that simulates a basic banking system. The application allows users to create and manage multiple bank accounts, perform transactions, and track account balances. The bank system supports both flat fee and percentage fee transactions.

## Features

- Create and manage user accounts.
- Perform transactions between accounts.
- Withdraw and deposit money into accounts.
- Charge transaction fees (flat fee or percentage fee).
- Display account details and transaction history.
- Track total transaction fees and total transfer amounts for the bank.

## Classes

The project consists of the following main classes:

- `Bank`: Represents the bank and manages a list of accounts and transactions.
- `Account`: Represents a bank account with an account ID, user name, balance, and transaction history.
- `Transaction`: Abstract class representing a transaction, with subclasses for flat fee and percentage fee transactions.
- `FlatFeeTransaction`: Subclass of `Transaction` that represents transactions with a flat fee.
- `PercentFeeTransaction`: Subclass of `Transaction` that represents transactions with a percentage fee.
- `Main`: The main class containing the console interface for interacting with the bank system.

## Getting Started

### Prerequisites

To run this application, you need to have the following installed on your system:

- Java Development Kit (JDK) 8 or higher
- An IDE (like IntelliJ IDEA, Eclipse, or NetBeans) or a text editor
- A terminal or command prompt

## How to Use

- When you run the application, you will be prompted with a menu to perform various actions:

Create account:
- Enter the user name and initial balance to create a new account.

Perform transaction:
- Enter the source account ID, destination account ID, amount, transaction reason, and transaction type (flat fee or percentage fee).

Withdraw money:
- Enter the account ID and amount to withdraw money from an account.

Deposit money:
- Enter the account ID and amount to deposit money into an account.

Check account balance:
- Enter the account ID to view the current balance of an account.

List accounts:
- Display a list of all accounts with their details.

Check total transaction fees:
- Display the total amount of transaction fees collected by the bank.

Check total transfer amount:
- Display the total amount of money transferred between accounts.

List account transactions:
- Enter the account ID to view the transaction history of an account.

Exit:
- Exit the application.

## Exception Handling

The application includes exception handling to manage errors such as:

- AccountNotFoundException: Thrown when an account is not found.
- InsufficientFundsException: Thrown when an account has insufficient funds for a transaction.

## Additional Notes

- The account balance cannot go below zero.
- Transaction fees are deducted from the account balance during withdrawals and deposits.
- The transaction history for each account includes the details of each transaction along with the associated fees.

## Contributing

- If you would like to contribute to this project, please fork the repository and submit a pull request. For major changes, please open an issue first to discuss what you would like to change.

## Acknowledgments

- Thank you for using the Bank System Console Application. If you have any questions or feedback, please feel free to open an issue or contact the project maintainer.
