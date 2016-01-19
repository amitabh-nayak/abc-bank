package com.abc;

import java.util.Date;

/**
 * 
 * In order to maintain consistency transaction and its attributes have to be atomic
 *
 */
public class Transaction {
    
	private final double amount;
    private final Date transactionDate;
    private final TransactionType transactionType;

    public Transaction(double amount, TransactionType transactionType) {
        this.amount = amount;
        this.transactionType = transactionType;
        this.transactionDate = DateProvider.now();
    }

	public double getAmount() {
		return amount;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}
    

}
