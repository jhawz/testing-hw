package com.apress.springrecipes.bank;

import org.hibernate.type.SetType;

public class Account {
	
	protected enum AccountType {
		CHECKING("Checking"), SAVINGS("Savings"), BROKERAGE("Brokerage");
		
		private String type;
		
		private AccountType(String type) {
			this.type = type;
		}
		
		public String getTypeString() {
			return type;
		}
	}

    private String accountNo;
    private double balance;
    private String accountType;

    public Account() {}

    public Account(String accountNo, double balance) {
        this.accountNo = accountNo;
        this.balance = balance;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public double getBalance() {
        return balance;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    
    public String getAccountType() {
        return accountType;
    }
    
    public void setAccountType(String accountType) {
    	this.accountType = accountType;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Account)) {
            return false;
        }
        Account account = (Account) obj;
        return account.accountNo.equals(accountNo) && account.balance == balance;
    }
}
