package com.abc;

/**
 * 
 * An enum class for transaction types
 *
 */
public enum TransactionType {
	
	DEPOSIT("DEPOSIT", 0),
	WITHDRAW("WITHDRAW", 1),
	BALANCETRANSFER("BALANCETRANSFER", 2);
	
	private final String name;
	private final int value;
	
	public String getName() {
		return name;
	}

	public int getValue() {
		return value;
	}

	private TransactionType(String name, int value) {
		this.name = name;
		this.value = value;
	}
	
	
}
