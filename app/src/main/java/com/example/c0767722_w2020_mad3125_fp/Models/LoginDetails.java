package com.example.c0767722_w2020_mad3125_fp.Models;


public class LoginDetails {
    private String id;
    private String loginTime;
    public LoginDetails() {
    }
    public LoginDetails(String id,String LoginTime ) {

        this.id=id;
        this.loginTime = LoginTime;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {this.id = id; }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String lTime) {this.loginTime = lTime; }
}


