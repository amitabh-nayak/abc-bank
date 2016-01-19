package com.abc;

/**
 * Need an account type enum rather than embedding the type in the
 * Account class. 
 *
 */
public enum AccountType {
	
	CHECKING("CHECKING", 0),
	SAVINGS("SAVINGS", 1),
	MAXI_SAVINGS("MAXI_SAVINGS", 2);
	
	private final String name;
	private final int value;
	
	private AccountType(final String name, final int value) {
		this.name = name;
        this.value = value;
    }

	public String getName() {
		return name;
	}

	public int getValue() {
		return value;
	}
	
}
