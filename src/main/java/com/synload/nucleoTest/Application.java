package com.synload.nucleoTest;

import com.synload.nucleoTest.endpoints.Login;
import com.synload.nucleoTest.repositories.AccountService;
import com.synload.nucleoTest.tests.AccountAuthenticationTests;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import java.util.TreeMap;

/**
 * Created by Nathaniel on 8/5/2017.
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableScheduling
public class Application {
    public static final String ACCOUNT_SERVICE_URL = "ACCOUNTSERVICE";
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @LoadBalanced
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
    @Bean
    public AccountService accountService() {
        return new AccountService(ACCOUNT_SERVICE_URL);
    }

    @Bean
    public AccountAuthenticationTests accountAuthenticateServiceController() {
        return new AccountAuthenticationTests(accountService());
    }
}
