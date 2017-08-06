package com.synload.nucleoTest.requests;

/**
 * Created by Nathaniel on 7/23/2017.
 */
public class AccountRequest {
    private String user=null;
    private String password=null;
    public  AccountRequest(){

    }

    public AccountRequest(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
