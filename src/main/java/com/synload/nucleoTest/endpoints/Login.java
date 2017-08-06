package com.synload.nucleoTest.endpoints;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Nathaniel on 8/5/2017.
 */
@RestController
@RequestMapping("/tests")
public class Login {
    public static long failedLogins = 0;
    public static long successfulLogins = 0;

    @GetMapping("/logins")
    public String logins(){
        return successfulLogins+" successful, "+failedLogins+" Failed";
    }
}
