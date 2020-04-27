package com.sayuri.emanagerapi.form;

import com.sayuri.emanagerapi.model.CustomerCategoryPrice;

public class ConsumptionCalculateResponse {

    private double total;
    private CustomerCategoryPrice customerCategoryPrice;

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public CustomerCategoryPrice getCustomerCategoryPrice() {
        return customerCategoryPrice;
    }

    public void setCustomerCategoryPrice(CustomerCategoryPrice customerCategoryPrice) {
        this.customerCategoryPrice = customerCategoryPrice;
    }
}
