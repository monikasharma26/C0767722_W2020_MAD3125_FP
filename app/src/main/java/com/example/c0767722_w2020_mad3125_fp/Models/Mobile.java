package com.example.c0767722_w2020_mad3125_fp.Models;

import java.io.Serializable;

public class Mobile extends Bill {
    String manufacture;
    String planName;
    String mobileNumber;
    String gnbUsed;
    String mintuesUsed;


    public Mobile(String customerId, String billId, String billDate,
                  String billType, String billAmount, String manufacture, String planName, String mobileNumber, String gnbUsed, String mintuesUsed) {
        super(customerId, billId, billDate, billType, billAmount);
        this.manufacture = manufacture;
        this.planName = planName;
        this.mobileNumber = mobileNumber;
        this.gnbUsed = gnbUsed;
        this.mintuesUsed = mintuesUsed;
    }
    public Mobile(String manufacture, String planName, String mobileNumber, String gnbUsed, String mintuesUsed) {
        this.manufacture = manufacture;
        this.planName = planName;
        this.mobileNumber = mobileNumber;
        this.gnbUsed = gnbUsed;
        this.mintuesUsed = mintuesUsed;
    }

    public String getManufacture() {
        return manufacture;
    }

    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getGnbUsed() {
        return gnbUsed;
    }

    public void setGnbUsed(String gnbUsed) {
        this.gnbUsed = gnbUsed;
    }

    public String getMintuesUsed() {
        return mintuesUsed;
    }

    public void setMintuesUsed(String mintuesUsed) {
        this.mintuesUsed = mintuesUsed;
    }

    @Override
    public String toString() {
        return "Mobile{" +
                "manufacture='" + manufacture + '\'' +
                ", planName='" + planName + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", gnbUsed='" + gnbUsed + '\'' +
                ", mintuesUsed='" + mintuesUsed + '\'' +
                ", customerId='" + customerId + '\'' +
                ", billId='" + billId + '\'' +
                ", billDate='" + billDate + '\'' +
                ", billType='" + billType + '\'' +
                ", billAmount='" + billAmount + '\'' +
                '}';
    }
}
