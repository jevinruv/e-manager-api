package com.sayuri.emanagerapi.form;

public class ConsumptionCalculateForm {

    private int customerCategoryId;
    private double consumptionValue;
    private double maxDemandValue;

    public int getCustomerCategoryId() {
        return customerCategoryId;
    }

    public void setCustomerCategoryId(int customerCategoryId) {
        this.customerCategoryId = customerCategoryId;
    }

    public double getConsumptionValue() {
        return consumptionValue;
    }

    public void setConsumptionValue(double consumptionValue) {
        this.consumptionValue = consumptionValue;
    }

    public double getMaxDemandValue() {
        return maxDemandValue;
    }

    public void setMaxDemandValue(double maxDemandValue) {
        this.maxDemandValue = maxDemandValue;
    }
}
