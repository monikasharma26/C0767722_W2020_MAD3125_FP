package com.example.c0767722_w2020_mad3125_fp.Models;

import java.io.Serializable;

public class Internet extends Bill {

    String internetGbused;
    String providerName;

    public Internet(String customerId, String billId, String billDate, String billType, String billAmount, String internetGbused, String providerName) {
        super(customerId, billId, billDate, billType, billAmount);
        this.internetGbused = internetGbused;
        this.providerName = providerName;
    }

    public Internet(String internetGbused, String providerName) {
        this.internetGbused = internetGbused;
        this.providerName = providerName;
    }

    public String getInternetGbused() {
        return internetGbused;
    }

    public void setInternetGbused(String internetGbused) {
        this.internetGbused = internetGbused;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    @Override
    public String toString() {
        return "Internet{" +
                "internetGbused='" + internetGbused + '\'' +
                ", providerName='" + providerName + '\'' +
                ", customerId='" + customerId + '\'' +
                ", billId='" + billId + '\'' +
                ", billDate='" + billDate + '\'' +
                ", billType='" + billType + '\'' +
                ", billAmount='" + billAmount + '\'' +
                '}';
    }
}
