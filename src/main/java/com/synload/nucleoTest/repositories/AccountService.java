package com.synload.nucleoTest.repositories;

import com.synload.nucleoTest.domain.AccountData;
import com.synload.nucleoTest.requests.AccountRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;
import java.util.logging.Logger;

/**
 * Created by Nathaniel on 8/5/2017.
 */
public class AccountService {
    @Autowired
    @LoadBalanced
    protected RestTemplate restTemplate;

    protected String serviceUrl;

    private static String username;

    private static String password;

    protected Logger logger = Logger.getLogger(AccountService.class.getName());

    public AccountService(String serviceUrl) {
        this.serviceUrl = serviceUrl.startsWith("http") ? serviceUrl : "http://" + serviceUrl;
        username = UUID.randomUUID().toString(); // set test username
        password = UUID.randomUUID().toString(); // set test password
    }

    public String getLogin(){
        return restTemplate.postForObject(
            serviceUrl+"/authenticate/login",
            new AccountRequest(username,password),
            String.class
        );
    }
}
