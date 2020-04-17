package com.example.c0767722_w2020_mad3125_fp.Models;

public class Customer 
{
    private String id;
    private String firstName;
    private String lastName;
    private String fullName;
    private String emailId;
    private String mobileNo;

    public Customer() {
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
        return firstName + lastName ;
    }



    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId='" + id + '\'' +
                ", FullName='" + getFullName() + '\'' +
                ", emailId='" + emailId + '\'' +
                ", mobile='" + mobileNo + '\'' +
                '}';
    }


}
