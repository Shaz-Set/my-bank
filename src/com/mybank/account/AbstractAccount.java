package com.mybank.account;

public abstract class AbstractAccount {
    /*
    Properties
    - Customer (Owner)
    - (Account) Name
    - Balance
    - Number
     */

	private String customer;
    private String accountName;
    private double balance;
    private final String accountNumber;

    protected AbstractAccount(String customer, String accountName, double balance, String accountNumber) {
        super();
        this.customer = customer;
        this.accountName = accountName;
        this.balance = balance;
        this.accountNumber = accountNumber;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    /*
    Methods
    - Deposit
    - Withdraw
    - Transfer (internal, externals)
    - Interest
     */
    public Double deposit(double amount) throws IllegalArgumentException {
        this.balance += amount;
        System.out.println("Deposited amount: " + amount);
        return amount;
    }

    public Double withdraw(double amount) throws InsufficientFundsException, UnsupportedOperationException {
        this.balance -= amount;
        System.out.println("Withdrawn amount: " + amount);
        return amount;
    }

    public Double transfer(AbstractAccount toAccount, double amount) throws InsufficientFundsException {
        // withdraw from this account
        this.balance -= amount;
        // deposit into other account
        toAccount.balance += amount;
        System.out.println("Transferred amount: " + amount);

        return amount;
    }

    public void checkAmountIsGreaterThanZeroElseThrow(double amount) throws IllegalArgumentException {
        if (amount <= 0.0) {
            throw new IllegalArgumentException("Amount must be bigger than zero: " + amount);
        }
    }

    public void checkForSufficientFundsElseThrow(double amount) throws InsufficientFundsException {
        if (amount > this.balance) {
            throw new InsufficientFundsException(
                    "Insufficient funds: " +
                    this.balance +
                    " - cannot transfer: " +
                    amount);
        }
    }
    @Override
   	public String toString() {
   		return "AbstractAccount [customer=" + customer + ", accountName=" + accountName + ", balance=" + balance
   				+ ", accountNumber=" + accountNumber + "]";
   	}
}
