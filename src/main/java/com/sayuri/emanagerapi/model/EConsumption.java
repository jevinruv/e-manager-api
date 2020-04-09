package com.sayuri.emanagerapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Entity
public class EConsumption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int weekNo;
    private double consumptionPlanned;
    private double consumptionActual;
    private double consumptionPlannedCost;
    private double consumptionActualCost;

    @JsonFormat(pattern = "dd-MM-yyyy", timezone = "Asia/Colombo")
    private Date consumptionDate;

    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss", timezone = "Asia/Colombo")
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

    public int getWeekNo() {
        return weekNo;
    }

    public void setWeekNo(int weekNo) {
        this.weekNo = weekNo;
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

        DateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
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
