package com.sayuri.emanagerapi.form;

import com.sayuri.emanagerapi.model.EConsumption;

public class ConsumptionEmailForm {

    private String report;
    private EConsumption consumption;

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public EConsumption getConsumption() {
        return consumption;
    }

    public void setConsumption(EConsumption consumption) {
        this.consumption = consumption;
    }
}
