package com.synload.nucleoTest.tests;

import com.synload.nucleoTest.endpoints.Login;
import com.synload.nucleoTest.repositories.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.util.UUID;
import java.util.logging.Logger;

/**
 * Created by Nathaniel on 8/5/2017.
 */
@Controller
public class AccountAuthenticationTests {
    @Autowired
    protected AccountService accountService;

    protected Logger logger = Logger.getLogger(AccountAuthenticationTests.class.getName());

    public AccountAuthenticationTests(AccountService accountService){
        this.accountService = accountService;
    }


    @Scheduled(fixedRate=5000)
    public void test_one(){

        logger.info("Make sure only 1 account can be created");
        String username = UUID.randomUUID().toString(); // set test username
        String password = UUID.randomUUID().toString(); // set test password

        logger.info("Register with account details");
        setLoginStats(accountService.getRegister(username, password));

        logger.info("Login again [expected=new session]");
        setLoginStats(accountService.getLogin(username, password));

    }

    @Scheduled(initialDelay=1000, fixedRate=5000)
    public void test_two(){

        logger.info("Make sure only 1 account can be created");

        String username = UUID.randomUUID().toString(); // set test username
        String password = UUID.randomUUID().toString(); // set test password

        logger.info("Register with account details");
        setLoginStats(accountService.getRegister(username, password));

        logger.info("Register with again with the same account details [expected=null]");
        logger.info("session: "+accountService.getRegister(username, password));

    }

    @Scheduled(initialDelay=2000, fixedRate=5000)
    public void test_three(){
        String username = UUID.randomUUID().toString(); // set test username
        String password = UUID.randomUUID().toString(); // set test password

        logger.info("Register with account details");
        setLoginStats(accountService.getRegister(username, password));

        logger.info("Check session and make sure its valid [expected=true]");
        logger.info("isValid: "+accountService.getSessionValid(username, password));

    }

    public void setLoginStats(String session){
        logger.info("session: "+session);
        if(session!=null) {
            if(session.equals("")) {
                Login.failedLogins++;
            } else {
                Login.successfulLogins++;
            }
        } else {
            Login.failedLogins++;
        }
    }

}
