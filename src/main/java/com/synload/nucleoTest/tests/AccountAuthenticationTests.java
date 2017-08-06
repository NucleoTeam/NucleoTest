package com.synload.nucleoTest.tests;

import com.synload.nucleoTest.endpoints.Login;
import com.synload.nucleoTest.repositories.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

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
        String session = accountService.getLogin();
        if(session!=null) {
            if(session.equals("")) {
                Login.failedLogins++;
            } else {
                Login.successfulLogins++;
            }
        } else {
            Login.failedLogins++;
        }
        logger.info("session: "+session);
    }

}
