package com.abc;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TransactionTest {
   // This is a redundant test. Need to add test cases to test various transactions
	@Test
    public void transaction() {
        Transaction t = new Transaction(5, TransactionType.DEPOSIT);
        assertTrue(t instanceof Transaction);
    }
}
