package com.synload.nucleoTest.tests;

import com.synload.nucleoTest.repositories.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by Nathaniel on 8/5/2017.
 */
@Controller
public class AccountAuthenticationTests {
    @Autowired
    protected AccountService accountService;

    public AccountAuthenticationTests(AccountService accountService){
        this.accountService = accountService;
    }



}
