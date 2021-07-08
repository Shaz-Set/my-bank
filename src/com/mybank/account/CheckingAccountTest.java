package com.mybank.account;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CheckingAccountTest {
	// class under test
	CheckingAccount account;

	@BeforeEach
	void setup() {
		this.account = new CheckingAccount("customer 1", "test account", 0, "test account number");
	}

	@Test
	void deposit__amount_greater_than_zero__works() {
		// try with good amount (greater than zero)
		double amount = 42.0;

		// do the actual test
		account.deposit(amount);

		// get the actual balance
		double balance = account.getBalance();

		// check it did work
		assertEquals(amount, balance);
	}

	@Test
	void deposit__amount_less_than_zero__throws() {
		// bad amount
		double amount = -1.0;

		// do it
		assertThrows(IllegalArgumentException.class, () -> {
			account.deposit(amount);
		});
	}

	@Test
	void deposit__amount_equal_to_zero__throws() {
		double amount = 0.0;

		// do it
		assertThrows(IllegalArgumentException.class, () -> {
			account.deposit(amount);
		});
	}

	@Test
	void withdraw__amount_greater_than_zero__works() throws InsufficientFundsException {
		double amount = 100;
		account.deposit(amount + 1);
		account.withdraw(amount);
		assertEquals(1.0, account.getBalance());

	}

	@Test
	void withdraw__amount_less_than_zero__throws() {
		double amount = -100;
		assertThrows(IllegalArgumentException.class, () -> {
			account.withdraw(amount);
		});
	}

	@Test
	void withdraw__amount_equal_to_zero__throws() {
		double amount = 0.0;
		assertThrows(IllegalArgumentException.class, () -> {
			account.withdraw(amount);
		});
	}

	@Test
	void withdraw__amount_more_than_balance__throws() {
		double amount = 100;
		account.getBalance();
		assertThrows(InsufficientFundsException.class, () -> {
			account.withdraw(amount);
		});
	}
}