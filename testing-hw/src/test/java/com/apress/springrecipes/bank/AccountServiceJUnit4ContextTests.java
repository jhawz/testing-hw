package com.apress.springrecipes.bank;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.annotation.Timed;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.apress.springrecipes.bank.Account.AccountType;

@ContextConfiguration(locations = "classpath:beans.xml")
public class AccountServiceJUnit4ContextTests extends
        AbstractTransactionalJUnit4SpringContextTests {

    private static final String TEST_ACCOUNT_NO = "1234";
    private static final AccountType TEST_ACCOUNT_TYPE = AccountType.SAVINGS;

    @Autowired
    private AccountService accountService;

    @Before
    public void init() {
	executeSqlScript("classpath:/bank.sql",true);
        simpleJdbcTemplate.update(
                "INSERT INTO ACCOUNT (ACCOUNT_NO, BALANCE, ACCOUNT_TYPE) VALUES (?, ?, ?)",
                TEST_ACCOUNT_NO, 100, TEST_ACCOUNT_TYPE.getTypeString());
    }

    @Test
    public void deposit() {
        accountService.deposit(TEST_ACCOUNT_NO, 50);
        double balance = simpleJdbcTemplate.queryForObject(
                "SELECT BALANCE FROM ACCOUNT WHERE ACCOUNT_NO = ?",
                Double.class, TEST_ACCOUNT_NO);
        assertEquals(balance, 150.0, 0);
    }

    @Test
    @Repeat(5)
    public void withDraw() {
        accountService.withdraw(TEST_ACCOUNT_NO, 50);
        double balance = simpleJdbcTemplate.queryForObject(
                "SELECT BALANCE FROM ACCOUNT WHERE ACCOUNT_NO = ?",
                Double.class, TEST_ACCOUNT_NO);
        assertEquals(balance, 50.0, 0);
    }
}
