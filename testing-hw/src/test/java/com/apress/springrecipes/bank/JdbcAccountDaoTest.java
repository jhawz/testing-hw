package com.apress.springrecipes.bank;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Timed;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@ContextConfiguration(locations = "/beans.xml")
public class JdbcAccountDaoTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	private static final String TEST_ACCOUNT_NO = "1234";
	
	 @Autowired
	    private AccountDao accountDao;  
	 
	 @Before
	 	public void setUp() throws Exception {
	        executeSqlScript("classpath:/bank.sql",true);
	    }
	 
	 	@Test
	    public void deposit() {
		 	Account account = new Account();
		 	account.setAccountNo("1234");
		 	account.setBalance(1000);
	        accountDao.createAccount(account);
	        
	        Account accountFromDB = accountDao.findAccount("1234");
	        
	        assertTrue("bad account",account.equals(accountFromDB) );
	    }

}
