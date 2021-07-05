package com.mybank.account;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class MusicalChairs {
	public static class AccountBalanceComparator implements Comparator<AbstractAccount> {

		@Override
		public int compare(AbstractAccount account1, AbstractAccount account2) {
			// Compare returns a negative number if account 1 should be listed before
			// account 2
			// A positive number, if account 2 should be listed before account 1
			// Returns 0, if they are considered the same
			if (account1.getBalance() > account2.getBalance()) {
				return -1;
			} else if (account1.getBalance() < account2.getBalance()) {
				return 1;
			} else {
				return 0;
			}
		}

	}

	public static void play() throws InsufficientFundsException {
		CheckingAccount one = new CheckingAccount("customer one", "customer one account", 100.0, "c1");

		SavingsAccount two = new SavingsAccount("saver two", "saver two's savings account", 100.0, "s2");

		CheckingAccount three = new CheckingAccount("customer three", "customer three's account", 100.0, "c3");

		SavingsAccount four = new SavingsAccount("saver four", "saver four's savings account", 100.0, "s4");

		CheckingAccount five = new CheckingAccount("customer five", "customer five's account", 100.0, "c5");

		List<AbstractAccount> allAccounts = Arrays.asList(one, two, three, four, five);

		for (int amount = 1; amount < 1000; amount++) {
			int i = amount % 5; // modulo (%) nifty little trick to keep a number between 0 and 4 in this case
			int j = (i + 1) % 5;
			AbstractAccount fromAccount = allAccounts.get(i);
			AbstractAccount toAccount = allAccounts.get(j);

			// transfer money!!!
			Double amountTransferred = fromAccount.transfer(toAccount, amount);

			// check if transfer worked, if not, break out of loop
			if (amountTransferred == null) {
				break;
			}
		}

		// print all balances at the end of the game
		// hint: System.out.println("Account one balance: " + one.getBalance());
		for (AbstractAccount account : allAccounts) {
			System.out.println(account.getAccountName() + " - balance : " + account.getBalance());
		}
	}

	public static void playWithCollections() {
		System.out.println("Inside new play method");

		// Generate 5 bank accounts, mix of checking and saving accounts
		List<AbstractAccount> accountList = new ArrayList<>();

		for (int i = 0; i < 5; i++) {
			// create new account with a balance
			String customer = "Customer " + i;
			String accountName = "Account " + i;
			String accountNumber = String.valueOf(i);
			int balance = (i + 1) * 100;
			AbstractAccount thisAccount;
			if (i % 2 == 0) {
				thisAccount = new CheckingAccount(customer, accountName, balance, accountNumber);
			} else {
				thisAccount = new SavingsAccount(customer, accountName, balance, accountNumber);
			}
			// add that account to a collection (list)
			accountList.add(thisAccount);
		}

		System.out.println(accountList);
		// keep transferring money, until a transfer fails (because of too little money
		// left)

		for (int amount = 1; amount < 1000; amount++) {

			int i = amount % 5; // modulo (%) nifty little trick to keep a number between 0 and 4 in this case
			int j = (i + 1) % 5;

			AbstractAccount fromAccount = accountList.get(i);
			AbstractAccount toAccount = accountList.get(j);

			// transfer money!!!

			try {
				fromAccount.transfer(toAccount, amount);

			} catch (InsufficientFundsException e) {
				break;
			}
		}

		for (AbstractAccount account : accountList) {
			System.out.println(account.getAccountName() + "- balance: " + account.getBalance());
		}
	}

	public static void playWithSet() {
		System.out.println("Inside new play method");

		// Generate 5 bank accounts, mix of checking and saving accounts
		List<AbstractAccount> accountList = new ArrayList<>();

		for (int i = 0; i < 5; i++) {
			// create new account with a balance
			String customer = "Customer " + i;
			String accountName = "Account " + i;
			String accountNumber = String.valueOf(i);
			int balance = (i + 1) * 100;
			AbstractAccount thisAccount;
			if (i % 2 == 0) {
				thisAccount = new CheckingAccount(customer, accountName, balance, accountNumber);
			} else {
				thisAccount = new SavingsAccount(customer, accountName, balance, accountNumber);
			}
			// add that account to a collection (list)
			accountList.add(thisAccount);
		}

		System.out.println(accountList);

		// find the unique set inside account list
		Set<AbstractAccount> uniqueAccounts = new HashSet<>(accountList);
		System.out.println("Unique accounts: " + uniqueAccounts);

	}

	public static void playWithMap() {
		System.out.println("Inside play with map");
		// Generate 5 bank accounts, mix of checking and saving accounts
		// this time we will chuck it in the Map
		Map<String, AbstractAccount> accountsMap = new HashMap<>();

		for (int i = 0; i < 5; i++) {
			// create new account with a balance
			String customer = "Customer " + i;
			String accountName = "Account " + i;
			String accountNumber = "#" + i;
			int balance = (i + 1) * 100;
			AbstractAccount thisAccount;
			if (i % 2 == 0) {
				thisAccount = new CheckingAccount(customer, accountName, balance, accountNumber);
			} else {
				thisAccount = new SavingsAccount(customer, accountName, balance, accountNumber);
			}
			// add that account to a collection (list)
			accountsMap.put(accountNumber, thisAccount);
		}

		System.out.println(accountsMap);
	}

	public static void playWithSorting() {
		System.out.println("Inside play with Sorting");

		// Generate 5 bank accounts, mix of checking and saving accounts
		List<AbstractAccount> accountList = new ArrayList<>();

		for (int i = 0; i < 5; i++) {
			// create new account with a balance
			String customer = "Customer " + i;
			String accountName = "Account " + i;
			String accountNumber = String.valueOf(i);
			int balance = (i + 1) * 100;
			AbstractAccount thisAccount;
			if (i % 2 == 0) {
				thisAccount = new CheckingAccount(customer, accountName, balance, accountNumber);
			} else {
				thisAccount = new SavingsAccount(customer, accountName, balance, accountNumber);
			}
			// add that account to a collection (list)
			accountList.add(thisAccount);
		}

		System.out.println(accountList);

		accountList.sort(new AccountBalanceComparator());
		System.out.println("Sorted by highest balance first: " + accountList);
		
		//do the same thing with tree set
		//beware: will remove duplicates!!!
		Set<AbstractAccount> sortedSet = new TreeSet<>(new AccountBalanceComparator());
		sortedSet.addAll(accountList);
		System.out.println("Sorted with a tree set: " + accountList);
	}
}
