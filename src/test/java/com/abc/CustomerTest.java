package com.abc;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CustomerTest {
	
	 private static final double DOUBLE_DELTA = 1e-15;

    @Test //Test customer statement generation
    public void testApp(){

        Account checkingAccount = new Account(Account.CHECKING);
        Account savingsAccount = new Account(Account.SAVINGS);

        Customer henry = new Customer("Henry").openAccount(checkingAccount).openAccount(savingsAccount);

        checkingAccount.deposit(100.0);
        savingsAccount.deposit(4000.0);
        savingsAccount.withdraw(200.0);

        assertEquals("Statement for Henry\n" +
                "\n" +
                "Checking Account\n" +
                "  deposit $100.00\n" +
                "Total $100.00\n" +
                "\n" +
                "Savings Account\n" +
                "  deposit $4,000.00\n" +
                "  withdrawal $200.00\n" +
                "Total $3,800.00\n" +
                "\n" +
                "Total In All Accounts $3,900.00", henry.getStatement());
    }

    @Test
    public void testOneAccount(){
        Customer oscar = new Customer("Oscar").openAccount(new Account(Account.SAVINGS));
        assertEquals(1, oscar.getNumberOfAccounts());
    }

    @Test
    public void testTwoAccount(){
        Customer oscar = new Customer("Oscar")
                .openAccount(new Account(Account.SAVINGS));
        oscar.openAccount(new Account(Account.CHECKING));
        assertEquals(2, oscar.getNumberOfAccounts());
    }

    @Ignore
    public void testThreeAcounts() {
        Customer oscar = new Customer("Oscar")
                .openAccount(new Account(Account.SAVINGS));
        oscar.openAccount(new Account(Account.CHECKING));
        assertEquals(3, oscar.getNumberOfAccounts());
    }
    
    @Test
    public void testBalanceTransfer(){
    	
    	
        Customer jack = new Customer("Jack");
        Account savingAccount = new Account(Account.SAVINGS);
        Account checkingAccount = new Account(Account.CHECKING);
        
        jack.openAccount(checkingAccount);     
        jack.openAccount(savingAccount);
        assertEquals(2, jack.getNumberOfAccounts());
        
        savingAccount.deposit(2000.00);
        assertEquals(2000.0,savingAccount.getBalance(), DOUBLE_DELTA);
        
        checkingAccount.deposit(3000.00);
        assertEquals(3000.0,checkingAccount.getBalance(), DOUBLE_DELTA);
        
        checkingAccount.transfer(savingAccount, 500.0);
        assertEquals(2500.0,checkingAccount.getBalance(), DOUBLE_DELTA);
        assertEquals(2500.0,savingAccount.getBalance(), DOUBLE_DELTA);
        
        assertEquals(2,checkingAccount.getTransactions().size());
        assertEquals(2,savingAccount.getTransactions().size());
        
        assertEquals(checkingAccount.getBalance(), checkingAccount.sumTransactions(), DOUBLE_DELTA);
        assertEquals(savingAccount.getBalance(), savingAccount.sumTransactions(), DOUBLE_DELTA);
        
    }
}
