package com.example.c0767722_w2020_mad3125_fp.Models;

import java.io.Serializable;

public class Bill implements Serializable {
    String customerId;
    String billId;
    String billDate;
    String billType;
    String billAmount;

    public Bill() {
    }

    public Bill(String customerId, String billId, String billDate, String billType, String billAmount) {
        this.customerId = customerId;
        this.billId = billId;
        this.billDate = billDate;
        this.billType = billType;
        this.billAmount = billAmount;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }

    public String getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(String billAmount) {
        this.billAmount = billAmount;
    }

}
