package com.sayuri.emanagerapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class MileStone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double planned;
    private double actual;
    private int eConsumptionId;
    private String status = "PLANNED";

    @JsonFormat(pattern = "yyyy-MM", timezone = "Asia/Colombo")
    private Date mileStoneDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPlanned() {
        return planned;
    }

    public void setPlanned(double planned) {
        this.planned = planned;
    }

    public double getActual() {
        return actual;
    }

    public void setActual(double actual) {
        this.actual = actual;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getMileStoneDate() {
        return mileStoneDate;
    }

    public void setMileStoneDate(Date mileStoneDate) {
        this.mileStoneDate = mileStoneDate;
    }

    public int geteConsumptionId() {
        return eConsumptionId;
    }

    public void seteConsumptionId(int eConsumptionId) {
        this.eConsumptionId = eConsumptionId;
    }
}
