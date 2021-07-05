package com.mybank.account;

public class SavingsAccount extends AbstractAccount {

    public SavingsAccount(String customer, String accountName, double balance, String accountNumber) {
        super(customer, accountName, balance, accountNumber);
    }

    @Override
    public Double deposit(double amount) throws IllegalArgumentException {
        // validate amount is good
        checkAmountIsGreaterThanZeroElseThrow(amount);

        return super.deposit(amount);
    }

    @Override
    public Double withdraw(double amount) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Cannot withdraw from a savings account.");
    }

    @Override
    public Double transfer(AbstractAccount toAccount, double amount) throws InsufficientFundsException {
        // validate amount is good
        checkAmountIsGreaterThanZeroElseThrow(amount);
        // validate we have the funds
        checkForSufficientFundsElseThrow(amount);

        return super.transfer(toAccount, amount);
    }
}
