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

    @GetMapping("/logins")
    public Object logins(){
        return events.get("login");
    }

    @GetMapping("/create")
    public Object create(){
        return events.get("create");
    }


}
