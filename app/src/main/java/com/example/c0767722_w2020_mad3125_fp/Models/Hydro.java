package com.example.c0767722_w2020_mad3125_fp.Models;

import java.io.Serializable;

public class Hydro extends Bill {
    String agencyName;
    String unitsConsumed;

    public Hydro(String customerId, String billId, String billDate, String billType, String billAmount, String agencyName, String unitsConsumed) {
        super(customerId, billId, billDate, billType, billAmount);
        this.agencyName = agencyName;
        this.unitsConsumed = unitsConsumed;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public String getUnitsConsumed() {
        return unitsConsumed;
    }

    public void setUnitsConsumed(String unitsConsumed) {
        this.unitsConsumed = unitsConsumed;
    }

    @Override
    public String toString() {
        return "Hydro{" +
                "agencyName='" + agencyName + '\'' +
                ", unitsConsumed='" + unitsConsumed + '\'' +
                ", customerId='" + customerId + '\'' +
                ", billId='" + billId + '\'' +
                ", billDate='" + billDate + '\'' +
                ", billType='" + billType + '\'' +
                ", billAmount='" + billAmount + '\'' +
                '}';
    }
}
