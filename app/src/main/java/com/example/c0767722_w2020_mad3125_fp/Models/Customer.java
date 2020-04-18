package com.example.c0767722_w2020_mad3125_fp.Models;

import java.io.Serializable;

public class Customer implements Serializable
{
    private String id;
    private String firstName;
    private String lastName;
    private String fullName;
    private String emailId;

    public Customer() {
    }

    public Customer(String id, String firstName, String lastName, String emailId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return firstName + " " + lastName ;
    }



    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }


    @Override
    public String toString() {
        return "Customer{" +
                "customerId='" + id + '\'' +
                ", FullName='" + getFullName() + '\'' +
                ", emailId='" + emailId + '\'' +
                '}';
    }


}
