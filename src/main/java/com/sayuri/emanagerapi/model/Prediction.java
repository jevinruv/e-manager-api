package com.sayuri.emanagerapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@JsonDeserialize(using = PredictionDeserializer.class)
public class Prediction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double consumption;
    private double consumptionLower;
    private double consumptionUpper;
    private String consumptionDate;
    private int predictionSeq;

    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss", timezone = "Asia/Colombo")
    private Date createdDate;

    public Prediction() {
        this.createdDate = getDate();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getConsumption() {
        return consumption;
    }

    public void setConsumption(double consumption) {
        this.consumption = consumption;
    }

    public double getConsumptionLower() {
        return consumptionLower;
    }

    public void setConsumptionLower(double consumptionLower) {
        this.consumptionLower = consumptionLower;
    }

    public double getConsumptionUpper() {
        return consumptionUpper;
    }

    public void setConsumptionUpper(double consumptionUpper) {
        this.consumptionUpper = consumptionUpper;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getConsumptionDate() {
        return consumptionDate;
    }

    public void setConsumptionDate(String consumptionDate) {
        this.consumptionDate = consumptionDate;
    }

    public int getPredictionSeq() {
        return predictionSeq;
    }

    public void setPredictionSeq(int predictionSeq) {
        this.predictionSeq = predictionSeq;
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
