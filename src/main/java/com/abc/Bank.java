package com.abc;

import java.util.HashSet;

public class Bank {
	
    private HashSet<Customer> customers;

    public Bank() {
        customers = new HashSet<Customer>();
    }

    public void addCustomer(Customer customer) {
    	
        customers.add(customer);
    }

    public String customerSummary() {
        StringBuilder summary = 
        		new StringBuilder("Customer Summary");
        for (Customer c : customers)
            summary.append("\n - ").append(c.getName()).append(" (").append(format(c.getNumberOfAccounts(), "account")).append(")") ;
        return summary.toString();
    }

    //Make sure correct plural of word is created based on the number passed in:
    //If number passed in is 1 just return the word otherwise add an 's' at the end
    private String format(int number, String word) {
        return number + " " + (number == 1 ? word : word + "s");
    }

    public double totalInterestPaid() {
        double total = 0;
        for(Customer c: customers)
            total += c.totalInterestEarned();
        return total;
    }

   //This method is never tested
    public Customer getFirstCustomer() {
        Object[] bankCustomers = customers.toArray();
    	return bankCustomers.length > 0 ? (Customer)bankCustomers[0] : null;
    }
}
