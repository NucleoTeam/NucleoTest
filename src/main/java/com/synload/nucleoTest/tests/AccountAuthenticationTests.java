package com.synload.nucleoTest.tests;

import com.synload.nucleoTest.endpoints.Login;
import com.synload.nucleoTest.repositories.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import javax.el.LambdaExpression;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.TreeMap;
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


    @Scheduled(fixedRate=20000)
    public void test_one(){

        logger.info("Make sure only 1 account can be created");
        String username = UUID.randomUUID().toString(); // set test username
        String password = UUID.randomUUID().toString(); // set test password

        logger.info("Register with account details");
        String response = null;
        try{
            response = accountService.getRegister(username, password);
        }catch(Exception e){
            e.printStackTrace();
        }
        setLoginStats( response, "create", "1: create account", 1);

        logger.info("Login again [expected=new session]");
        response = null;
        try{
            response = accountService.getLogin(username, password);
        }catch(Exception e){
            e.printStackTrace();
        }
        setLoginStats( response, "create", "2: can login", 1);

    }

    @Scheduled(initialDelay=1000, fixedRate=20000)
    public void test_two(){

        logger.info("Make sure only 1 account can be created");

        String username = UUID.randomUUID().toString(); // set test username
        String password = UUID.randomUUID().toString(); // set test password

        logger.info("Register with account details");
        String response = null;
        try{
            response = accountService.getRegister(username, password);
        }catch(Exception e){
            e.printStackTrace();
        }
        setLoginStats( response, "unique_account", "1: create new account", 1);

        logger.info("Register with again with the same account details [expected=null]");
        response = null;
        try{
            response = accountService.getRegister(username, password);
        }catch(Exception e){
            e.printStackTrace();
        }
        setLoginStats( response, "unique_account", "2: repeated details, not unique account", 0);
        logger.info("session: "+response);

    }

    @Scheduled(initialDelay=2000, fixedRate=20000)
    public void test_three(){
        String username = UUID.randomUUID().toString(); // set test username
        String password = UUID.randomUUID().toString(); // set test password

        logger.info("Register with account details");
        String response = null;
        try{
            response = accountService.getRegister(username, password);
        }catch(Exception e){
            e.printStackTrace();
        }
        setLoginStats(accountService.getRegister(username, password), "session_verification", "1: create account", 1);

        logger.info("Check session and make sure its valid [expected=true]");
        response = null;
        try{
            response = accountService.getSessionValid(username, password);
        }catch(Exception e){
            e.printStackTrace();
        }
        setLoginStats(accountService.getRegister(username, password), "session_verification", "1: session validation", 3);
        logger.info("isValid: "+response);

    }

    public void setLoginStats(String response, String type, String event, int sw){
        if(!Login.events.containsKey(type)){
            Login.events.put(type, new TreeMap<>());
        }
        switch(sw){
            case 0:
                if(!Login.events.get(type).containsKey(event)){
                    Login.events.get(type).put(event,new ArrayList<>());
                }
                if(response!=null){
                    Login.events.get(type).get(event).add(new Date().getTime()); // save failure
                }
                break;
            case 1:
                if(!Login.events.get(type).containsKey(event)){
                    Login.events.get(type).put(event,new ArrayList<>());
                }
                if(response==null){
                    Login.events.get(type).get(event).add(new Date().getTime()); // save failure
                }
                if(response.length()==0){
                    Login.events.get(type).get(event).add(new Date().getTime()); // save failure
                }
                break;
            case 3:
                if(!Login.events.get(type).containsKey(event)){
                    Login.events.get(type).put(event,new ArrayList<>());
                }
                if(response==null){
                    Login.events.get(type).get(event).add(new Date().getTime()); // save failure
                }
                if(response.equalsIgnoreCase("false")){
                    Login.events.get(type).get(event).add(new Date().getTime()); // save failure
                }
                break;
            case 4:
                if(!Login.events.get(type).containsKey(event)){
                    Login.events.get(type).put(event,new ArrayList<>());
                }
                if(response==null){
                    Login.events.get(type).get(event).add(new Date().getTime()); // save failure
                }
                if(response.equalsIgnoreCase("true")){
                    Login.events.get(type).get(event).add(new Date().getTime()); // save failure
                }
                break;
        }
    }

}
