package com.abc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class Account {

	public static final int CHECKING = 0;
	public static final int SAVINGS = 1;
	public static final int MAXI_SAVINGS = 2;
	
	public static final Comparator<Transaction> TRANSACTION_ORDER_BY_DATE_COMPARATOR = new Comparator<Transaction>() {
		public int compare(Transaction t1, Transaction t2) {
			return t1.getTransactionDate().compareTo(t1.getTransactionDate());
		}
	};

	private final int accountType;
	private double balance;
	private List<Transaction> transactions;

	public Account(int accountType) {
		this.accountType = accountType;
		this.balance = 0.0;
		this.transactions = new ArrayList<Transaction>();
	}

	// synchronized the method to make it thread safe
	public synchronized void deposit(double amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException(
					"amount must be greater than zero");
		} else {
			balance += amount;
			transactions.add(new Transaction(amount, TransactionType.DEPOSIT));
		}
	}

	// synchronized the method to make it thread safe
	public synchronized void withdraw(double amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException(
					"amount must be greater than zero");
		} else if (amount > balance) {
			throw new IllegalArgumentException(
					"amount must be less than or equal to balance");
		} else {
			balance -= amount;
			transactions
					.add(new Transaction(-amount, TransactionType.WITHDRAW));
		}
	}

	public synchronized double interestEarned() {
		double amount = sumTransactions();
		switch (accountType) {
		case SAVINGS:
			if (amount <= 1000)
				return amount * 0.001;
			else
				return 1 + (amount - 1000) * 0.002;
		case MAXI_SAVINGS:
			if (isWithdrawnInTenDays())
				return amount * 0.01;
			else
				return amount * 0.05;
			
		default:
			return amount * 0.001;
		}
	}

	public synchronized double sumTransactions() {
		double amount = 0.0;
		for (Transaction t : transactions) {
			amount += t.getAmount();
		}
		return amount;
	}

	/*
	 * Redundant code
	 * 
	 * private double checkIfTransactionsExist(boolean checkAll) { double amount
	 * = 0.0; for (Transaction t: transactions) amount += t.getAmount(); return
	 * amount; }
	 */

	public int getAccountType() {
		return accountType;
	}

	public List<Transaction> getTransactions() {
		return Collections.unmodifiableList(transactions);
	}

	public synchronized double getBalance() {
		return balance;
	}

	public synchronized void transfer(Account toAccount, double amount) {
		if (amount <= balance) {
			this.balance = this.balance - amount;
			transactions.add(new Transaction(-amount,
					TransactionType.BALANCETRANSFER));

			toAccount.balance = toAccount.balance + amount;
			toAccount.transactions.add(new Transaction(amount,
					TransactionType.BALANCETRANSFER));
		}
	}
	private boolean isWithdrawnInTenDays(){
		
		ArrayList<Transaction> sortedTransction = new ArrayList<Transaction>(transactions);
		Collections.sort(sortedTransction,TRANSACTION_ORDER_BY_DATE_COMPARATOR);
		Date currentDate = DateProvider.now();
		Date before10days = DateProvider.beforeTenDays();
		
		for(Transaction t : sortedTransction ){
			if(TransactionType.WITHDRAW.equals(t.getTransactionType()) 
					&& t.getTransactionDate().after(before10days) && t.getTransactionDate().before(currentDate)){
				return true;
			}
		}
		return false;
	}
}
