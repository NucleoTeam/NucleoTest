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

    protected Logger logger = Logger.getLogger(AccountService.class.getName());

    public AccountService(String serviceUrl) {
        this.serviceUrl = serviceUrl.startsWith("http") ? serviceUrl : "http://" + serviceUrl;
    }

    public String getLogin(String username, String password){
        return restTemplate.postForObject(
            serviceUrl+"/authenticate/login",
            new AccountRequest(username,password),
            String.class
        );
    }
    public String getRegister(String username, String password){
        return restTemplate.postForObject(
                serviceUrl+"/authenticate/create",
                new AccountRequest(username,password),
                String.class
        );
    }
    public String getSessionValid(String username, String password){
        return restTemplate.postForObject(
                serviceUrl+"/authenticate/create",
                new AccountRequest(username,password),
                String.class
        );
    }
}
