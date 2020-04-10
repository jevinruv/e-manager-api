package com.sayuri.emanagerapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class CustomerCategoryPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String consumptionValue;
    private double energyCharge;
    private double fixedCharge;
    private double fuelAdjustmentCharge;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", timezone = "Asia/Colombo")
    private Date reviewedDate;

    @ManyToOne
    @JoinColumn
    @JsonIgnoreProperties("customerCategoryPrices")
    private CustomerCategory customerCategory;

    public CustomerCategoryPrice() {
        this.reviewedDate = this.getDate();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getConsumptionValue() {
        return consumptionValue;
    }

    public void setConsumptionValue(String consumptionValue) {
        this.consumptionValue = consumptionValue;
    }

    public double getEnergyCharge() {
        return energyCharge;
    }

    public void setEnergyCharge(double energyCharge) {
        this.energyCharge = energyCharge;
    }

    public double getFixedCharge() {
        return fixedCharge;
    }

    public void setFixedCharge(double fixedCharge) {
        this.fixedCharge = fixedCharge;
    }

    public double getFuelAdjustmentCharge() {
        return fuelAdjustmentCharge;
    }

    public void setFuelAdjustmentCharge(double fuelAdjustmentCharge) {
        this.fuelAdjustmentCharge = fuelAdjustmentCharge;
    }

    public Date getReviewedDate() {
        return reviewedDate;
    }

    public void setReviewedDate(Date reviewedDate) {
        this.reviewedDate = reviewedDate;
    }

    public CustomerCategory getCustomerCategory() {
        return customerCategory;
    }

    public void setCustomerCategory(CustomerCategory customerCategory) {
        this.customerCategory = customerCategory;
    }

    private Date getDate() {

        Date date = new Date();

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//        df.setTimeZone(TimeZone.getTimeZone("Asia/Colombo"));

        String strDate = df.format(date);

        Date newDate = null;
        try {
            newDate = df.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return newDate;
    }
}
