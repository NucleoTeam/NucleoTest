package com.synload.nucleoTest.endpoints;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Created by Nathaniel on 8/5/2017.
 */
@RestController
@RequestMapping("/tests")
public class Login {
    public static TreeMap<String, TreeMap<String, ArrayList<Long>>> events = new TreeMap<>();

    @GetMapping("/login")
    public Object login(){
        return events.get("login");
    }

    @GetMapping("/create")
    public Object create(){
        return events.get("unique_account");
    }

    @GetMapping("/session")
    public Object session(){
        return events.get("session_verification");
    }

    @GetMapping("/logout")
    public Object logout(){
        return events.get("session_logout");
    }
}
