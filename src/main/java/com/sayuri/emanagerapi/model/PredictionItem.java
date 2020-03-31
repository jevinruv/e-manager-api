package com.sayuri.emanagerapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.*;

@Entity
@JsonDeserialize(using = PredictionItemDeserializer.class)
public class PredictionItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double consumption;
    private double consumptionLower;
    private double consumptionUpper;
    private String consumptionDate;

    @ManyToOne
    @JoinColumn
    @JsonIgnoreProperties("predictionItems")
    private Prediction prediction;

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

    public String getConsumptionDate() {
        return consumptionDate;
    }

    public void setConsumptionDate(String consumptionDate) {
        this.consumptionDate = consumptionDate;
    }

    public Prediction getPrediction() {
        return prediction;
    }

    public void setPrediction(Prediction prediction) {
        this.prediction = prediction;
    }
}
