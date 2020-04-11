package com.sayuri.emanagerapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class EConsumption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String frequency;
    private double consumptionPlanned;
    private double consumptionActual;
    private double consumptionPlannedCost;
    private double consumptionActualCost;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Colombo")
    private Date consumptionDate;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", timezone = "Asia/Colombo")
    private Date consumptionPlannedDate;

    public EConsumption() {
        this.consumptionPlannedDate = this.getDate();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public double getConsumptionPlanned() {
        return consumptionPlanned;
    }

    public void setConsumptionPlanned(double consumptionPlanned) {
        this.consumptionPlanned = consumptionPlanned;
    }

    public double getConsumptionActual() {
        return consumptionActual;
    }

    public void setConsumptionActual(double consumptionActual) {
        this.consumptionActual = consumptionActual;
    }

    public Date getConsumptionDate() {
        return consumptionDate;
    }

    public void setConsumptionDate(Date consumptionDate) {
        this.consumptionDate = consumptionDate;
    }

    public Date getConsumptionPlannedDate() {
        return consumptionPlannedDate;
    }

    public void setConsumptionPlannedDate(Date consumptionPlannedDate) {
        this.consumptionPlannedDate = consumptionPlannedDate;
    }

    public double getConsumptionPlannedCost() {
        return consumptionPlannedCost;
    }

    public void setConsumptionPlannedCost(double consumptionPlannedCost) {
        this.consumptionPlannedCost = consumptionPlannedCost;
    }

    public double getConsumptionActualCost() {
        return consumptionActualCost;
    }

    public void setConsumptionActualCost(double consumptionActualCost) {
        this.consumptionActualCost = consumptionActualCost;
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
